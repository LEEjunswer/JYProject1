package com.JYProject.project.service;

import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.repository.mybatis.BoardMybatisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private  final BoardMybatisRepository boardMybatisRepository;

    public BoardServiceImpl(BoardMybatisRepository boardMybatisRepository) {
        this.boardMybatisRepository = boardMybatisRepository;
    }

    @Override
    public int insertBoard(BoardDTO boardDTO) {

        return boardMybatisRepository.insertBoard(boardDTO);
    }

    @Override
    public BoardDTO selectBoardDetail(Long id) {
        return boardMybatisRepository.selectBoardDetail(id);
    }

    @Override
    public int updateBoard(BoardDTO boardDTO) {
        return boardMybatisRepository.updateBoard(boardDTO);
    }

    @Override
    public int deleteBoard(Long id) {
        return boardMybatisRepository.deleteBoard(id);
    }

    @Override
    public List<BoardDTO> boardAllList() {
        return boardMybatisRepository.boardAllList();
    }

    @Override
    public List<BoardDTO> boardSearchTitleList(String title) {
        return boardMybatisRepository.boardSearchTitleList(title);
    }

    @Override
    public List<BoardDTO> boardSearchContentList(String content) {
        return boardMybatisRepository.boardSearchContentList(content);
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
    public int getDisLikesTotalCount(Long id) {
        return boardMybatisRepository.getDisLikesTotalCount(id);
    }
}
