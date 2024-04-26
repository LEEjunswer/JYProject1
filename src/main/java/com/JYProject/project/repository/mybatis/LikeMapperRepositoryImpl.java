package com.JYProject.project.repository.mybatis;

import com.JYProject.project.model.Like;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
@Slf4j
public class LikeMapperRepositoryImpl implements LikeMapperRepository {
    private  final LikeMapperRepository likeMapperRepository;
    @Override
    public int insertLikeBoard(Like like) {

        return likeMapperRepository.insertLikeBoard(like);
    }

    @Override
    public int insertLikeReply(Like like) {
        return likeMapperRepository.insertLikeReply(like);
    }

    @Override
    public List<Like> getAllLikes(Long id) {
        return null;
    }

    @Override
    public List<Like> getAllLikesBoardAndMemberId(Long id, Long MemberId, Long boardId) {
        return null;
    }

    @Override
    public List<Like> getAllLikesReplyAndMemberId(Long id, Long MemberId, Long replyId) {
        return null;
    }
}