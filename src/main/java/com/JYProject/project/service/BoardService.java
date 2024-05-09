package com.JYProject.project.service;

import com.JYProject.project.model.Board;
import com.JYProject.project.model.dto.BoardDTO;

import java.util.List;

public interface BoardService {

    public int insertBoard(BoardDTO boardDTO);
    public BoardDTO selectBoardDetail(Long boardId);

    public int updateBoard(BoardDTO boardDTO);
    public int deleteBoard(Long boardId);

    public List<BoardDTO> boardAllList();

    public List<BoardDTO> boardSearchTitleList(String title);

    public List<BoardDTO> boardSearchContentList(String content);
    public int selectBoardTotalCount();

    public int getLikesTotalCount(Long boardId);

    public int boardViewCntIncrease(Long boardId);
    public int getDisLikesTotalCount(Long boardId);

    public List<BoardDTO>boardGetCategoryList(int categoryId);
}
