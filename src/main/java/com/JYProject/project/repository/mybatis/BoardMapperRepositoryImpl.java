package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.Board;
import com.JYProject.project.model.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BoardMapperRepositoryImpl implements BoardMapperRepository {
        private final BoardMapperRepository boardMapperRepository;

        @Autowired
        public BoardMapperRepositoryImpl(BoardMapperRepository boardMapperRepository) {
                this.boardMapperRepository = boardMapperRepository;
        }
        public int insertBoard(Board board){
                return boardMapperRepository.insertBoard(board);
        }
        public Board selectBoardDetail(Long boardId){
                return boardMapperRepository.selectBoardDetail(boardId);
        }
        public int updateBoard(Board board){

                return boardMapperRepository.updateBoard(board);
        }
        public int deleteBoard(Long boardId){

                return boardMapperRepository.deleteBoard(boardId);
        }
        public List<Board> boardAllList(){

                return boardMapperRepository.boardAllList();
        }
        public int selectBoardTotalCount(){
                return boardMapperRepository.selectBoardTotalCount();
        }

        @Override
        public int boardViewCntIncrease(Long boardId) {
                return boardMapperRepository.boardViewCntIncrease(boardId);
        }

        @Override
        public List<Board> boardSearchTitleList(String title) {
                return boardMapperRepository.boardSearchTitleList(title);
        }

        @Override
        public List<Board> boardSearchContentList(String content) {
                return boardMapperRepository.boardSearchContentList(content);
        }

        public int getLikesTotalCount(Long id){

                return boardMapperRepository.getLikesTotalCount(id);
        }

        public int getDisLikesTotalCount(Long id){

                return boardMapperRepository.getDisLikesTotalCount(id);
        }

        @Override
        public List<Board> boardGetCategoryList(int categoryId) {
                return boardMapperRepository.boardGetCategoryList(categoryId);
        }

}