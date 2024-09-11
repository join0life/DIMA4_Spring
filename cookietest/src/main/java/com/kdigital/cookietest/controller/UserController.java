package com.kdigital.cookietest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	
	@GetMapping("/loginForm")
	public String loginFrom() {
		return "loginForm";
	}
	
	@PostMapping("/login")
	public String login(
			@RequestParam(name="loginId") String loginId
			, @RequestParam(name="loginPwd") String loginPwd
			, @RequestParam(name="remember-me", defaultValue= "false") boolean rememberme
			, HttpSession session // 로그인 유지를 위한 session 객체
			) {
		
		// 전달 받은 아이디와 비밀번호를 DB에서 확인했다고 가정!!
		
		session.setAttribute("loginId", loginId);
		session.setAttribute("loginName", "변사또");
		System.out.println(rememberme);
		
		if (rememberme) {
			session.setAttribute("remember", true);
		} else {
			session.setAttribute("remember", false);
		}
		
		return "loginResult";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "loginResult";
	}

}
