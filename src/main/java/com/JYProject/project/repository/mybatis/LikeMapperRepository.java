package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.Like;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikeMapperRepository {

     int insertLikeBoard(Like like);
     int insertLikeReply(Like like);
     List<Like> getAllLikes(Long id);
     int getOneLikesBoardAndMemberId(Like like);
     int getOneBoardDisLikes(Like like);
     int getOneBoardLikes(Like like);
}
