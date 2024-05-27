package com.JYProject.project.controller;

import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.model.dto.FileDTO;
import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.service.BoardServiceImpl;
import com.JYProject.project.service.FileServiceImpl;
import com.JYProject.project.service.MemberServiceImpl;
import com.JYProject.project.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private  final BoardServiceImpl boardService;
    private final MemberServiceImpl memberService;
    private final FileServiceImpl fileService;



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
        if(loginId == null){
            model.addAttribute("updateMessage", "로그인 후에 글쓰기가 가능합니다.");
            return "redirect:/boards/list";
        }
        MemberDTO  m =  memberService.selectMemberDetail(loginId);
        model.addAttribute("nickname",m.getNickname());
        model.addAttribute("memberId",m.getMemberId());
        return"/boards/join";
    }

    @GetMapping("/category/{category}")
    public String choiceCategory(@PathVariable("category") int categoryId,Model model){
        System.out.println(categoryId);
      List<BoardDTO> list =  boardService.boardGetCategoryList(categoryId);
        model.addAttribute("boardList", list);
        return "/boards/list";
    }

    @PostMapping("/boards/join")
    public String create(
            @ModelAttribute BoardDTO boardDTO,
            @RequestParam(value = "fileUrls") List<String> fileUrls,
            RedirectAttributes redirectAttributes) {
        System.out.println("boardDTO = " + boardDTO);
        System.out.println("Received fileUrls: " + fileUrls);

        try {
            String contentChangeImgPath= boardDTO.getContent().replace("../uploads/", "http://localhost:8082/uploads/");
            boardDTO.setContent(contentChangeImgPath);
            Long getBoardId =  boardService.insertBoard(boardDTO);

            System.out.println(getBoardId + " getBoardId");
                FileDTO fileDTO = new FileDTO();
                fileDTO.setBoardId(getBoardId);
                fileDTO.setFileNameFromList(fileUrls);
                fileDTO.setRegDate(LocalDateTime.now());
                fileService.insertFile(fileDTO);


            redirectAttributes.addFlashAttribute("suc", "성공적으로 게시글 등록되었습니다");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "게시글 등록에 실패했습니다.");
        }
        return "redirect:/boards/list";
    }


//보드게시판 제목 클릭시 상세하게 보여줄 예정
@GetMapping("boards/content/{boardId}")
public String content(@PathVariable("boardId") Long boardId ,Model model,HttpSession session){
   BoardDTO board = boardService.selectBoardDetail(boardId);
   String userId = (String) session.getAttribute(SessionConst.USER_NAME);
   String nickName = (String) session.getAttribute(SessionConst.USER_NAME);

    if (userId != null || !board.getWriter().equals(nickName)) {

        boardService.boardViewCntIncrease(boardId);
        model.addAttribute("board" , board);
        return "boards/content";
    }
  model.addAttribute("board" , board);

    return "boards/content";
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
    @GetMapping("/boards/myBoard/{loginId}")
    public String getMyBoardList(@PathVariable("loginId")String loginId,Model model ){
    if(loginId == null){

        return "home";
    }

    MemberDTO memberDTO = memberService.selectMemberDetail(loginId);
    List<BoardDTO>  myBoardList  =  boardService.getMyBoardList(memberDTO.getMemberId());
    model.addAttribute("myBoards" , myBoardList);
    return "boards/myBoardList";
    }

    @PostMapping("/boards/search/{query}")
    public String searchTitle(@PathVariable("query") String query, Model model){
        System.out.println("query = " + query);
        List<BoardDTO> boardSearchTitleList = boardService.boardSearchTitleList(query);
        model.addAttribute("boardList", boardSearchTitleList);
        return "boards/list";
    }
  /*  @GetMapping("/boards/search/{detail}/{/search}")
    public String searchDetails(@PathVariable("search") String search,@PathVariable("detail") String detail,Model model ){
        List<BoardDTO> boardList = new ArrayList<>();
        if(detail.equals("0")){
        boardList = boardService.boardSearchAllList(search);
        }else if(detail.equals("1")){
            boardList = boardService.boardSearchTitleList(search);
        }else if(detail.equals("2")){
            boardList = boardService.boardSearchContentList(search);
        }else{
            boardList =boardService.boardSearchWriterList(search);
        }

        model.addAttribute("boardList",boardList);
        return "boards/list";
    }*/
}
