package com.JYProject.project.controller.apiController;

import com.JYProject.project.model.dto.AdopterDTO;
import com.JYProject.project.model.dto.ReplyDTO;
import com.JYProject.project.service.AdopterSerivce.AdopterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AdopterAPIController {
        private final AdopterService adopterService;

    @PostMapping("/adopter/choose")
    public ResponseEntity<Map<String,String>> choose(@RequestBody AdopterDTO adopterDTO){
        System.out.println("adopterDTO = " + adopterDTO);
        Map<String, String> response = new HashMap<>();
        int  check =adopterService.insertAdopt(adopterDTO.getReplyId(), adopterDTO.getQuestionId());
    if(check ==1){
        response.put("suc","성공적으로 채택이 완료되었습니다.");
    return  ResponseEntity.ok(response);
    }
    response.put("fail","채택이 실패했습니다 잠시 후 다시 이용해주세요");
    return ResponseEntity.ok(response);
    }
}
