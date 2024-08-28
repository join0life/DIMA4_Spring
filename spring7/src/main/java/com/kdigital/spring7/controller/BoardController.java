package com.kdigital.spring7.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kdigital.spring7.dto.BoardDTO;
import com.kdigital.spring7.service.BoardService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	
	final BoardService boardService;
	
	/**
	 * 게시글 목록 조회를 위해 DB에 요청처리
	 * 1) index에서 넘어올 경우에는 searchItem/searchWord가 없는 상태이므로 기본값 세팅
	 * 2) List에서 직접 입력해서 넘어올 경우 searchItem/searchWord가 있으므로 그 값을 사용
	 * 검색기능 추가
	 * @param model
	 * @return
	 */
	@GetMapping("/boardList")
	public  String boardList(@RequestParam(name="searchItem", defaultValue="boardTitle") String searchItem
			, @RequestParam(name="searchWord", defaultValue="") String searchWord 
			, Model model) {
//		log.info("searchItem : {}", searchItem);
//		log.info("searchWord : {}", searchWord);
		
		List<BoardDTO> list = boardService.selectAll(searchItem, searchWord); // 글 목록 요청
		
		model.addAttribute("list", list);
		model.addAttribute("searchItem", searchItem);
		model.addAttribute("searchWord", searchWord);
		
		return "board/boardList";
	}
	/**
	 * 글쓰기 화면 요청
	 * @return
	 */
	@GetMapping("/boardWrite")
	public String boardWriter() {
		return "board/boardWrite";
	}
	
	/**
	 * DB에 글을 등록 처리하는 요청
	 * @return
	 */
	@PostMapping("/boardWrite")
	public String boardWriter(@ModelAttribute BoardDTO boardDTO) {
		log.info("클라이언트에서 전송된 데이터 : {}", boardDTO.toString());
		
		boardService.insertBoard(boardDTO);
		
		return "redirect:/board/boardList";
		
	}
	/**
	 * 글 자세히 보기
	 * 검색 후의 정보를 전달 받도록 함
	 * @param boardNum
	 * @param model
	 * @return
	 */
	@GetMapping("/boardDetail")
	public String boardDetail(@RequestParam(name="boardNum") Long boardNum
			, @RequestParam(name="searchItem", defaultValue="boardTitle") String searchItem
			, @RequestParam(name="searchWord", defaultValue="") String searchWord 
			, Model model) {
		BoardDTO board = boardService.selectOne(boardNum);
		
		// 조회수 증가
		boardService.incrementHitcount(boardNum);
		
		
		if(board == null) {
			return "redirect:/board/boardList";
		}
	
		model.addAttribute("board", board);
		// 검색 기능이 추가되면 계속 달고 다녀야 함
		model.addAttribute("searchItem", searchItem);
		model.addAttribute("searchWord", searchWord);
		
		return "board/boardDetail";
	}
	/**
	 * 전달받은 글번호(boardNum)를 받아 service로 전달
	 * @param boardNum
	 * @return
	 */
	@GetMapping("/boardDelete")
	public String boardDelete(
			@RequestParam(name="boardNum") Long boardNum
			, @RequestParam(name="searchItem", defaultValue="boardTitle") String searchItem
			, @RequestParam(name="searchWord", defaultValue="") String searchWord
			, RedirectAttributes rttr) {
		//service로 boardNum을 전달
		
		boardService.deleteOne(boardNum);

		rttr.addAttribute("searchItem", searchItem);
		rttr.addAttribute("searchWord", searchWord);
		
		// 추가할 내용 있음
		return "redirect:/board/boardList";
		
	}
	/**
	 * 게시글 수정을 위해 화면에 출력할 내용을 조회
	 * @param boardNum
	 * @return 
	 */
	@GetMapping("/boardUpdate")
	public String boardUpdate(
			@RequestParam(name="boardNum") Long boardNum
			, @RequestParam(name="searchItem", defaultValue="boardTitle") String searchItem
			, @RequestParam(name="searchWord", defaultValue="") String searchWord 
			, Model model) {
		log.info("수정할 글번호 : {} ", boardNum);
		//service로 boardNum을 전달
		
		BoardDTO board = boardService.selectOne(boardNum); // 조회
		log.info("수정할 글 : {} ", board.toString());
		
		model.addAttribute("board", board);
		
		model.addAttribute("searchItem", searchItem);
		model.addAttribute("searchWord", searchWord);
		
		return "board/boardUpdate";
		
	}
	/**
	 * 게시글 수정 요청
	 * @param boardNum
	 * @return 
	 */
	@PostMapping("/boardUpdate")
	public String boardUpdate(@ModelAttribute BoardDTO board
			, @RequestParam(name="searchItem", defaultValue="boardTitle") String searchItem
			, @RequestParam(name="searchWord", defaultValue="") String searchWord
			, RedirectAttributes rttr) {// GET 방식으로 주소 표시줄 위에 계속 데이터를 가지고 다니는 객체
		log.info("수정할 글 : {} ", board.toString());

		boardService.updateBoard(board);
		
		rttr.addAttribute("searchItem", searchItem);
		rttr.addAttribute("searchWord", searchWord);
		return "redirect:/board/boardList";		
	}
}
