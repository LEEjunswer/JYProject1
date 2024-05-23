package com.JYProject.project.controller.apiController;

import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberAPIController {

    private final MemberServiceImpl memberService;

    @RequestMapping(value="members/idValidCheck", method = RequestMethod.GET)
    public String idValidCheck(@RequestParam String loginId){
        MemberDTO getOneId  =  memberService.selectMemberDetail(loginId);
        if(getOneId == null){
            return "true";
        }
        return "false";
    }
    @RequestMapping(value = "members/nickValidCheck",method = RequestMethod.GET)
    public String nickValidCheck(@RequestParam String nickname ,@RequestParam String loginId){

        return "true";
    }
}
