package com.kdigital.spring7.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.LastModifiedDate;

import com.kdigital.spring7.dto.BoardDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

@Entity
@Table(name="board")
public class BoardEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="board_num")
	private Long boardNum;
	
	@Column(name="board_writer", nullable = false)
	private String boardWriter;
	
	@Column(name="board_title")
	private String boardTitle;
	
	@Column(name="board_content")
	private String boardContent;
	
	@Column(name="hit_count")
	private int hitCount;
	
	@Column(name="create_date")
	@CreationTimestamp // 게시글이 처음 생성될 때 자동으로 날짜 세팅
	private LocalDateTime createDate;
	
	@Column(name="update_date")
	@LastModifiedDate // 게시글이 수정된 마지막 날짜/시간을 세팅
	private LocalDateTime updateDate;
	
	@Column(name="original_file_name")
	private String originalFileName;
	
	@Column(name="saved_file_name")
	private String savedFileName;
	
	// 댓글 개수 처리
	@Formula("(SELECT count(1) FROM reply r WHERE board_num = r.board_num)")
	private int replyCount;
	
	
	
	/* 참고 : 양방향 관계가 설정되어 있는 경우
	 * one에 해당하는 테이블 엔티티에 설정함!
	 * - mappedBy : 양방향 참조를 할 경우 one에 해당하는 테이블 엔티티값
	 * - CascadeType.REMOVE : DB에서 테이블 생성할 때 on delete cascade 옵션과 동일
	 * - FetchType.LAZY : 지연 호출, FetchType.EAGER : 즉시 호출
	 * - orphanRemoval : 1:N 관계일 때 지정하는 옵션
	 * 					자식 엔티티가 변경되면 JPA(i -> u -> d)
	 * 					PK(JoinColumn)의 값이 Null이 되는 자식 --> 고아 객체. 고아 객체는 연결점을 잃게 됨
	 * 					orphanRemoval을 true로 설정하면 고아 객체가 삭제됨
	 * 					(게시판은 양방향 관계가 아니므로 아래 코드는 불필요)
	 */
//	@OneToMany(
//			mappedBy = "boardEntity"
//			, cascade = CascadeType.REMOVE
//			, fetch = FetchType.LAZY
//			, orphanRemoval = true
//			)
//	@OrderBy("reply_num asc") 
//	private List<ReplyEntity> replyEntity = new ArrayList<>();
	
	
	// DTO를 받아서 ----> Entity로 반환
	public static BoardEntity toEntity(BoardDTO boardDTO) {
		return BoardEntity.builder()
				.boardNum(boardDTO.getBoardNum())
				.boardWriter(boardDTO.getBoardWriter())
				.boardTitle(boardDTO.getBoardTitle())
				.boardContent(boardDTO.getBoardContent())
				.hitCount(boardDTO.getHitCount())
				// 사용자가 직접 입력하는 정보가 아니기 때문에 필요하지 않음
//				.createDate(boardDTO.getCreateDate())
//				.updateDate(boardDTO.getUpdateDate())
				.originalFileName(boardDTO.getOriginalFileName())
				.savedFileName(boardDTO.getSavedFileName())
				.build();
	}
}
