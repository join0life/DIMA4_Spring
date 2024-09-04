package com.kdigital.ajaxtest.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {
	
	// 첫 번째 요청
	@GetMapping("/ajaxReq1")
	@ResponseBody
	public String ajaxReq1() {
		System.out.println("Req1 요청 수신");
		
		return "OK"; // html이 아니라 OK라는 문자열을 내보내는 것, forwarding 방식이 아님(@ResponseBody 추가)
	}
	
	// 두 번째 요청
	@GetMapping("/ajaxReq2")
	@ResponseBody
	public Map<String, String> ajaxReq2(
			@RequestParam(name="name") String name
			, @RequestParam(name="phone") String phone
			) {
		
		Map<String, String> map = new HashMap<>();
		map.put("name", name+"님");
		map.put("phone", phone);
		
		return map;
	}
	
	// 세 번째 요청
    @GetMapping("/ajaxReq3")
    @ResponseBody
    public List<String> ajaxReq3(){
       List<String> list = Arrays.asList("염버니", "루카리오", "누리레느", "밀로틱", "리자몽");
       
       return list;   
    }
}
