package com.JYProject.project.service.BoardService;

import com.JYProject.project.model.Board;
import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.model.dto.FileDTO;
import com.JYProject.project.model.dto.MemberDTO;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface BoardService {

     Long insertBoard(BoardDTO boardDTO ,List<String> fileUrls)throws Exception;
     BoardDTO selectBoardDetail(Long boardId);
     BoardDTO handleBoardView(Long boardId, String userId, String nickName);
     int updateBoard(BoardDTO boardDTO);
     int deleteBoard(Long boardId);
     List<BoardDTO> boardAllList();
    int getMyBoardCount(Long memberId);
    void boardDeleteFindMemberId(Long memberId);
    BoardDTO getBoardDetail(Long id);
    List<BoardDTO> boardSearchAllList(String string);
    List<BoardDTO> boardSearchTitleList(String title);
    List<BoardDTO> boardSearchWriterList(String writer);
    List<BoardDTO> boardSearchContentList(String content);
    List<Integer> getUsersBoardCount(List<MemberDTO> memberDTO);
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
