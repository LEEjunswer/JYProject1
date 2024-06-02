package com.JYProject.project.service;

import com.JYProject.project.model.Board;
import com.JYProject.project.model.Like;
import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.model.dto.LikeDTO;
import com.JYProject.project.repository.mybatis.BoardMapperRepository;
import com.JYProject.project.repository.mybatis.LikeMapperRepository;
import com.JYProject.project.repository.mybatis.MemberMapperRepository;
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
    private  final MemberMapperRepository memberMapperRepository;

    @Override
    public int insertLikeBoard(LikeDTO likeDTO) {

        // 이걸 좀더 수정을 해봐야 겟다;;  DTO를 한꺼번에 합치는게 더 나으려나 ;;
       Board board =  boardMapperRepository.selectBoardDetail(likeDTO.getBoardId());
        // 좋아요나 싫어요를 받을떄마다 포인트가 깎이거나 상승한다.
        if (likeDTO.isLikes()) {
            boardMapperRepository.incrementLikes(likeDTO.getBoardId());
            memberMapperRepository.addPointLikes(board.getMemberId());
        } else {
            boardMapperRepository.incrementDislikes(likeDTO.getBoardId());
            memberMapperRepository.addPointDisLikes(board.getMemberId());

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
