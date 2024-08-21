package com.kdigital.test2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kdigital.test2.dto.Fitness;



@Controller
public class FitnessController {
	@PostMapping("/regist")
	public String regist(
			@ModelAttribute Fitness fitness,
			Model model ){
		System.out.println(fitness);
		
		model.addAttribute("fitness", fitness);
		
		return "bmiResult"; // forwarding
	}
}
