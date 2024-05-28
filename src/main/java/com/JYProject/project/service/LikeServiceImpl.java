package com.JYProject.project.service;

import com.JYProject.project.model.Like;
import com.JYProject.project.model.dto.LikeDTO;
import com.JYProject.project.repository.mybatis.BoardMapperRepository;
import com.JYProject.project.repository.mybatis.LikeMapperRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final  LikeMapperRepository likeMapperRepository;
    private  final BoardMapperRepository boardMapperRepository;

    @Override
    public int insertLikeBoard(LikeDTO likeDTO) {

        if (likeDTO.isLikes()) {
            boardMapperRepository.incrementLikes(likeDTO.getBoardId());
        } else {
            boardMapperRepository.incrementDislikes(likeDTO.getBoardId());
        }
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
    public int getOneLikesBoardAndMemberId(LikeDTO likeDTO) {

        return likeMapperRepository.getOneLikesBoardAndMemberId(convertToEntity(likeDTO));
    }

    @Override
    public int getOneBoardDisLikes(LikeDTO likeDTO) {
        return likeMapperRepository.getOneBoardDisLikes(convertToEntity(likeDTO));
    }

    @Override
    public int getOneBoardLikes(LikeDTO likeDTO) {
        return likeMapperRepository.getOneBoardLikes(convertToEntity(likeDTO));
    }


    private Like convertToEntity(LikeDTO likeDTO){
            Like like = new Like();
            like.setLikeId(likeDTO.getLikeId());
            like.setMemberId(likeDTO.getMemberId());
            like.setBoardId(likeDTO.getBoardId());
            like.setLikes(likeDTO.isLikes());
            like.setRegDate(likeDTO.getRegDate());
        return like;
    }
    private LikeDTO convertToDTO(Like like){
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setLikeId(like.getLikeId());
        likeDTO.setBoardId(like.getBoardId());
        likeDTO.setMemberId(like.getMemberId());
        likeDTO.setLikes(like.isLikes());
        likeDTO.setRegDate(like.getRegDate());
        return likeDTO;
    }
}
