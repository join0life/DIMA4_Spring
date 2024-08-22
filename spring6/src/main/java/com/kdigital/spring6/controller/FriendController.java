package com.kdigital.spring6.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kdigital.spring6.dto.Friend;
import com.kdigital.spring6.service.FriendService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class FriendController {
	final FriendService service; // 주입(Injection) 받음 ==> IoC
	/*
	 * 화면을 요청
	 * @return
	 * */
	
	@GetMapping("/insertFriend")
	public String insertFriend() {
		return "insertFriend";
	}
	/*
	 * 저장을 요청
	 * @param Friend
	 * @return
	 */
	@PostMapping("/insertFriend")
	public String insertFriend(
			@ModelAttribute Friend friend
			) {
		// 저장할 수 있도록 service에 데이터를 넘김
		log.info("Controller");
		
		service.insert(friend);
		
		return "redirect:/"; // 브라우저한테 / 요청을 하도록 함! ==> redirect 
	}
	/*
	 * 친구 목록 요청 ==> DB에서 데이터 목록을 가져와야 함
	 */
	@GetMapping("/listFriend")
	public String listFriend(Model model) {
		
		List<Friend> list = service.list();
		model.addAttribute("list", list);
		return "listFriend";
	}
}
