package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.Like;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikeMapperRepository {

    public int insertLikeBoard(Like like);
    public int insertLikeReply(Like like);
    public List<Like> getAllLikes(Long id);
    public List<Like> getAllLikesBoardAndMemberId(Like like);
    public List<Like> getAllLikesReplyAndMemberId(Like like);
}
