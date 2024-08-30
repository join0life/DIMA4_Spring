package com.kdigital.spring7.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

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
   
   // 업로드하는 파일을 받는 멤버변수
   private MultipartFile uploadFile;
   
   private String originalFileName; // 원본 파일명
   private String savedFileName;	// 하드디스크에 저장할 때 사용되는 변경된 파일명
   
   // 페이징 처리를 위해 BoardService.java에서 Page 형태로 받은 데이터 중
   // 목록에 출력할 멤버만 간추리기 위해 만든 생성자
   public BoardDTO(Long boardNum,
		   String boardWriter,
		   String boardTitle,
		   int hitCount,
		   LocalDateTime createDate,
		   String originalFileName) {
	   this.boardNum = boardNum;
	   this.boardTitle = boardTitle;
	   this.boardWriter = boardWriter;
	   this.hitCount = hitCount;
	   this.createDate = createDate;
	   this.originalFileName = originalFileName;
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
               .updateDate(boardEntity.getUpdateDate())   //DTO의 경우 사용자에게 DB 정보를 불러오는 케이스므로, 생략하면 안됨
               .originalFileName(boardEntity.getOriginalFileName())
               .savedFileName(boardEntity.getSavedFileName())
               .build();
               
      
   }


}


