package com.JYProject.project.service;

import com.JYProject.project.model.Like;
import com.JYProject.project.model.dto.LikeDTO;

import java.util.List;

public interface LikeService {
    public int insertLikeBoard(LikeDTO likeDTO);
    public int insertLikeReply(LikeDTO likeDTO);
    public List<LikeDTO> getAllLikes(Long id);
    public boolean getOneLikesBoardAndMemberId(LikeDTO likeDTO);


}
