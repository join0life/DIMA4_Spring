package com.kdigital.spring7.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kdigital.spring7.dto.BoardDTO;
import com.kdigital.spring7.entity.BoardEntity;
import com.kdigital.spring7.repository.BoardRepository;

import ch.qos.logback.core.net.SyslogOutputStream;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
   
   final BoardRepository boardRepository;
   /**
    * DB에 게시글 저장
    * @param boardDTO : 저장해야하는 게시글
    */
   public void insertBoard(BoardDTO boardDTO) {
      // 1) Entity로 변환할 것
      BoardEntity boardEntity = BoardEntity.toEntity(boardDTO);
      
      // 2) save()로 데이터 저장 
      boardRepository.save(boardEntity);
   }
   
   /**
    * 게시글 목록 요청
 * @param searchWord 
 * @param searchItem 
    * @return
    */
   public List<BoardDTO> selectAll(String searchItem, String searchWord) {
      List<BoardDTO> list = new ArrayList<>();
      
      List<BoardEntity> entityList = null;
      switch(searchItem) {
      case "boardTitle" :
    	  entityList = boardRepository.findByBoardTitleContains(searchWord, Sort.by(Sort.Direction.DESC, "boardNum")); break;
      case "boardWriter" :
    	  entityList = boardRepository.findByBoardWriterContains(searchWord, Sort.by(Sort.Direction.DESC, "boardNum")); break;
      case "boardContent" :
    	  entityList = boardRepository.findByBoardContentContains(searchWord, Sort.by(Sort.Direction.DESC, "boardNum")); break;
      }
      
      // 1) 1단계 - 단순 조회
      //List<BoardEntity> entityList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "boardNum")); // "boardNum" 기준으로 역순 정렬
      
      //Entity를 DTO로 바꾸는 작업 수행
      entityList.forEach((entity) -> list.add(BoardDTO.toDTO(entity)));
      
      return list;
   }

   /**
    * PK에 해당하는 boardNum 값을 이용해 글 한 개 조회
    * @param boardNum
    * @return
    */
	public BoardDTO selectOne(Long boardNum) {
		Optional<BoardEntity> entity = boardRepository.findById(boardNum);
		
		// 데이터를 꺼내 BoardDTO로 변환
		if(entity.isPresent()) {
			BoardEntity temp = entity.get();
			return BoardDTO.toDTO(temp);
		}
		return null;
	}

	/**
	 * 전달받은 글번호(boardNum)을 DB에서 삭제
	 * @param boardNum
	 */
	@Transactional
	public void deleteOne(Long boardNum) {
		// 글번호에 해당하는 글이 존재하는지 읽어옴
		Optional<BoardEntity> entity = boardRepository.findById(boardNum);
		
		if(entity.isPresent()) {
			boardRepository.deleteById(boardNum);
		}
		
	}

	/**
	 * DB에 데이터를 수정 요청
	 * @param board
	 * @return
	 */
	@Transactional // DB에 두 번 접근할 때 반드시 있어야 함
	public void updateBoard(BoardDTO board) {
		// 1) 수정하려는 데이터가 있는지 조회
		Optional<BoardEntity> entity = boardRepository.findById(board.getBoardNum());
		if (entity.isPresent()) {
			BoardEntity temp = entity.get();
			temp.setBoardTitle(board.getBoardTitle()); // DB에 수정함
			temp.setBoardContent(board.getBoardContent());
		}
	}
	/**
	 * 조회수 증가
	 * @param boardNum
	 */
	@Transactional
	public void incrementHitcount(Long boardNum) {
		Optional<BoardEntity> entity = boardRepository.findById(boardNum);
		
		if(entity.isPresent()) {
			BoardEntity temp = entity.get();
			temp.setHitCount(temp.getHitCount() + 1);
		}
		
	}

}
