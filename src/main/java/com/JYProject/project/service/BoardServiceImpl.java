package com.JYProject.project.service;

import com.JYProject.project.model.Board;
import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.repository.mybatis.BoardMapperRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {

    private  final BoardMapperRepositoryImpl boardMybatisRepository;

    public BoardServiceImpl(BoardMapperRepositoryImpl boardMybatisRepository) {
        this.boardMybatisRepository = boardMybatisRepository;
    }

    @Override
    public int insertBoard(BoardDTO boardDTO) {
        Board board =convertToEntity(boardDTO);
        return boardMybatisRepository.insertBoard(board);
    }

    @Override
    public BoardDTO selectBoardDetail(Long id) {
  Board board=  boardMybatisRepository.selectBoardDetail(id);

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
    public List<BoardDTO> boardSearchTitleList(String title) {
        List<Board> boardList = boardMybatisRepository.boardSearchTitleList(title);
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
    private Board convertToEntity(BoardDTO boardDTO) {
        Board board = new Board();
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setWriter(boardDTO.getWriter());
        board.setCategoryId(boardDTO.getCategoryId());
        board.setMemberId(boardDTO.getMemberId());
        return board;
    }
    private BoardDTO convertToDTO(Board board) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setTitle(board.getTitle());
        boardDTO.setContent(board.getContent());
        boardDTO.setWriter(board.getWriter());
        boardDTO.setCategoryId(board.getCategoryId());
        boardDTO.setMemberId(board.getMemberId());
        return boardDTO;
    }
}
