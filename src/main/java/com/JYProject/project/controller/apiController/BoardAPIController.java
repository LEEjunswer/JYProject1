package com.JYProject.project.controller.apiController;

import com.JYProject.project.model.dto.LikeDTO;
import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.service.BoardServiceImpl;
import com.JYProject.project.service.LikeServiceImpl;
import com.JYProject.project.service.MemberServiceImpl;
import com.JYProject.project.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BoardAPIController {
    private final BoardServiceImpl boardService;
    private final MemberServiceImpl memberService;
    private final LikeServiceImpl likeService;
    @RequestMapping(value="/boards/checkLogin",method = RequestMethod.GET)
    public ResponseEntity<Boolean> checkLogin(HttpSession session) {
        Boolean isLoggedIn = (Boolean) session.getAttribute(SessionConst.USER_ID);
        if (isLoggedIn != null && isLoggedIn) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }
    @RequestMapping(value = "/boards/disLikes" , method = RequestMethod.GET)
    public ResponseEntity<String> checkDisLikes(@RequestParam Long boardId, HttpSession session){
        String isLogin = (String) session.getAttribute(SessionConst.USER_ID);
        MemberDTO memberDTO = memberService.selectMemberDetail(isLogin);
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setBoardId(boardId);
        likeDTO.setMemberId(memberDTO.getMemberId());
        boolean alreadyDisliked = likeService.getOneLikesBoardAndMemberId(likeDTO);
        if (alreadyDisliked) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 싫어요를 눌렀습니다.");
        }
        likeDTO.setLikes(false);

        likeService.insertLikeBoard(likeDTO);
        return ResponseEntity.ok("싫어요를 하셨씁니다.");
    }
    @RequestMapping(value = "/boards/likes" , method = RequestMethod.GET)
    public ResponseEntity<String> checkLikes(@RequestParam Long boardId, HttpSession session){
        String isLogin = (String) session.getAttribute(SessionConst.USER_ID);
        MemberDTO memberDTO = memberService.selectMemberDetail(isLogin);
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setBoardId(boardId);
        likeDTO.setMemberId(memberDTO.getMemberId());
        boolean alreadyDisliked = likeService.getOneLikesBoardAndMemberId(likeDTO);
        if (alreadyDisliked) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 싫어요를 눌렀습니다.");
        }
        likeDTO.setLikes(true);
        likeService.insertLikeBoard(likeDTO);
        return ResponseEntity.ok("좋아요를 하셨습니다.");
    }
}
