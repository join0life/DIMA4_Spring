package com.kdigital.ajaxtest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kdigital.ajaxtest.dto.GuestbookDTO;
import com.kdigital.ajaxtest.entity.GuestbookEntity;
import com.kdigital.ajaxtest.repository.GuestbookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GuestbookService {
	
	final GuestbookRepository repository;

	/**
	 * 방명록의 모든 글을 조회
	 * @return
	 */
	public List<GuestbookDTO> selectAll() {
		List<GuestbookDTO> list = new ArrayList<>();
		
		List<GuestbookEntity> guestEntity = repository.findAll();
		
		// entity를 DTO로 변환
		guestEntity.forEach((entity) -> list.add(GuestbookDTO.toDTO(entity)));
		
		return list;
	}
}
