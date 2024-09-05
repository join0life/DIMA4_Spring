package com.kdigital.spring7.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kdigital.spring7.dto.ReplyDTO;
import com.kdigital.spring7.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {
	final ReplyService replyService;
	
	@PostMapping("/replyInsert")
	public ReplyDTO replyInsert(@ModelAttribute ReplyDTO replyDTO) {
		log.info("댓글 삽입 : {}", replyDTO.toString());
		
		return replyDTO; // 수정할 내용임!
	}
	
	/**
	 *  전달 받은 게시글의 댓글 목록 전체를 조회
	 * @return
	 */
	@GetMapping("/replyAll")
	public List<ReplyDTO> replyAll(@RequestParam(name="boardNum") Long boardNum) {
		log.info("{}", boardNum);
		
		List<ReplyDTO> list = replyService.replyAll(boardNum);
		return list; 
	}
}
