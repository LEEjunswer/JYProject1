package com.JYProject.project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyResponseDTO {
   private List<MemberDTO> memberList;
   private List<ReplyDTO> replyList;
   private int totalPages;
   private int currentPage;
   private int totalCount;
}
