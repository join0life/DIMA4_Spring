package com.kdigital.spring7.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kdigital.spring7.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
	// 검색 기능을 위해 추가
	List<BoardEntity> findByBoardWriterContains(String searchWord, Sort sort);
	List<BoardEntity> findByBoardTitleContains(String searchWord, Sort sort);
	List<BoardEntity> findByBoardContentContains(String searchWord, Sort sort);

}