package com.JYProject.project.controller;

import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.model.dto.FileDTO;
import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.service.BoardServiceImpl;
import com.JYProject.project.service.MemberService;
import com.JYProject.project.service.MemberServiceImpl;
import com.JYProject.project.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BoardController {

    private  final BoardServiceImpl boardService;
    private final MemberServiceImpl memberService;
    @Autowired
    public  BoardController(BoardServiceImpl boardService, MemberServiceImpl memberService){
        this.boardService=boardService;
        this.memberService = memberService;
    }


    @GetMapping("/boards/list")
    public String list(Model model){
        List<BoardDTO> list = boardService.boardAllList();
        model.addAttribute("list" , list);
        return "boards/list";
    }
    //게시판 만들기 폼
    @GetMapping("/boards/join")
    public String join(HttpSession session,
                       RedirectAttributes redirectAttributes,Model model) {

        /*    redirectAttributes.addFlashAttribute("error","비로그인시 글을 작성할 수 없습니다");
            return "redirect:/home";*/

        String loginId = (String) session.getAttribute(SessionConst.USER_ID);
        MemberDTO  m =  memberService.selectMemberDetail(loginId);
        model.addAttribute("nickname",m.getNickname());
        model.addAttribute("memberId",m.getMemberId());
        return"/boards/join";
    }
    //보드 생성
@PostMapping("/boards/join")
public String create(
        @ModelAttribute BoardDTO boardDTO,RedirectAttributes redirectAttributes){
    boardService.insertBoard(boardDTO);
    redirectAttributes.addFlashAttribute("suc","성공적으로 게시글 등록되었습니다");
    return "redirect:/boards/list";
}
//보드게시판 제목 클릭시 상세하게 보여줄 예정
@GetMapping("boards/content/{boardId}")
public String content(@PathVariable("boardId") Long boardId ,Model model,HttpSession session){
   BoardDTO board = boardService.selectBoardDetail(boardId);
   String userId = (String) session.getAttribute(SessionConst.USER_ID);

    if (userId != null && !userId.equals(board.getMemberId())) {
        boardService.boardViewCntIncrease(boardId);
        model.addAttribute("board" , board);
        return "/boards/content";
    }
  model.addAttribute("board" , board);

    return "/boards/content";
}

@GetMapping("/boards/update/{boardId}")
public String updateForm(@PathVariable("boardId") Long boardId,  Model model
        , HttpSession session,
                         RedirectAttributes redirectAttributes){
    MemberDTO log = (MemberDTO)session.getAttribute("log");
    if(log == null){
        redirectAttributes.addFlashAttribute("error", "비상적인 접근입니다 로그인부터 해주세요");
        return "home";
    }else{
        BoardDTO board = boardService.selectBoardDetail(boardId);
        model.addAttribute("board" , board);
        return "boards/update";
    }
}
@PostMapping("/boards/update")
public String update(@ModelAttribute BoardDTO boardDTO){

    int updateCheck = boardService.updateBoard(boardDTO);

    return "redirect:/boards/" ;
}
@PostMapping("/boards/delete/{boardId}")
public String delete(@PathVariable("boardId") Long boardId, HttpSession session,
                     RedirectAttributes redirectAttributes){
    MemberDTO log = (MemberDTO)session.getAttribute("log");
    if(log == null){
        redirectAttributes.addFlashAttribute("error", "잘못된 접근입니다");
        return "home";
    }else{
        int check =  boardService.deleteBoard(boardId);
        return "redirect:/boards/list";
    }

}




}