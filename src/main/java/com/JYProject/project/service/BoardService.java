package com.JYProject.project.service;

import com.JYProject.project.model.Board;
import com.JYProject.project.model.dto.BoardDTO;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface BoardService {

     Long insertBoard(BoardDTO boardDTO);
     BoardDTO selectBoardDetail(Long boardId);

     int updateBoard(BoardDTO boardDTO);
     int deleteBoard(Long boardId);

     List<BoardDTO> boardAllList();

    List<BoardDTO> boardSearchAllList(String string);
    List<BoardDTO> boardSearchTitleList(String title);
    List<BoardDTO> boardSearchWriterList(String writer);
    List<BoardDTO> boardSearchContentList(String content);

     int selectBoardTotalCount();

     int getLikesTotalCount(Long boardId);

     List<BoardDTO> getMyBoardList(Long memberId);

     int boardViewCntIncrease(Long boardId);
     int getDisLikesTotalCount(Long boardId);

     List<BoardDTO>boardGetCategoryList(int categoryId);

     List<BoardDTO>getWeekBestBoardList(Map<String, Object> params);

     List<BoardDTO>getDailyBestBoardList(Map<String,Object> params);



    void incrementLikes(Long boardId);
    void incrementDislikes(Long boardId);

}
