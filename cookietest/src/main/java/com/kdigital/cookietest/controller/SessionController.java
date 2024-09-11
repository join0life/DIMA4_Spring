package com.kdigital.cookietest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SessionController {

	@GetMapping("/sessionSave")
	public String sessionSave(HttpSession session) {
		session.setAttribute("loginId", "hong");
		session.setAttribute("loginName", "홍길동");
		session.setAttribute("loginAge", 25);
		
		return "sessionResult";
	}
	
	@GetMapping("/sessionRead")
	public String sessionRead(HttpSession session) {
		// isNew() : Cookie base의 session 사용을 막음
		
		if (session.getAttributeNames().hasMoreElements()) {
			String id = (String)session.getAttribute("loginId");
			String name = (String)session.getAttribute("loginName");
			int age = (Integer)session.getAttribute("loginAge"); // 다운캐스팅
			
			log.info("로그인 아이디 : {}", id);
			log.info("로그인 이름 : {}", name);
			log.info("로그인 나이 : {}", age);
		
		} else {
			log.info("세션 정보가 저장되지 않았습니다.");
		
		}
		return "redirect:/";
		
	}
	
	@GetMapping("/sessionDel")
	public String sessionDel(HttpSession session) {
//		session.removeAttribute("loginId");
//		session.removeAttribute("loginName");
//		session.removeAttribute("loginAge");
		
		session.invalidate(); // 모든 세션 한 번에 삭제 
		
		return "redirect:/";
	}
}
