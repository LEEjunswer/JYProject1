package com.JYProject.project.controller;

import com.JYProject.project.model.dto.*;
import com.JYProject.project.service.AdminService.AdminBoardService;
import com.JYProject.project.service.BoardService.BoardService;
import com.JYProject.project.service.FileService.FileService;
import com.JYProject.project.service.MemberService.MemberService;
import com.JYProject.project.service.ReplyService.ReplyService;
import com.JYProject.project.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    // boardService말고는 전부 Serivce에서 처리하자 ;;  나중에 한꺼번에 공부하고 수정할 예정
    private  final BoardService boardService;
    private final MemberService memberService;
    private final ReplyService replyService;
    /*나중에 이름을 바꾸던가 해야겟다*/
    private final AdminBoardService adminBoardService;


    @GetMapping("/boards/list")
    public String list(Model model){
        List<BoardDTO> list = boardService.boardAllList();
        model.addAttribute("list" , list);
        return "boards/list";
    }

    @GetMapping("/boards/join")
    public String join(HttpSession session, Model model) {
        String loginId = (String) session.getAttribute(SessionConst.USER_ID);
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
    
    /*공지사항 및 이벤트 보드리스트 유저분들에게 보여주기*/
    @GetMapping("/boards/noticeBoard/{category}")
    public String noticeBoard(@PathVariable("category")String category,Model model){
        if(category == null){
    List<AdminBoardDTO>  noticeList =  adminBoardService.getAllNoticeList();
        model.addAttribute("noticeList",noticeList);
    return "/boards/noticeBoard";
        }
        int changeCategory= Integer.parseInt(category);
        List<AdminBoardDTO> noticeList = adminBoardService.getCategoryList(changeCategory);
        model.addAttribute("noticeList",noticeList);
       return "/boards/noticeBoard";
    }

    /*공지사항이나 이베트 content 나중에 수정하거나 뺄 예정*/
    @GetMapping("/boards/notice/content/{id}")
    public String noticeContent(@PathVariable("id")Long id,Model model){
      AdminBoardDTO adminBoardDTO=  adminBoardService.getOneNoticeDetail(id);
        model.addAttribute("notice", adminBoardDTO);
        return "/boards/noticeContent";
    }
    @PostMapping("/boards/join")
    public String create(
            @ModelAttribute BoardDTO boardDTO,
            @RequestParam(value = "fileUrls") List<String> fileUrls,
            RedirectAttributes redirectAttributes) {

        try {
            int getBoardId = boardService.insertBoard(boardDTO, fileUrls);
            redirectAttributes.addFlashAttribute("suc", "성공적으로 게시글 등록되었습니다");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "게시글 등록에 실패했습니다.");
        }
        return "redirect:/boards/list";
    }


//보드게시판 제목 클릭시 상세하게 보여줄 예정
@GetMapping("/boards/content/{boardId}")
public String content(@PathVariable("boardId") Long boardId ,Model model,HttpSession session){

   ReplyResponseDTO replyDTOList = replyService.getOneBoardReplyPaging(boardId,1,10);
   String userId = (String) session.getAttribute(SessionConst.USER_ID);
   String nickName = (String) session.getAttribute(SessionConst.USER_NAME);
    BoardDTO board = boardService.handleBoardView(boardId,userId,nickName);
   model.addAttribute("repliesMember", replyDTOList.getMemberList());
    model.addAttribute("replies",replyDTOList);
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
@GetMapping("/boards/myBoardList/{memberId}")
public String myBoardList(@PathVariable("memberId")Long memberId ,Model model){

       List<BoardDTO> boardList = boardService.getMyBoardList(memberId);
    System.out.println("boardList = " + boardList);
        model.addAttribute("myBoards", boardList);
        return "/boards/myBoardList";
}
    @PostMapping("/boards/search/{query}")
    public String searchTitle(@PathVariable("query") String query, Model model){
        System.out.println("query = " + query);
        List<BoardDTO> boardSearchTitleList = boardService.boardSearchTitleList(query);
        model.addAttribute("boardList", boardSearchTitleList);
        return "/boards/list";
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
