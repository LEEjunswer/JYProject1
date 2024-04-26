package com.JYProject.project.service;

import com.JYProject.project.model.Like;
import com.JYProject.project.model.dto.LikeDTO;
import com.JYProject.project.repository.mybatis.LikeMapperRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final  LikeMapperRepository likeMapperRepository;
    @Override
    public int insertLikeBoard(LikeDTO likeDTO) {

        return likeMapperRepository.insertLikeBoard(convertToEntity(likeDTO));
    }

    @Override
    public int insertLikeReply(LikeDTO likeDTO) {
        return likeMapperRepository.insertLikeReply(convertToEntity(likeDTO));
    }

    @Override
    public List<LikeDTO> getAllLikes(Long id) {
        List<Like>   like =likeMapperRepository.getAllLikes(id);
        return like.stream().map(this :: convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<LikeDTO> getAllLikesBoardAndMemberId(LikeDTO likeDTO) {

        List<Like> like = likeMapperRepository.getAllLikesBoardAndMemberId(convertToEntity(likeDTO));
        return like.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<LikeDTO> getAllLikesReplyAndMemberId(LikeDTO likeDTO) {
        List<Like> like = likeMapperRepository.getAllLikesReplyAndMemberId(convertToEntity(likeDTO));
        return like.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private Like convertToEntity(LikeDTO likeDTO){
            Like like = new Like();
            like.setLikeId(likeDTO.getLikeId());
            like.setMemberId(likeDTO.getMemberId());
            like.setReplyId(likeDTO.getReplyId());
            like.setRegDate(likeDTO.getRegDate());
        return like;
    }
    private LikeDTO convertToDTO(Like like){
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setLikeId(like.getLikeId());
        likeDTO.setBoardId(like.getBoardId());
        likeDTO.setMemberId(like.getMemberId());
        likeDTO.setRegDate(like.getRegDate());
        return likeDTO;
    }
}
