package com.kdigital.spring7.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kdigital.spring7.dto.BoardDTO;
import com.kdigital.spring7.entity.BoardEntity;
import com.kdigital.spring7.repository.BoardRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
   
   final BoardRepository boardRepository;
   
   @Value("${user.board.pageLimit}")
   private int pageLimit;	// 한 페이지에 보여줄 글의 개수
	
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
 * @param pageable 
 * @param searchWord 
 * @param searchItem 
    * @return
    */
   public Page<BoardDTO> selectAll(Pageable pageable, String searchItem, String searchWord) {
      int page = pageable.getPageNumber() - 1;
      // -1을 한 이유 : DB page의 위치 값이 0부터 시작하므로
      // 사용자가 1페이지를 요청하면 DB에서는 0페이지를 가져 온다.
	   
	  // List<BoardDTO> list = new ArrayList<>();
	  
	  // 3) 페이징이 추가된 조회
	  Page<BoardEntity> entityList = null;
      
	  switch(searchItem) {
      case "boardTitle" :
    	  entityList = boardRepository.findByBoardTitleContains(
    			  searchWord,
    			  PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardNum"))); 
    	  break;
      case "boardWriter" :
    	  entityList = boardRepository.findByBoardWriterContains(
    			  searchWord,
    			  PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardNum"))); 
    	  break;
      case "boardContent" :
    	  entityList = boardRepository.findByBoardContentContains(
    			  searchWord,
    			  PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "boardNum"))); 
    	  break;
      }
	  
	  /* 2) 검색 기능이 추가된 조회
      List<BoardEntity> entityList = null;
      switch(searchItem) {
      case "boardTitle" :
    	  entityList = boardRepository.findByBoardTitleContains(searchWord, Sort.by(Sort.Direction.DESC, "boardNum")); break;
      case "boardWriter" :
    	  entityList = boardRepository.findByBoardWriterContains(searchWord, Sort.by(Sort.Direction.DESC, "boardNum")); break;
      case "boardContent" :
    	  entityList = boardRepository.findByBoardContentContains(searchWord, Sort.by(Sort.Direction.DESC, "boardNum")); break;
      }
      
      */
      
      // 1) 1단계 - 단순 조회
      //List<BoardEntity> entityList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "boardNum")); // "boardNum" 기준으로 역순 정렬
      
      //Entity를 DTO로 바꾸는 작업 수행
	  
	  Page<BoardDTO> list = null;
      
	  // 일반 기능만 가지고 있는 형태
//      entityList.forEach((entity) -> list.add(BoardDTO.toDTO(entity)));
	  
	  //페이징 형태의 list로 변환
	  // 목록에서 사용할 필요한 데이터만 간추림
	   list = entityList.map(
	            board -> new BoardDTO(
	               board.getBoardNum(),
	               board.getBoardWriter(),
	               board.getBoardTitle(),
	               board.getHitCount(),
	               board.getCreateDate()
	               )
	               
	            ); // entity에서 하나씩 뽑아 board로 넣기
	            
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
