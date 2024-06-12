package com.JYProject.project.repository.AdopterRepository;

import com.JYProject.project.model.Adopter;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdopterMapperRepository {
     int insertAdopt(Adopter adopter);
}
