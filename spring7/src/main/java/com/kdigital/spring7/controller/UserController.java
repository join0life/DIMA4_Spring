package com.kdigital.spring7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	/**
	 * 로그인 화면 요청
	 * @return
	 */
	@GetMapping("/login")
	public String login() {
		
		return "user/login";
	}
	/**
	 * 회원 가입시 사용 가능한 아이디인지 비동기를 이용해 처리
	 * @param userId
	 * @return
	 */
	@PostMapping("/confirmId")
	@ResponseBody
	public boolean confirmId(@RequestParam(name="userId") String userId) {
		log.info("회원 가입 아이디: {}", userId);
		
		boolean result = userService.existId(userId);  // 존재하면 false, 없으면 true
		
		return !result;
	}
	
}
