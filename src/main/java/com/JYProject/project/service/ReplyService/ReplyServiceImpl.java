package com.JYProject.project.service.ReplyService;

import com.JYProject.project.model.Member;
import com.JYProject.project.model.Reply;
import com.JYProject.project.model.dto.BoardDTO;
import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.model.dto.ReplyDTO;
import com.JYProject.project.model.dto.ReplyResponseDTO;
import com.JYProject.project.repository.ReplyRepository.ReplyMapperRepository;
import com.JYProject.project.service.BoardService.BoardService;
import com.JYProject.project.service.BoardService.BoardServiceImpl;
import com.JYProject.project.service.MemberService.MemberService;
import com.JYProject.project.service.MemberService.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyServiceImpl implements ReplyService {

    private final ReplyMapperRepository replyMybatisRepository;
    private  final MemberService memberService;
    private  final BoardService boardService;


    @Override
    public int insertReply(ReplyDTO replyDTO) {
       Reply reply = convertToEntity(replyDTO);

        return  replyMybatisRepository.insertReply(reply);
    }

    @Override
    public ReplyDTO selectOneReply(Long id){
    Reply reply  = replyMybatisRepository.selectOneReply(id);
        return convertToDTO(reply);
    }

    @Override
    public List<BoardDTO> getBoardsFromReplies(Long memberId) {
        List<ReplyDTO> replyList = replyMybatisRepository.getMyReplyList(memberId).stream().map(this::convertToDTO).toList();
        List<BoardDTO> boardList = new ArrayList<>();

        for (ReplyDTO reply : replyList) {
            BoardDTO board = boardService.getBoardDetail(reply.getBoardId());
            if (board != null) {
                boardList.add(board);
            }
        }

        return boardList;
    }

    @Override
    public int updateReply(ReplyDTO replyDTO) {
   Reply reply = convertToEntity(replyDTO);
        return replyMybatisRepository.updateReply(reply);
    }

    @Override
    public int deleteReply(Long replyId) {
        int  check=replyMybatisRepository.deleteReply(replyId);
        return check;
    }

    @Override
    public int replyLikesTotalCount(Long id) {
        return replyMybatisRepository.replyLikesTotalCount(id);
    }

    @Override
    public List<ReplyDTO> getMyReplyList(Long memberId) {
        return replyMybatisRepository.getMyReplyList(memberId).stream().map(this ::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void replyDeleteFindMemberId(Long memberId) {
        replyMybatisRepository.replyDeleteFindMemberId(memberId);
    }

    @Override
    public int getMyReplyCount(Long memberId) {
        return replyMybatisRepository.getMyReplyCount(memberId);
    }


    @Override
    public int replyDisLikesTotalCount(Long id) {
        return replyMybatisRepository.replyDisLikesTotalCount(id);
    }

    @Override
    public int getOneBoardReplyCount(Long boardId) {
        return replyMybatisRepository.getOneBoardReplyCount(boardId);
    }

    @Override
    public ReplyResponseDTO getOneBoardReplyPaging(Long boardId, int page, int size) {
        if (page < 1) {
            page = 1;
        }
        int offset = (page - 1);
        Map<String, Object> params = new HashMap<>();
        params.put("boardId", boardId);
        params.put("offset", offset);
        params.put("pageSize", size);

        List<ReplyDTO> replyList = replyMybatisRepository.getOneBoardReply(params).stream().map(this::convertToDTO).collect(Collectors.toList());
        System.out.println("replyList = " + replyList);
        int totalRecords = replyMybatisRepository.getOneBoardReplyCount(boardId);
        int totalPages = (int) Math.ceil((double) totalRecords / size);
        List<MemberDTO> memberList = new ArrayList<>();
        ReplyResponseDTO responseList = new ReplyResponseDTO();
        for(ReplyDTO reply : replyList){
            MemberDTO   memberDTO=memberService.getOneMemberId(reply.getMemberId());
            memberList.add(memberDTO);
        }
        responseList.setMemberList(memberList);
        responseList.setReplyList(replyList);
        responseList.setTotalPages(totalPages);
        responseList.setCurrentPage(page);
        responseList.setTotalCount(totalRecords);

        return responseList;
    }

    private Reply convertToEntity(ReplyDTO replyDTO){
        Reply reply = new Reply();
        reply.setReplyId(replyDTO.getReplyId());
        reply.setContent(replyDTO.getContent());
        reply.setMemberId(replyDTO.getMemberId());
        reply.setBoardId(replyDTO.getBoardId());
        reply.setRegDate(replyDTO.getRegDate());
        reply.setDeleteDate(replyDTO.getDeleteDate());
/*        *//*        reply.setLikes(replyDTO.getLikes());*//*
        reply.setDislikes(replyDTO.getDislikes());*/
        reply.setMemberInfo(convertToMemberEntity(replyDTO.getMemberInfo()));
        return  reply;
    }
    private ReplyDTO convertToDTO(Reply reply){
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setReplyId(reply.getReplyId());
        replyDTO.setBoardId(reply.getBoardId());
        replyDTO.setMemberId(reply.getMemberId());
        replyDTO.setContent(reply.getContent());
        replyDTO.setRegDate(reply.getRegDate());
        replyDTO.setDeleteDate(reply.getDeleteDate());
/*        replyDTO.setDislikes(reply.getDislikes());
        replyDTO.setLikes(reply.getLikes());*/
        replyDTO.setMemberInfo(convertToMemberDTO(reply.getMemberInfo()));
        return replyDTO;
    }
    private Member convertToMemberEntity(MemberDTO memberDTO){
        if(memberDTO == null){
            return null;
        }
        Member member = new Member();
        member.setMemberId(memberDTO.getMemberId());
        member.setNickname(memberDTO.getNickname());
        return  member;
    }
    private MemberDTO convertToMemberDTO(Member member){
        if(member == null){
            return null;
        }
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId(member.getMemberId());
        memberDTO.setNickname(member.getNickname());
        return memberDTO;
    }
}
