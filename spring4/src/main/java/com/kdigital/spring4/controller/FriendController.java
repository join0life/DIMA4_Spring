package com.kdigital.spring4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kdigital.spring4.dto.Friend;

@Controller
public class FriendController {
	@PostMapping("/regist")
	public String regist(
			@ModelAttribute Friend friend,
			Model model ){
		System.out.println(friend);
		
		model.addAttribute("friend", friend); // friend 객체에서 정보를 꺼낼 때 "friend"라는 이름으로 꺼냄
		
		return "registResult"; // forwarding
	}
}
