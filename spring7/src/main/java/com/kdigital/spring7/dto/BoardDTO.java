package com.kdigital.spring7.dto;

import java.time.LocalDateTime;

import com.kdigital.spring7.entity.BoardEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class BoardDTO {
   private Long    boardNum;
   private String   boardWriter;
   private String   boardTitle;
   private String   boardContent;
   private int    hitCount;
   private LocalDateTime createDate;
   private LocalDateTime updateDate;
   
   public BoardDTO(Long boardNum, String boardWriter, String boardTitle, int hitCount, LocalDateTime createDate) {
	   this.boardNum = boardNum;
	   this.boardTitle = boardTitle;
	   this.boardWriter = boardWriter;
	   this.hitCount = hitCount;
	   this.createDate = createDate;
   
}
   
   
   // Entity를 받아서 ----> DTO로 전환
   public static BoardDTO toDTO(BoardEntity boardEntity) {
         return BoardDTO.builder()
               .boardNum(boardEntity.getBoardNum())
               .boardWriter(boardEntity.getBoardWriter())
               .boardTitle(boardEntity.getBoardTitle())
               .boardContent(boardEntity.getBoardContent())
               .hitCount(boardEntity.getHitCount())
               .createDate(boardEntity.getCreateDate())
               .updateDate(boardEntity.getUpdateDate())   //dto의 경우 사용자에게 db 정보를 불러오는 케이스므로, 생략하면 안됨
               .build();
               
      
   }


}


