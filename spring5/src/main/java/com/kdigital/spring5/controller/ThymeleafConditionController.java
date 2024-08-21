package com.kdigital.spring5.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kdigital.spring5.dto.Friend;

@Controller
public class ThymeleafConditionController {
	@GetMapping("/display/condition")
	public String condition(Model model) {
		// 일반 객체 
		Friend friend
		= new Friend("홍길동", 25, "010-1111-2222", LocalDate.of(2003, 12, 5), true);
		
		// Iterable 데이터
		List<String> list = Arrays.asList("사과", "배", "딸기", "복숭아", "포도", "오렌지");
		List<Integer> nList = new ArrayList<>();
		for(int i=1; i<=20; ++i)
			nList.add(i*3);
		
		
		// Map 데이터
		Map<Integer, Friend> map = new HashMap<>();
		map.put(10, new Friend("손오공", 25, "010", LocalDate.of(2000, 12, 25), true));
		map.put(20, new Friend("저팔계", 30, "011", LocalDate.of(1994, 11, 1), false));
		map.put(30, new Friend("손오공", 21, "019", LocalDate.of(2003, 1, 5), true));
		
		model.addAttribute("friend", friend);
		model.addAttribute("list", list);
		model.addAttribute("nList", nList);
		model.addAttribute("map", map);
		
		return "thyme_condition";
	}
}
