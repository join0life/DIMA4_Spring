package com.kdigital.test1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
	@PostMapping({"/calc"})
	public String index(
			@RequestParam(name="x", defaultValue="1") int x,
			@RequestParam(name="y", defaultValue="1") int y,
			Model model
			) {

		System.out.println("x = "+x+", y = " +y); // 없어도 됨
		
		model.addAttribute("x", x);
		model.addAttribute("y", y);
		model.addAttribute("z", (x+y)); // 서버 -> 클라이언트
		
		return "index";	
	}
}
