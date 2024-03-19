package com.JYProject.project.service;

import com.JYProject.project.model.dto.BoardDTO;

import java.util.List;

public interface BoardSerivce {

    public int insertBoard(BoardDTO boardDTO);
    public BoardDTO selectBoardDetail(Long id);

    public int updateBoard(BoardDTO boardDTO);
    public int deleteBoard(Long id);

    public List<BoardDTO> selectBoardList();


    public int selectBoardTotalCount();

    public int getLikesTotalCount(Long id);

    public int getDisLikesTotalCount(Long id);
}
