package com.JYProject.project.service.DeletedService;

import com.JYProject.project.model.dto.DeletedMemberDTO;
import com.JYProject.project.model.dto.MemberDTO;

public interface DeletedService {
    int getDeleteMember(MemberDTO memberDTO);
    
    /*나중에 찾을떄 리스트나 가져올 수 있도록*/
    DeletedMemberDTO getOneDeletedMember(DeletedMemberDTO deletedMemberDTO);
}
