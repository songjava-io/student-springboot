package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String edit(Model model, @PathVariable(required = true) int boardSeq) {
		logger.debug("edit");
		Board board = boardService.selectBoard(boardSeq);
		Assert.notNull(board, "게시글 정보가 없습니다.");
		// 게시물 상세정보 set
		model.addAttribute("board", board);
		// jsp를 호출
		return "/board/form";
	}
	
	/**
	 * 등록 / 업데이트 처리
	 * @param form
	 * @return
	 */
	@PostMapping("/save")
	public String save(@Validated BoardSaveForm form) {
		// 유효성 체크
		Board selectBoard = null;
		// 등록이 아닌 수정화면에서 요청인경우
		if (form.getBoardSeq() > 0) {
			// 기존에 등록된 데이터인지 조회
			selectBoard = boardService.selectBoard(form.getBoardSeq());	
		}
		// 존재하지 않는경우
		if (selectBoard == null) {
			// 게시물 등록 처리
			boardService.insertBoard(form);
		} else {
			boardService.updateBoard(form);
		}
		// 목록 화면으로 이동
		return "redirect:/board";
	}
	
	/**
	 * 게시글 삭제 처리
	 * @param model
	 * @param boardSeq
	 * @return
	 */
	@PostMapping("/delete")
	public HttpEntity<Boolean> delete(@RequestParam(required = true) int boardSeq) {
		logger.debug("delete");
		Board board = boardService.selectBoard(boardSeq);
		Assert.notNull(board, "게시글 정보가 없습니다.");
		// 삭제처리
		boardService.deleteBoard(boardSeq);
		return new ResponseEntity<Boolean>(HttpStatus.OK); 
	}
	
}
