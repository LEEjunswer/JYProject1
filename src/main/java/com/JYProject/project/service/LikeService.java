package com.JYProject.project.service;

import com.JYProject.project.model.Like;
import com.JYProject.project.model.dto.LikeDTO;

import java.util.List;

public interface LikeService {
     int insertLikeBoard(LikeDTO likeDTO);
     int insertLikeReply(LikeDTO likeDTO);
     List<LikeDTO> getAllLikes(Long id);
     int getOneLikesBoardAndMemberId(LikeDTO likeDTO);
     int getOneBoardDisLikes(LikeDTO likeDTO);
     int getOneBoardLikes(LikeDTO likeDTO);

}
