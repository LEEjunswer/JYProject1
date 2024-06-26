package com.JYProject.project.controller.apiController;

import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.model.dto.ReplyDTO;
import com.JYProject.project.model.dto.ReplyResponseDTO;
import com.JYProject.project.service.FilterService.FilterService;
import com.JYProject.project.service.MemberService.MemberService;
import com.JYProject.project.service.MemberService.MemberServiceImpl;
import com.JYProject.project.service.ReplyService.ReplyService;
import com.JYProject.project.service.ReplyService.ReplyServiceImpl;
import com.JYProject.project.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ReplyAPIController {

    private final ReplyService replyService;
    private final MemberService memberService;
    private final FilterService filterService;


    //댓글작성 페이징
    @PostMapping(value="/review/content/paging")
    public ResponseEntity<ReplyResponseDTO> pagingBoardReview(@RequestParam Long boardId, @RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "10")int  size){
        ReplyResponseDTO response = replyService.getOneBoardReplyPaging(boardId, page, size);
        System.out.println("response = " + response);
        return ResponseEntity.ok().body(response);
    }

    //댓글작성
    @PostMapping("/boards/content/{idx}/reply")
    public ResponseEntity<Map<String,String>> create(@PathVariable("idx") Long idx, HttpSession session,@RequestBody ReplyDTO replyDTO){
        Map<String, String> response = new HashMap<>();
        String isLogin = (String) session.getAttribute(SessionConst.USER_ID);
       if(isLogin == null){
           response.put("message","로그인 이후 댓글 등록이 가능합니다");
           return ResponseEntity.ok(response);
       }
        List<String> filters = filterService.getAllWord();
        for (String word : filters) {
            if (replyDTO.getContent().contains(word)) {
                response.put("message", "게시글에 차단된 단어가 포함되어 있습니다: " + word);
                return ResponseEntity.ok(response);
            }
        }


        MemberDTO m = memberService.selectMemberDetail(isLogin);
        ReplyDTO replyDTO1 = new ReplyDTO();
        replyDTO1.setBoardId(idx);
        replyDTO1.setMemberId(m.getMemberId());
        replyDTO1.setContent(replyDTO.getContent());
        System.out.println("replyDTO1 = " + replyDTO1);
        int check = replyService.insertReply(replyDTO1);

        if (check > 0) {
            response.put("message","댓글이 정상적으로 달렸습니다");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "댓글 달기 실패");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @PostMapping("/review/comment/delete")
    public  ResponseEntity<Map<String,String>> deleteReply(HttpSession session,@RequestBody ReplyDTO replyDTO){
        Map<String, String> response = new HashMap<>();

       
         String isLogin = (String) session.getAttribute(SessionConst.USER_ID);
         if(isLogin == null){
             response.put("message","비정상적인 접근입니다.");
             return ResponseEntity.ok(response);
         }

       int  check =  replyService.deleteReply(replyDTO.getReplyId());
        if( check> 0){
            response.put("message", "댓글이 정상적으로 삭제되었습니다.");
            return  ResponseEntity.ok(response);
        }
        response.put("message", "댓글 삭제 오류.");
        return ResponseEntity.ok(response);
    }
    @PostMapping("/review/comment/update")
    public  ResponseEntity<Map<String,String>> updateReview(HttpSession session,@RequestBody ReplyDTO replyDTO){

        Map<String, String> response = new HashMap<>();
        String isLogin = (String) session.getAttribute(SessionConst.USER_ID);
        
        if(isLogin == null){
            response.put("message","비정상적인 접근입니다.");
            return ResponseEntity.ok(response);
        }

        int  check =  replyService.updateReply(replyDTO);
        System.out.println("check = " + check);
        if( check> 0){
            response.put("message", "댓글이 정상적으로 수정되었습니다.");
            return  ResponseEntity.ok(response);
        }
        response.put("message", "댓글 수정 오류.");
        return ResponseEntity.ok(response);
    }
}
