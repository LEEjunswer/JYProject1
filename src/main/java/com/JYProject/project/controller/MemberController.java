package com.JYProject.project.controller;

import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.service.BoardService.BoardService;
import com.JYProject.project.service.DeletedService.DeletedService;
import com.JYProject.project.service.MemberService.MemberService;
import com.JYProject.project.service.ReplyService.ReplyService;
import com.JYProject.project.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    // 밑에도 역시 멤버 서비스에서 한꺼번에 해야하는데 나중에 수정할예정
    private final BoardService boardService;
    private final ReplyService replyService;
    private final DeletedService deletedService;
    @GetMapping("/members/join")
    public String join(MemberDTO memberDTO){
        return"/members/join";
    }

    //일단 가입만 집어넣음
    @PostMapping("/members/join")
    public String create(@ModelAttribute MemberDTO memberDTO){
        System.out.println("member =" + memberDTO.toString());


        int check = memberService.insertMember(memberDTO);

        return "home";

    }
    @GetMapping("/members/login")
    public String loginForm(){

        return  "/members/loginForm";
    }
    @PostMapping("members/login")
    public String login(@ModelAttribute MemberDTO memberDTO,
                        HttpSession session,
                        RedirectAttributes redirectAttributes,Model model) {
        MemberDTO login = memberService.login(memberDTO);
        if (login != null) {
            if(!login.getActive()){
            model.addAttribute("message","현재 회원님은 계정 비활성화 상태 or 회원탈퇴 유저입니다. 고객센터 문의 부탁드립니다");
                return "redirect:/";
            }

            // 어떻게  받아올지 고민중
/*            model.addAttribute("message", "10포인트를 획득했습니다!");*/
            session.setAttribute(SessionConst.USER_ID, login.getLoginId());
            session.setAttribute(SessionConst.USER_NAME, login.getNickname());
            return "redirect:/";
        }
            redirectAttributes.addFlashAttribute("error", "로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.");
            return "redirect:/members/login";


    }


    @GetMapping("/members/update")
    public String update(HttpSession session,Model model){
        String loginId = (String) session.getAttribute(SessionConst.USER_ID);
        model.addAttribute("loginId",loginId);
        return "/members/update";
    }
    @PostMapping("/members/update")
    public String updateForm(@ModelAttribute MemberDTO memberDTO, Model model, RedirectAttributes redirectAttributes) {
        boolean check =  memberService.checkIdAndPw(memberDTO);
        System.out.println("boolean check="+check);
        if (!check) {
            redirectAttributes.addFlashAttribute("error", "아이디와 비밀번호가 일치하지 않습니다");
            return "redirect:/members/update";
        }
        // 리다이렉트할 때 GET 요청으로 보내야 함
        return "redirect:/members/updateForm";
    }

    @GetMapping("/members/updateForm")
    public String updateForm(@RequestParam String loginId, Model model) {
        MemberDTO member = memberService.selectMemberDetail(loginId);
        model.addAttribute("m", member);
        return "/members/updateForm";
    }
    /*@PostMapping("/members/updateForm")
    public String updateForm(@ModelAttribute MemberDTO memberDTO,RedirectAttributes redirectAttributes) {
        System.out.println(memberDTO.toString());
        memberService.updateMember(memberDTO);
        redirectAttributes.addFlashAttribute("update", memberDTO.getLoginId() +"님 성공적으로 회원수정이 완료되었습니다");
        return "redirect:/";
    }*/


    @GetMapping("/members/delete")
    public String deleteForm(HttpSession session,Model model){
        String loginId = (String) session.getAttribute(SessionConst.USER_ID);
        model.addAttribute("loginId",loginId);
        return "/members/deleteForm";
    }
    @PostMapping("/members/delete")
    public String delete(@ModelAttribute MemberDTO memberDTO
    ,RedirectAttributes redirectAttributes
    ,HttpSession session
    ,Model model){
        MemberDTO member = memberService.login(memberDTO);
        if(member != null){
            deletedService.getDeleteMember(member);
            session.removeAttribute("loginId");
            session.removeAttribute("nickname");
          redirectAttributes.addFlashAttribute("update", member.getLoginId()+"회원탈퇴 성공하셧습니다");
            return "redirect:/";
        }
        String loginId = memberDTO.getLoginId();
        model.addAttribute("loginId",loginId);
        redirectAttributes.addFlashAttribute("error", "아이디와 비밀번호가 일치하지 않습니다 다시 입력해주세요.");
        return "redirect:/members/deleteForm";
    }
    // 좀더 수정 할 에정
    @GetMapping("/members/logout")
        public String logout(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession(false);
        if(session != null){
            session.removeAttribute(SessionConst.USER_ID);
            session.removeAttribute(SessionConst.USER_NAME);
            session.removeAttribute("profile");
            model.addAttribute("loginId", null);
            model.addAttribute("nickname", null);
            return "redirect:/";
        }

            return "redirect:/";
        }

    @GetMapping("/members/myPage")
    public String myPage(HttpSession httpSession, Model model){
        //인터셉터로 처리하게 했지만 계속 적어보기
        String isLogin = (String)httpSession.getAttribute(SessionConst.USER_ID);
        if(isLogin == null){

            return "redirect:/";
        }

        MemberDTO memberDTO = memberService.selectMemberDetail(isLogin);
        int boardCount = boardService.getMyBoardCount(memberDTO.getMemberId());
        int replyCount= replyService.getMyReplyCount(memberDTO.getMemberId());
        System.out.println("memberDTO = " + memberDTO);
        model.addAttribute("boardCount",boardCount);
        model.addAttribute("replyCount", replyCount);
        model.addAttribute("m", memberDTO);
        return "/members/myPage";
    }
}
