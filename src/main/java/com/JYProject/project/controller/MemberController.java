package com.JYProject.project.controller;

import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.service.MemberServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MemberController {

    // RequestMappnig(/members) 줄 예정 계정 비활성화 및 회원탈퇴 구현예정  테이블 delete_member로 값 전환시킬예정 회원탈퇴 시키고
    private final MemberServiceImpl memberService;

    @Autowired
    public MemberController(MemberServiceImpl memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/members/new")
    public String join(MemberDTO memberDTO) {
        return "/members/join";
    }

    //일단 가입만 집어넣음
    @PostMapping("/members/join")
    public String create(@ModelAttribute MemberDTO memberDTO) {
        int check = memberService.insertMember(memberDTO);
        return "home";

    }

    //모든 회원리스트 가져가기
    @GetMapping("/members/list")
    public String list(Model model) {
        List<MemberDTO> list = memberService.MemberAllList();
        model.addAttribute("list", list);
        return "/members/list";
    }


    public String login(@ModelAttribute MemberDTO memberDTO,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {
        // 로그인 로직
        MemberDTO login = memberService.login(memberDTO);

        if (login != null) {
            // 로그인이 성공하면 세션에 사용자 정보 저장
            session.setAttribute("log", login);
            return "redirect:/home";
        } else {
            redirectAttributes.addFlashAttribute("error", "로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.");
            return "redirect:/members/loginForm";
        }
    }


    @GetMapping("/members/update")
    public String updateForm(HttpSession session, Model model) {
        MemberDTO log = (MemberDTO) session.getAttribute("log");
        if (log == null) {
            System.out.println("잘못된 접근");
            return "home";
        }
        model.addAttribute("log", log);
        return "/members/update";
    }

    @PostMapping("/members/update")
    public String update(@ModelAttribute MemberDTO memberDTO){
        memberService.updateMember(memberDTO);
        System.out.println("나중에 변경할것 ");
        return "home";
    }

    @GetMapping("/members/delete")
    public String deleteForm(@ModelAttribute MemberDTO memberDTO) {
        if (memberDTO.getLoginId() == null) {
            System.out.println("잘못된 접근입니다");
            return "home";

        }
        return "deleteForm";
    }

    @PostMapping("/members/delete")
    public String delete(@ModelAttribute MemberDTO memberDTO
    ,RedirectAttributes redirectAttributes
    ,HttpSession session){
        MemberDTO member = memberService.login(memberDTO);
        if(member != null){
            session.removeAttribute("log");

            redirectAttributes.addFlashAttribute("suc", member.getLoginId() + "회원탈퇴 성공하셧습니다");
            return "redirect:/home";
        }
        redirectAttributes.addFlashAttribute("error", "아이디와 비밀번호가 일치하지 않습니다 다시 입력해주세요.");
        return "redirect:/members/delete";
    }


    //나중에 회원가입에서 ajax로 받을예정
    @PostMapping("/validCheck")
    @ResponseBody
    public String validIdCheck(@RequestParam String loginId){
    boolean check = !memberService.validCheckId(loginId);
        if(check){
        return "valid";
        }else{
        return "invalid";
        }
    }

    //회원탈퇴시 로그인한 아이디와 비밀번호 입력한 값이 같을시에 ajax로 받이서 삭제 예정
    @PostMapping("/validCheckPassword")
    @ResponseBody

    public String validPwCheck(@ModelAttribute MemberDTO memberDTO){
       boolean check = !memberService.checkIdAndPw(memberDTO);
        if(check){
            return"valid";
        }else{
            return"invalid";
        }
    }
}
