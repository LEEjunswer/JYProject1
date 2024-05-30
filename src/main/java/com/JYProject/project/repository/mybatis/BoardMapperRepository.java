package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.Board;
import com.JYProject.project.model.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapperRepository {

    Long insertBoard(Board board);
     Board selectBoardDetail(Long boardId);
     int updateBoard(Board board);
     int deleteBoard(Long boardId);
     List<Board> boardAllList();
     int selectBoardTotalCount();

     int boardViewCntIncrease(Long boardId);

    // 밑에 리스트는 나중에 검색했을때 값 가져올 예정
     List<Board> boardSearchAllList(String string);
     List<Board> boardSearchTitleList(String title);
    List<Board> boardSearchWriterList(String writer);
     List<Board> boardSearchContentList(String content);

     int getLikesTotalCount(Long boardId);

     int getDisLikesTotalCount(Long boardId);

     List<Board> getMyBoardList(Long memberId);

     List<Board> boardGetCategoryList(int categoryId);

     List<Board> getWeekBestBoardList(Map<String, Object> params);

     List<Board> getDailyBestBoardList(Map<String, Object> params);

  void incrementLikes(Long boardId);

 void incrementDislikes(Long boardId);
}
