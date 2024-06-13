package com.JYProject.project.service.BoardService;

import com.JYProject.project.model.Board;
import com.JYProject.project.model.Category;
import com.JYProject.project.model.Member;
import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.model.dto.CategoryDTO;
import com.JYProject.project.model.dto.FileDTO;
import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.repository.BoardRepository.BoardMapperRepository;
import com.JYProject.project.service.FileService.FileService;
import com.JYProject.project.service.MemberService.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapperRepository boardMybatisRepository;
    private final FileService fileService;
    private final MemberService memberService;


    @Override
    public Long insertBoard(BoardDTO boardDTO, List<String> fileUrls) throws Exception {
        String contentChangeImgPath= boardDTO.getContent().replace("../uploads/", "http://localhost:8082/uploads/");
        boardDTO.setContent(contentChangeImgPath);
        Board board = convertToEntity(boardDTO);
         boardMybatisRepository.insertBoard(board);
        FileDTO fileDTO = new FileDTO();
        fileDTO.setBoardId(board.getBoardId());
        fileDTO.setFileNameFromList(fileUrls);
        fileDTO.setRegDate(LocalDateTime.now());
        fileService.insertFile(fileDTO);
        return board.getBoardId() ;
    }

    @Override
    public BoardDTO selectBoardDetail(Long id) {
  Board board=  boardMybatisRepository.selectBoardDetail(id);
  return convertToDTO(board) ;
    }

    @Override
    public BoardDTO handleBoardView(Long boardId, String loginId, String nickName) {
        Board board=  boardMybatisRepository.selectBoardDetail(boardId);
        if (loginId != null || !board.getMemberInfo().getNickname().equals(nickName)) {
            boardMybatisRepository.boardViewCntIncrease(boardId);

        }
        return convertToDTO(board);
    }

    @Override
    public int updateBoard(BoardDTO boardDTO) {
        Board board = convertToEntity(boardDTO);
        return boardMybatisRepository.updateBoard(board);
    }

    @Override
    public int deleteBoard(Long id) {
        return boardMybatisRepository.deleteBoard(id);
    }

    @Override
    public List<BoardDTO> boardAllList() {
            List<Board>  boardList =  boardMybatisRepository.boardAllList();
        return boardList.stream().map(this :: convertToDTO).collect(Collectors.toList());
    }

    @Override
    public int getMyBoardCount(Long memberId) {
        return boardMybatisRepository.getMyBoardCount(memberId);
    }

    @Override
    public void boardDeleteFindMemberId(Long memberId) {
        boardMybatisRepository.boardDeleteFindMemberId(memberId);
    }

    @Override
    public BoardDTO getBoardDetail(Long id) {
        return convertToDTO(boardMybatisRepository.getBoardDetail(id));
    }

    @Override
    public List<BoardDTO> boardSearchAllList(String string) {
        List<Board> boardList = boardMybatisRepository.boardSearchAllList(string);
        return boardList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<BoardDTO> boardSearchTitleList(String title) {
        List<Board> boardList = boardMybatisRepository.boardSearchTitleList(title);
        return boardList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<BoardDTO> boardSearchWriterList(String writer) {
        List<Board> boardList = boardMybatisRepository.boardSearchWriterList(writer);
        return boardList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<BoardDTO> boardSearchContentList(String content) {
        List<Board> boardList=boardMybatisRepository.boardSearchContentList(content);
        return boardList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

        @Override
        public List<Integer> getUsersBoardCount(List<MemberDTO> memberDTO) {
          if(memberDTO == null){
              return null;
          }
            List<Long> memberIdList = memberDTO.stream()
                    .map(MemberDTO::getMemberId)
                    .toList();
            List<Integer> boardCountList = new ArrayList<>();
            for (Long memberId : memberIdList) {
                Integer boardCount = boardMybatisRepository.getMyBoardCount(memberId);
                boardCountList.add(boardCount);
            }
            return boardCountList;
        }

    @Override
    public int selectBoardTotalCount() {
        return boardMybatisRepository.selectBoardTotalCount();
    }

    @Override
    public int getLikesTotalCount(Long id) {
        return boardMybatisRepository.getLikesTotalCount(id);
    }

    @Override
    public List<BoardDTO> getMyBoardList(Long memberId) {
        return boardMybatisRepository.getMyBoardList(memberId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public int boardViewCntIncrease(Long boardId) {
        return boardMybatisRepository.boardViewCntIncrease(boardId);
    }

    @Override
    public int getDisLikesTotalCount(Long id) {
        return boardMybatisRepository.getDisLikesTotalCount(id);
    }

    @Override
    public List<BoardDTO> boardGetCategoryList(int categoryId) {
    List<Board>  boardList=   boardMybatisRepository.boardGetCategoryList(categoryId);

       return boardList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<BoardDTO> getWeekBestBoardList(Map<String, Object> params) {
        List<Board> boardList = boardMybatisRepository.getWeekBestBoardList(params);

        return  boardList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<BoardDTO> getDailyBestBoardList(Map<String, Object> params) {
        List<Board> boardList = boardMybatisRepository.getDailyBestBoardList(params);
        return boardList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void incrementLikes(Long boardId) {
        boardMybatisRepository.incrementLikes(boardId);
    }

    @Override
    public void incrementDislikes(Long boardId) {
    boardMybatisRepository.incrementDislikes(boardId);
    }

    private Board convertToEntity(BoardDTO boardDTO) {
        Board board = new Board();
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setBoardId(boardDTO.getBoardId());
        board.setRegDate(boardDTO.getRegDate());
        board.setDeleteDate(boardDTO.getDeleteDate());
        board.setCategoryId(boardDTO.getCategoryId());
        board.setDislikes(boardDTO.getDislikes());
        board.setLikes(boardDTO.getLikes());
        board.setFileId(boardDTO.getFileId());
        board.setViewCnt(boardDTO.getViewCnt());
        board.setMemberId(boardDTO.getMemberId());
        board.setMemberInfo(convertToMemberEntity(boardDTO.getMemberInfo()));
        board.setCategoryInfo(convertToCategoryEntity(boardDTO.getCategoryInfo()));
        return board;
    }

    private BoardDTO convertToDTO(Board board) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle(board.getTitle());
        boardDTO.setContent(board.getContent());
        boardDTO.setDeleteDate(board.getDeleteDate());
        boardDTO.setRegDate(board.getRegDate());
        boardDTO.setBoardId(board.getBoardId());
        boardDTO.setLikes(board.getLikes());
        boardDTO.setDislikes(board.getDislikes());
        boardDTO.setCategoryId(board.getCategoryId());
        boardDTO.setMemberId(board.getMemberId());
        boardDTO.setViewCnt(board.getViewCnt());
        boardDTO.setFileId(board.getFileId());
        boardDTO.setMemberInfo(convertToMemberDTO(board.getMemberInfo()));
        boardDTO.setCategoryInfo(convertToCategoryDTO(board.getCategoryInfo()));
        return boardDTO;
    }
        private Member convertToMemberEntity(MemberDTO memberDTO){
            if(memberDTO == null){
                return null;
            }
            Member member = new Member();
            member.setMemberId(memberDTO.getMemberId());
            member.setLoginId(memberDTO.getLoginId());
            member.setPw(memberDTO.getPw());
            member.setName(memberDTO.getName());
            member.setActive(memberDTO.getActive());
            member.setEmail(memberDTO.getEmail());
            member.setProfileImg(memberDTO.getProfileImg());
            member.setLastLoginDate(memberDTO.getLastLoginDate());
            member.setNickname(memberDTO.getNickname());
            member.setRegDate(memberDTO.getRegDate());
            member.setAddressDetail(memberDTO.getAddressDetail());
            member.setPhone(memberDTO.getPhone());
            member.setGrade(memberDTO.getGrade());
            member.setPoint(memberDTO.getPoint());
            return  member;
        }
        private MemberDTO convertToMemberDTO(Member member){
            if(member == null){
                 return  null;
            }
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setMemberId(member.getMemberId());
            memberDTO.setLoginId(member.getLoginId());
            memberDTO.setPw(member.getPw());
            memberDTO.setActive(member.getActive());
            memberDTO.setName(member.getName());
            memberDTO.setEmail(member.getEmail());
            memberDTO.setLastLoginDate(member.getLastLoginDate());
            memberDTO.setProfileImg(member.getProfileImg());
            memberDTO.setNickname(member.getNickname());
            memberDTO.setRegDate(member.getRegDate());
            memberDTO.setAddressDetail(member.getAddressDetail());
            memberDTO.setPhone(member.getPhone());
            memberDTO.setGrade(member.getGrade());
            memberDTO.setPoint(member.getPoint());
            return memberDTO;
        }
    private Category convertToCategoryEntity(CategoryDTO categoryDTO){
        if(categoryDTO == null){
            return  null;
        }
        Category category = new Category();
        category.setCategoryId(categoryDTO.getCategoryId());
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setRegDate(categoryDTO.getRegDate());
        return  category;
    }
    private CategoryDTO convertToCategoryDTO(Category category){
            if(category == null ){
                return  null;
            }
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(category.getCategoryId());
        categoryDTO.setCategoryName(category.getCategoryName());
        categoryDTO.setRegDate(category.getRegDate());
        return  categoryDTO;
    }
}
