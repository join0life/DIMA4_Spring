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
			Model modle ){
		System.out.println(friend);
		
		return "registResult";
	}
}
