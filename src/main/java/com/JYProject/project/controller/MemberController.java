package com.JYProject.project.controller;

import com.JYProject.project.model.dto.MemberDTO;
<<<<<<< HEAD
import com.JYProject.project.service.MemberService;
=======
>>>>>>> main
import com.JYProject.project.service.MemberServiceImpl;
import com.JYProject.project.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class MemberController {

    // RequestMappnig(/members) 줄 예정 계정 비활성화 및 회원탈퇴 구현예정  테이블 delete_member로 값 전환시킬예정 회원탈퇴 시키고
    private final MemberServiceImpl memberService;

    @Autowired
    public MemberController(MemberServiceImpl memberService) {
        this.memberService = memberService;
    }


<<<<<<< HEAD
    @GetMapping("/members/join")
    public String join(MemberDTO memberDTO){
        return"/members/join";
=======
    @GetMapping("/members/new")
    public String join(MemberDTO memberDTO) {
        return "/members/join";
>>>>>>> main
    }

    //일단 가입만 집어넣음
    @PostMapping("/members/join")
<<<<<<< HEAD
    public String create(@ModelAttribute MemberDTO memberDTO){
        System.out.println("member =" + memberDTO.toString());

        System.out.println("체크용");
=======
    public String create(@ModelAttribute MemberDTO memberDTO) {
>>>>>>> main
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

    @GetMapping("members/login")
    public String loginForm(){

        return  "/members/loginForm";
    }
    @PostMapping("members/login")
    public String login(@ModelAttribute MemberDTO memberDTO,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {
        MemberDTO login = memberService.login(memberDTO);

        if (login != null) {
            // 로그인이 성공하면 세션에 사용자 정보 저장
            System.out.println("login ="+ login.toString());
            session.setAttribute(SessionConst.USER_ID, login.getLoginId());
            session.setAttribute(SessionConst.USER_NAME, login.getNickname());
            return "redirect:/";
        }
            redirectAttributes.addFlashAttribute("error", "로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.");
            return "redirect:/members/login";


    }


    @GetMapping("/members/update")
<<<<<<< HEAD
    public String update(HttpSession session,Model model){
        String loginId = (String) session.getAttribute(SessionConst.USER_ID);
        model.addAttribute("loginId",loginId);
=======
    public String updateForm(HttpSession session, Model model) {
        MemberDTO log = (MemberDTO) session.getAttribute("log");
        if (log == null) {
            System.out.println("잘못된 접근");
            return "home";
        }
        model.addAttribute("log", log);
>>>>>>> main
        return "/members/update";
    }
    @PostMapping("/members/update")
<<<<<<< HEAD
    public String updateForm(@ModelAttribute MemberDTO memberDTO, Model model, RedirectAttributes redirectAttributes) {
        boolean check =  memberService.checkIdAndPw(memberDTO);
        System.out.println("boolean check="+check);
        if (!check) {
            redirectAttributes.addFlashAttribute("error", "아이디와 비밀번호가 일치하지 않습니다");
            return "redirect:/members/update";
=======
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

>>>>>>> main
        }
        // 리다이렉트할 때 GET 요청으로 보내야 함
        return "redirect:/members/updateForm?loginId=" + memberDTO.getLoginId();
    }

    @GetMapping("/members/updateForm")
    public String updateForm(@RequestParam String loginId, Model model) {
        MemberDTO member = memberService.selectMemberDetail(loginId);
        model.addAttribute("m", member);
        return "members/updateForm";
    }
    @PostMapping("/members/updateForm")
    public String updateForm(@ModelAttribute MemberDTO memberDTO,RedirectAttributes redirectAttributes) {
        System.out.println(memberDTO.toString());
        memberService.updateMember(memberDTO);
        redirectAttributes.addFlashAttribute("update", memberDTO.getLoginId() +"님 성공적으로 회원수정이 완료되었습니다");
        return "redirect:/";
    }


    @GetMapping("/members/delete")
    public String deleteForm(HttpSession session,Model model){
        String loginId = (String) session.getAttribute(SessionConst.USER_ID);
        model.addAttribute("loginId",loginId);
        return "members/deleteForm";
    }

    @PostMapping("/members/delete")
    public String delete(@ModelAttribute MemberDTO memberDTO
    ,RedirectAttributes redirectAttributes
    ,HttpSession session
    ,Model model){
        MemberDTO member = memberService.login(memberDTO);
        if(member != null){
<<<<<<< HEAD
            memberService.deleteMember(member.getMemberId());
            session.removeAttribute("loginId");
            session.removeAttribute("nickname");
          redirectAttributes.addFlashAttribute("update", member.getLoginId()+"회원탈퇴 성공하셧습니다");
            return "redirect:/";
=======
            session.removeAttribute("log");

            redirectAttributes.addFlashAttribute("suc", member.getLoginId() + "회원탈퇴 성공하셧습니다");
            return "redirect:/home";
>>>>>>> main
        }
        String loginId = memberDTO.getLoginId();
        model.addAttribute("loginId",loginId);
        redirectAttributes.addFlashAttribute("error", "아이디와 비밀번호가 일치하지 않습니다 다시 입력해주세요.");
        return "redirect:/members/deleteForm";
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
