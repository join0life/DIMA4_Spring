package com.kdigital.ajaxtest.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kdigital.ajaxtest.dto.CustomerDTO;

@Controller
public class SecondController {
	
	@GetMapping("/secondpage")
	public String secondpage() {
		return "second";
	}
	
	@GetMapping("/receive")
	@ResponseBody
	public List<CustomerDTO> receive() {
		List<CustomerDTO> list = Arrays.asList(
				new CustomerDTO("홍길동", "hong@naver.com", "010-1111-2223")
				, new CustomerDTO("임꺽정", "lim@naver.com", "010-2345-2354")
				, new CustomerDTO("전우치", "jeon@naver.com", "010-2312-3333")
				, new CustomerDTO("손오공", "ogong@naver.com", "010-4444-5555")
				, new CustomerDTO("사오정", "ojeong@naver.com", "010-2333-3322")
		);
		return list;
	}
}
