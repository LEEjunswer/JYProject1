package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BoardMybatisRepository {
        public int insertBoard(BoardDTO boardDTO);
        public BoardDTO selectBoardDetail(Long id);
        public int updateBoard(BoardDTO boardDTO);
        public int deleteBoard(Long id);
        public List<BoardDTO> selectBoardList();
        public int selectBoardTotalCount();

        public int getLikesTotalCount(Long id);

        public int getDisLikesTotalCount(Long id);

}
