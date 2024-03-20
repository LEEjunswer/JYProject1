package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.model.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BoardRepository {

    public int insertBoard(BoardDTO boardDTO);
    public BoardDTO selectBoardDetail(Long id);
    public int updateBoard(BoardDTO boardDTO);
    public int deleteBoard(Long id);
    public List<BoardDTO> boardAllList();
    public int selectBoardTotalCount();

    // 밑에 리스트는 나중에 검색했을때 값 가져올 예정
    public List<BoardDTO> boardSearchTitleList(String title);

    public List<BoardDTO> boardSearchContentList(String content);
    public int getLikesTotalCount(Long id);

    public int getDisLikesTotalCount(Long id);

}
