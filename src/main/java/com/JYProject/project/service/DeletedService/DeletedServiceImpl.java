package com.JYProject.project.service.DeletedService;

import com.JYProject.project.model.DeleteMember;
import com.JYProject.project.model.dto.DeletedMemberDTO;
import com.JYProject.project.model.dto.MemberDTO;
import com.JYProject.project.repository.DeletedRepository.DeletedMapperRepository;
import com.JYProject.project.service.BoardService.BoardService;
import com.JYProject.project.service.MemberService.MemberService;
import com.JYProject.project.service.ReplyService.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletedServiceImpl implements DeletedService {
    private final DeletedMapperRepository deletedMapperRepository;
    private final MemberService memberService;
    private final ReplyService replyService;
    private final BoardService boardService;
    @Override
    public int getDeleteMember(MemberDTO memberDTO) {
        DeletedMemberDTO deletedMemberDTO = new DeletedMemberDTO();
        deletedMemberDTO.setLoginId(memberDTO.getLoginId());
        deletedMemberDTO.setDeletedId(memberDTO.getMemberId());
        deletedMemberDTO.setName(memberDTO.getName());
        deletedMemberDTO.setPhone(memberDTO.getPhone());
        deletedMemberDTO.setEmail(memberDTO.getEmail());
        deletedMemberDTO.setZipCode(memberDTO.getZipCode());
        deletedMemberDTO.setAddress(memberDTO.getZipCode());
        deletedMemberDTO.setAddressDetail(memberDTO.getAddressDetail());
        deletedMemberDTO.setRegDate(memberDTO.getRegDate());
        /*딜리틀 값을 넘기고 멤버서비스는 삭제*/
        replyService.replyDeleteFindMemberId(memberDTO.getMemberId());
        boardService.boardDeleteFindMemberId(memberDTO.getMemberId());
        MemberDTO memberDTO1 = new MemberDTO();
        memberDTO1.setMemberId(memberDTO.getMemberId());
        memberDTO1.setActive(false);
        memberService.deleteMember(memberDTO1);
        return deletedMapperRepository.getDeleteMember(convertToEntity(deletedMemberDTO));
    }

    /*아직 미구현 어드면영역만들면서 같이 만들듯*/
    @Override
    public DeletedMemberDTO getOneDeletedMember(DeletedMemberDTO deletedMemberDTO) {
        return null;
    }


    private DeletedMemberDTO convertToDTO(DeleteMember deleteMember){
    DeletedMemberDTO deletedMemberDTO = new DeletedMemberDTO();
    deletedMemberDTO.setLoginId(deleteMember.getLoginId());
    deletedMemberDTO.setName(deleteMember.getName());
    deletedMemberDTO.setEmail(deleteMember.getEmail());
    deletedMemberDTO.setPhone(deleteMember.getPhone());
    deletedMemberDTO.setZipCode(deleteMember.getZipcode());
    deletedMemberDTO.setAddress(deleteMember.getAddress());
    deletedMemberDTO.setAddressDetail(deleteMember.getAddressDetail());;
    deletedMemberDTO.setRegDate(deleteMember.getRegDate());
    return deletedMemberDTO;
    }
    private DeleteMember convertToEntity(DeletedMemberDTO deletedMemberDTO){
        DeleteMember deleteMember = new DeleteMember();
        deleteMember.setLoginId(deletedMemberDTO.getLoginId());
        deleteMember.setName(deletedMemberDTO.getName());
        deleteMember.setEmail(deletedMemberDTO.getEmail());
        deleteMember.setPhone(deletedMemberDTO.getPhone());
        deleteMember.setZipcode(deletedMemberDTO.getZipCode());
        deleteMember.setAddress(deletedMemberDTO.getAddress());
        deleteMember.setAddressDetail(deletedMemberDTO.getAddressDetail());
        deleteMember.setRegDate(deletedMemberDTO.getRegDate());
        return deleteMember;
    }
}
