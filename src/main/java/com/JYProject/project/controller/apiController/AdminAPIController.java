package com.JYProject.project.controller.apiController;

import com.JYProject.project.model.dto.ReplyDTO;
import com.JYProject.project.service.AdminService.AdminBoardService;
import com.JYProject.project.service.AdminService.AdminEventService;
import com.JYProject.project.service.MemberService.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AdminAPIController {
    private final AdminBoardService adminBoardService;
    private final AdminEventService adminEventService;
    private final MemberService memberService;
    @PostMapping("/admin/active")
    public ResponseEntity<Map<String,String>> activeChange(@RequestParam("active") String active , @RequestParam("memberId") Long memberId){
        Map<String,String> response = new HashMap<>();
        int check ;
        if(active.equals("1")){
            /*정상*/
            check = memberService.updateMemberActive(memberId,true);
        }else{
            /*비정상*/
            check = memberService.updateMemberActive(memberId,false);
        }
        if(check == 1){
            response.put("msg","정상적으로 회원상태가 변경되었습니다");
            return ResponseEntity.ok(response);
        }
        response.put("msg","서버 오류.");
        return ResponseEntity.ok(response);
    }

}
