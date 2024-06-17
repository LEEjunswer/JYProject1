package com.JYProject.project.controller.apiController;

import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.model.dto.BoardResponseDTO;
import com.JYProject.project.model.dto.LikeDTO;
import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.service.BoardService.BoardService;
import com.JYProject.project.service.LikeService.LikeService;
import com.JYProject.project.service.MemberService.MemberService;
import com.JYProject.project.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BoardAPIController {
    private final BoardService boardService;
    private final LikeService likeService;
    private  final MemberService memberService;



    @RequestMapping(value="/boards/checkLogin",method = RequestMethod.GET)
    public ResponseEntity<Boolean> checkLogin(HttpSession session) {
        String isLoggedIn = (String) session.getAttribute(SessionConst.USER_ID);
        if (isLoggedIn != null) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }

    @PostMapping("/boards/list/paging")
    public  ResponseEntity<BoardResponseDTO> pagingBoardCategory(@RequestParam int categoryId, @RequestParam(defaultValue = "1") int page , @RequestParam(defaultValue = "10")int pageSize){
        BoardResponseDTO boardList = boardService.boardGetCategoryListPaging(categoryId,page,pageSize);
        System.out.println("boardList = " + boardList);
        return ResponseEntity.ok().body(boardList);
    }

    @RequestMapping(value = "/boards/disLikes" , method = RequestMethod.POST)
    public Map<String,Object> checkDisLikes(@RequestParam String boardId, HttpSession session){
        Map<String, Object> response = new HashMap<>();
        String isLogin = (String) session.getAttribute(SessionConst.USER_ID);
        Long changeBoardId = Long.parseLong(boardId);

        MemberDTO memberDTO = memberService.selectMemberDetail(isLogin);
        BoardDTO boardDTO  = boardService.selectBoardDetail(changeBoardId);
        if(boardDTO.getMemberId().equals(memberDTO.getMemberId())){
            response.put("message", "본인 글은 좋아요 싫어요를 하실 수 없습니다.");
            return response;
        }
        System.out.println("memberDTO = " + memberDTO);
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setBoardId(changeBoardId);
        likeDTO.setMemberId(memberDTO.getMemberId());
        int alreadyDisliked = likeService.getOneLikesBoardAndMemberId(likeDTO);

        if (alreadyDisliked != 0) {
            response.put("message", "이미 싫어요를 눌렀습니다.");
            return response;
        }

        likeDTO.setLikes(false);
        likeService.insertLikeBoard(likeDTO);
        int disLikeCount = likeService.getOneBoardDisLikes(likeDTO);
        response.put("dislikeCount", disLikeCount);
        response.put("message", "게시글 싫어요를 눌렀습니다.");
        return response;
    }
    @RequestMapping(value = "/boards/likes" , method = RequestMethod.POST)
    public Map<String, Object> checkLikes(@RequestParam String boardId, HttpSession session){
        Map<String, Object> response = new HashMap<>();
        String isLogin = (String) session.getAttribute(SessionConst.USER_ID);
        Long changeBoardId = Long.parseLong(boardId);
        MemberDTO memberDTO = memberService.selectMemberDetail(isLogin);
        BoardDTO boardDTO  = boardService.selectBoardDetail(changeBoardId);
        if(boardDTO.getMemberId().equals(memberDTO.getMemberId())){
            response.put("message", "본인 글은 좋아요 싫어요를 하실 수 없습니다.");
            return response;
        }
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setBoardId(changeBoardId);
        likeDTO.setMemberId(memberDTO.getMemberId());
        int alreadyDisliked = likeService.getOneLikesBoardAndMemberId(likeDTO);
        if (alreadyDisliked != 0) {
            response.put("message", "이미 좋아요를 하셨습니다");
            return response;
        }

        likeDTO.setLikes(true);
        likeService.insertLikeBoard(likeDTO);
        int likesCount = likeService.getOneBoardLikes(likeDTO);
        response.put("message", "게시글 좋아요를 눌렀습니다.");
        response.put("likesCount",likesCount);
        return response;
    }
/*    @PostMapping("/board/Paging")*/
}
