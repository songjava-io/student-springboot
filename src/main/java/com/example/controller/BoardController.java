package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.controller.form.BoardSaveForm;
import com.example.domain.Board;
import com.example.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final BoardService boardService;
	
	/**
	 * 게시물 목록 조회 및 화면
	 * @param model
	 * @return
	 */
	@GetMapping
	public String list(Model model) {
		logger.debug("list");
		// 게시물 목록 조회 후 model에 boardList key로 저장
		model.addAttribute("boardList", boardService.selectBoardList());
		// jsp를 호출
		return "/board/list";
	}
	
	/**
	 * 게시글 상세화면 화면
	 * @param model
	 * @param boardSeq
	 * @return
	 */
	@GetMapping("/{boardSeq}")
	public String detail(Model model, @PathVariable(required = true) int boardSeq) {
		logger.debug("detail");
		Board board = boardService.selectBoard(boardSeq);
		Assert.notNull(board, "게시글 정보가 없습니다.");
		// 게시물 상세정보 set
		model.addAttribute("board", board);
		// jsp를 호출
		return "/board/detail";
	}
	
	/**
	 * 게시물 등록 화면
	 * @param model
	 * @return
	 */
	@GetMapping("/form")
	public String form(Model model) {
		// jsp를 호출
		return "/board/form";
	}
	
	/**
	 * 게시글 수정 화면
	 * @param model
	 * @param boardSeq
	 * @return
	 */
	@GetMapping("/edit/{boardSeq}")
	public String edit(Model model, @PathVariable int boardSeq) {
		// 게시물 상세정보 set
		model.addAttribute("board", boardService.selectBoard(boardSeq));
		// jsp를 호출
		return "/board/form";
	}
	
	/**
	 * 등록 처리
	 * @param form
	 * @return
	 */
	@PostMapping("/save")
	public String save(@Validated BoardSaveForm form, Authentication authentication) {
		boardService.save(form, authentication);
		// 목록 화면으로 이동
		return "redirect:/board";
	}
	
	
	/**
	 * 업데이트 처리
	 * @param form
	 * @return
	 */
	@PostMapping("/update")
	public String update(@Validated BoardSaveForm form) {
		boardService.update(form);
		// 상세화면으로 이동
		return "redirect:/board/" + form.getBoardSeq();
	}
	
	/**
	 * 게시글 삭제 처리
	 * @param boardSeq
	 * @return
	 */
	@PostMapping("/delete")
	@ResponseBody
	public HttpEntity<Boolean> delete(@RequestParam int boardSeq) {
		// 삭제처리
		boardService.delete(boardSeq);
		return new ResponseEntity<Boolean>(HttpStatus.OK); 
	}
	
}
