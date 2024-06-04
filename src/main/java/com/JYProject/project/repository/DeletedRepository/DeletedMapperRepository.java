package com.JYProject.project.repository.DeletedRepository;

import com.JYProject.project.model.DeleteMember;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeletedMapperRepository {
    int getDeleteMember(DeleteMember deleteMember);
}
