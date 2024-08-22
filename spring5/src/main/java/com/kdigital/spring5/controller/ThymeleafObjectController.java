package com.kdigital.spring5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafObjectController {
	
	@GetMapping("/display/basicObj")
	public String basicObj(Model model) {
		int intNum = 123456780;
		double doubleNum = 1234.56780;
		double percent = 0.0325;
		double money = 56700;
		
		model.addAttribute("intNum", intNum);
		model.addAttribute("doubleNum", doubleNum);
		model.addAttribute("percent", percent);
		model.addAttribute("money", money);
		
		return "thyme_basicObj";
	}
}
