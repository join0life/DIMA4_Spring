package com.kdigital.spring6.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kdigital.spring6.dto.Friend;
import com.kdigital.spring6.service.FriendService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor   // 생성자 주입방식, setter 주입방식, Annotation 주입방식
public class FriendController {
	
	final FriendService service;   // Bean(Spring 이 관리하는 ) 객체를 주입받음
	
	/**
	 * 화면을 요청
	 * @return
	 */
	@GetMapping("/insertFriend")
	public String insertFriend(Model model) {
		// (error 처리를 위해)비어있는 객체의 데이터를 추가
		model.addAttribute("friend", new Friend()); 
		
		return "insertFriend";
	}
	
	/**
	 * 저장 요청
	 * @param Friend
	 * @return
	 */
	@PostMapping("/insertFriend")
	public String insertFriend(
			@Valid 
			@ModelAttribute Friend friend,
			BindingResult bindingResult
			) {
		
		log.info("friend 객체: {}", friend.toString());
		log.info("bindingResult: {}", bindingResult);
		
		if(bindingResult.hasErrors()) {
			log.info("validation 시 오류 발생");
			return "insertFriend"; 
		}
		
		service.insert(friend);
		
		return "redirect:/";
	}

	/**
	 * 친구 목록 요청 ==> DB에서 데이터 목록을 가져와야 함
	 */
	@GetMapping("/listFriend")
	public String listFriend(Model model) {
		
		List<Friend> list = service.list();
		model.addAttribute("list", list);
		
		return "listFriend";
	}
	/**
	 * 파라미터로 전송받은 fseq값을 이용해 DB에서 데이터를 삭제하도록
	 * 전달함
	 * @param fseq
	 * @return
	 */
	@GetMapping("/deleteOne")
	public String deleteFriend(@RequestParam(name="fseq") Integer fseq) {
		log.info("전달된 번호: {} ==> ", fseq);
		
		service.deleteOne(fseq);
		
		return "redirect:/listFriend";   // redirect는 get 요청이다!
	}
	
	/**
	 * 데이터를 수정하기 전 수정할 데이터를 DB에서 조회하는 기능
	 * @param fseq
	 * @return
	 */
	@GetMapping("/updateOne")
	public String updateFriend(@RequestParam(name="fseq") Integer fseq, Model model) {
		
		Friend friend = service.selectOne(fseq);
		
		model.addAttribute("friend", friend);
		
		return "updateFriend";
	}
	
	/**
	 * 전달받은 데이터를 수정하기 위한 요청(DB 까지 전달해야함)
	 * @param fseq
	 * @param model
	 * @return
	 */
	@PostMapping("/updateOne")
	public String updateFriend(@ModelAttribute Friend friend) {
		
		service.updateOne(friend); //
		
		return "redirect:/listFriend"; // 브라우저에게 목록을 다시 요청하도록 지시!
	}
	
	
}





