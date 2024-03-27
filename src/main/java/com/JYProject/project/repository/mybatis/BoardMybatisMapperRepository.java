package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BoardMybatisMapperRepository implements BoardMapperRepository {
        private final BoardMapperRepository boardMapperRepository;

        @Autowired
        public BoardMybatisMapperRepository(BoardMapperRepository boardMapperRepository) {
                this.boardMapperRepository = boardMapperRepository;
        }
        public int insertBoard(BoardDTO boardDTO){

                return boardMapperRepository.insertBoard(boardDTO);
        }
        public BoardDTO selectBoardDetail(Long id){
                return boardMapperRepository.selectBoardDetail(id);
        }
        public int updateBoard(BoardDTO boardDTO){

                return boardMapperRepository.updateBoard(boardDTO);
        }
        public int deleteBoard(Long id){

                return boardMapperRepository.deleteBoard(id);
        }
        public List<BoardDTO> boardAllList(){
                return boardMapperRepository.boardAllList();
        }
        public int selectBoardTotalCount(){
                return boardMapperRepository.selectBoardTotalCount();
        }

        @Override
        public List<BoardDTO> boardSearchTitleList(String title) {
                return boardMapperRepository.boardSearchTitleList(title);
        }

        @Override
        public List<BoardDTO> boardSearchContentList(String content) {
                return boardMapperRepository.boardSearchContentList(content);
        }

        public int getLikesTotalCount(Long id){

                return boardMapperRepository.getLikesTotalCount(id);
        }

        public int getDisLikesTotalCount(Long id){

                return boardMapperRepository.getDisLikesTotalCount(id);
        }

}
