package com.JYProject.project.repository.BoardRepository;

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
    int getMyBoardCount(Long memberId);
    Board getBoardDetail(Long id);
     int boardViewCntIncrease(Long boardId);
    void boardDeleteFindMemberId(Long memberId);
    // 밑에 리스트는 나중에 검색했을때 값 가져올 예정
     List<Board> boardSearchAllList(String string);
     List<Board> boardSearchTitleList(String title);
    List<Board> boardSearchWriterList(String writer);
     List<Board> boardSearchContentList(String content);

     int getLikesTotalCount(Long boardId);

     int getDisLikesTotalCount(Long boardId);

     List<Board> getMyBoardList(Long memberId);

     List<Board> boardGetCategoryList(int categoryId);

     int selectBoardTotalCategoryCount(int categoryId);

     List<Board> getWeekBestBoardList(Map<String, Object> params);
    List<Board> boardGetCategoryListPaging(Map<String, Object>params);
     List<Board> getDailyBestBoardList(Map<String, Object> params);

  void incrementLikes(Long boardId);

 void incrementDislikes(Long boardId);
}
