package com.kdigital.ajaxtest.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdigital.ajaxtest.dto.GuestbookDTO;
import com.kdigital.ajaxtest.service.GuestbookService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GuestbookController {
	
	final GuestbookService service;
	
	@GetMapping("/guestbookpage")
	public String guestbook(Model model) {
		List<GuestbookDTO> list = null;
		
		// DB로부터 데이터를 불러오는 작업
		list = service.selectAll();
		
		model.addAttribute("list", list);
		
		return "guest/guestbook";
	}
}
