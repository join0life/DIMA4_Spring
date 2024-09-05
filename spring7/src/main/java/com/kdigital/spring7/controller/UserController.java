package com.kdigital.spring7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kdigital.spring7.dto.UserDTO;
import com.kdigital.spring7.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
	
	final UserService userService;
	/**
	 * 회원 가입을 위한 화면 요청
	 * @return
	 */
	@GetMapping("/join")
	public String join() {
		return "user/join";
	}
	/**
	 * 회원가입 처리
	 * @param userDTO
	 * @return
	 */
	@PostMapping("/join")
	public String join(@ModelAttribute UserDTO userDTO
			) {
		log.info("UserDTO : {}", userDTO.toString());
		
		userDTO.setRoles("ROLE_USER");
		userDTO.setEnabled(true);
		
		boolean result = userService.join(userDTO);
		log.info("회원 가입 성공 : {}", result);
		
		return "redirect:/";
	}
}
