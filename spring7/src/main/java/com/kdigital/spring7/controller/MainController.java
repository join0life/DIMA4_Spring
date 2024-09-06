package com.kdigital.spring7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	/**
	 * 첫 화면 요청
	 * @return
	 */
	@GetMapping({"/",""})
	public String index() {
		return "index";
	}
}
