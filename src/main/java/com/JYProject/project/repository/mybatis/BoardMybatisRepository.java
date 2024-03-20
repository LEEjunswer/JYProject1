package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BoardMybatisRepository implements BoardRepository {
        private final BoardRepository boardRepository;

        @Autowired
        public BoardMybatisRepository(BoardRepository boardRepository) {
                this.boardRepository = boardRepository;
        }
        public int insertBoard(BoardDTO boardDTO){

                return boardRepository.insertBoard(boardDTO);
        }
        public BoardDTO selectBoardDetail(Long id){
                return boardRepository.selectBoardDetail(id);
        }
        public int updateBoard(BoardDTO boardDTO){

                return boardRepository.updateBoard(boardDTO);
        }
        public int deleteBoard(Long id){

                return boardRepository.deleteBoard(id);
        }
        public List<BoardDTO> boardAllList(){
                return boardRepository.boardAllList();
        }
        public int selectBoardTotalCount(){
                return boardRepository.selectBoardTotalCount();
        }

        @Override
        public List<BoardDTO> boardSearchTitleList(String title) {
                return boardRepository.boardSearchTitleList(title);
        }

        @Override
        public List<BoardDTO> boardSearchContentList(String content) {
                return boardRepository.boardSearchContentList(content);
        }

        public int getLikesTotalCount(Long id){

                return boardRepository.getLikesTotalCount(id);
        }

        public int getDisLikesTotalCount(Long id){

                return boardRepository.getDisLikesTotalCount(id);
        }

}
