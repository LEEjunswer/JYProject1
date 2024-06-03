package com.JYProject.project.service;

import com.JYProject.project.model.Board;
import com.JYProject.project.model.Member;
import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.repository.mybatis.BoardMapperRepositoryImpl;
import com.JYProject.project.repository.mybatis.MemberMapperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class BoardServiceImpl implements BoardService {

    private  final BoardMapperRepositoryImpl boardMybatisRepository;

    @Override
    public Long insertBoard(BoardDTO boardDTO) {

        Board board =convertToEntity(boardDTO);
        boardMybatisRepository.insertBoard(board);
        return board.getBoardId();
    }

    @Override
    public BoardDTO selectBoardDetail(Long id) {
  Board board=  boardMybatisRepository.selectBoardDetail(id);
        System.out.println("board = " + board);
  return convertToDTO(board) ;
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

    }

    @Override
    public void incrementDislikes(Long boardId) {

    }

    private Board convertToEntity(BoardDTO boardDTO) {
        Board board = new Board();
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setBoardId(boardDTO.getBoardId());
        board.setRegDate(boardDTO.getRegDate());
        board.setDeletedDate(boardDTO.getDeletedDate());
        board.setCategoryId(boardDTO.getCategoryId());
        board.setDislikes(boardDTO.getDislikes());
        board.setLikes(boardDTO.getLikes());
        board.setFileId(boardDTO.getFileId());
        board.setDeletedDate(board.getDeletedDate());
        board.setFileId(boardDTO.getFileId());
        board.setCategoryId(boardDTO.getCategoryId());
        board.setMemberId(boardDTO.getMemberId());
        board.setMemberInfo(convertToMemberEntity(boardDTO.getMemberInfo()));
        return board;
    }

    private BoardDTO convertToDTO(Board board) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle(board.getTitle());
        boardDTO.setContent(board.getContent());
        boardDTO.setDeletedDate(board.getDeletedDate());
        boardDTO.setRegDate(board.getRegDate());
        boardDTO.setBoardId(board.getBoardId());
        boardDTO.setLikes(board.getLikes());
        boardDTO.setDislikes(board.getDislikes());
        boardDTO.setCategoryId(board.getCategoryId());
        boardDTO.setMemberId(board.getMemberId());
        boardDTO.setFileId(board.getFileId());
        boardDTO.setMemberInfo( convertToMemberDTO(board.getMemberInfo()));
        return boardDTO;
    }
        private Member convertToMemberEntity(MemberDTO memberDTO){
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
}
