package com.JYProject.project.controller;

import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.service.BoardServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
public class BoardController {

    // 나중에 Controller 밑에 RequestMapping으로 다 줄일 예정  + 좋아요 싫어요 아직 미구현 나중에 detail로 가져올예정 토탈로 전부 가져올 예정

    private  final BoardServiceImpl boardService;
    @Autowired
    public BoardController(BoardServiceImpl boardService){
        this.boardService=boardService;
    }
    //보드 전체 리스트 리턴 나중에 메인으로 옮길 예정
    @GetMapping("/boards/list")
    public String list(Model model){
        List<BoardDTO> list = boardService.boardAllList();
        model.addAttribute("list" , list);
        return "boards/list";
    }
    //게시판 만들기 폼
    @GetMapping("/boards/join")
    public String join(HttpSession session,
    RedirectAttributes redirectAttributes) {
        Object log = session.getAttribute("log");
        if(log ==null){
            redirectAttributes.addFlashAttribute("error","비로그인시 글을 작성할 수 없습니다");
            return "redirect:/home";
        }else{
            return"/boards/join";
        }

    }
    //보드 생성
    @PostMapping("/boards/join")
    public String create(@ModelAttribute BoardDTO boardDTO){
        int check = boardService.insertBoard(boardDTO);
        //나중에 체크 조건 줄 예정
        return "redirect:/boards/list";
    }
    //보드게시판 제목 클릭시 상세하게 보여줄 예정
    @GetMapping("boards/content/{idx}")
    public String content(@PathVariable("idx") Long idx, Model model){
        BoardDTO board = boardService.selectBoardDetail(idx);

        model.addAttribute("board" , board);
        return "/boards/content" + idx;
    }
        @GetMapping("/boards/update/{idx}")
        public String updateForm(@PathVariable("idx") Long idx,  Model model
        , HttpSession session,
        RedirectAttributes redirectAttributes){
            MemberDTO log = (MemberDTO)session.getAttribute("log");
            if(log == null){
                redirectAttributes.addFlashAttribute("error", "비상적인 접근입니다 로그인부터 해주세요");
                return "home";
            }else{
                BoardDTO board = boardService.selectBoardDetail(idx);
                model.addAttribute("board" , board);
             return "boards/update";
            }
        }
        @PostMapping("/boards/update")
        public String update(@ModelAttribute BoardDTO boardDTO){
            Long idx = boardDTO.getId();
            int updateCheck = boardService.updateBoard(boardDTO);

            return "redirect:/boards/" + idx;
        }
    @PostMapping("/boards/delete/{idx}")
    public String delete(@PathVariable("idx") Long idx, HttpSession session,
                         RedirectAttributes redirectAttributes){
    MemberDTO log = (MemberDTO)session.getAttribute("log");
    if(log == null){
        redirectAttributes.addFlashAttribute("error", "잘못된 접근입니다");
        return "home";
    }else{
        int check =  boardService.deleteBoard(idx);
        return "redirect:/boards/list";
    }

    }

}
