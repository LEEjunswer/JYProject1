package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.Board;
import com.JYProject.project.model.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapperRepository {

    public int insertBoard(Board board);
    public Board selectBoardDetail(Long boardId);
    public int updateBoard(Board board);
    public int deleteBoard(Long boardId);
    public List<Board> boardAllList();
    public int selectBoardTotalCount();

    public int boardViewCntIncrease(Long boardId);

    // 밑에 리스트는 나중에 검색했을때 값 가져올 예정
    public List<Board> boardSearchTitleList(String title);

    public List<Board> boardSearchContentList(String content);
    public int getLikesTotalCount(Long boardId);

    public int getDisLikesTotalCount(Long boardId);

    public List<Board> boardGetCategoryList(int categoryId);

    public List<Board> getWeekBestBoardList(Map<String, Object> params);

    public List<Board> getDailyBestBoardList(Map<String, Object> params);

}
