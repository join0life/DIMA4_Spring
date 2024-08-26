package com.kdigital.test3.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kdigital.test3.dto.FitnessDTO;
import com.kdigital.test3.service.FitnessService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class FitnessController {
	
	final FitnessService service;
	
	/**
	 * 화면 요청
	 * @return
	 */
	@GetMapping("/insertMember")
	public String insertMember() {
		return "insertMember";
	}
	
	@PostMapping("/insertMember")
	public String insertMember(
			@ModelAttribute FitnessDTO fitness
			) {
		
		// 저장할 수 있도록 service에 데이터를 넘김
		log.info("Controller");
		
		service.insert(fitness);
		
		return "redirect:/";	// 브라우저한테 / 요청을 함	==> redirect
	}
	
	@GetMapping("/listMember")
	public String listFitness(Model model) {
		List<FitnessDTO> list = service.selectAll();
		model.addAttribute("list", list);
		return "listMember";
	}
	
	@GetMapping("/deleteOne")
	public String deleteFitness(@RequestParam(name="seq") Integer seq) {
		
		service.deleteOne(seq);
		
		return("redirect:/listMember");
	}
	
	@GetMapping("/updateOne")
	public String updateFitness(@RequestParam(name="seq") Integer seq, Model model) {
		FitnessDTO fitness = service.selectOne(seq);
		model.addAttribute("fitness", fitness);
		return "updateMember";
	}
	
	@PostMapping("/updateOne")
	public String updateFitness(@ModelAttribute FitnessDTO fitness) {
		
		service.updateOne(fitness);
		
		return "redirect:/listMember";
	}
}
