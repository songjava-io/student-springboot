package kr.songjava.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.songjava.web.domain.Board;
import kr.songjava.web.service.BoardService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	
	final Logger logger = LoggerFactory.getLogger(getClass());

	private final BoardService boardService;
	
	@GetMapping
	public String list(Model model,
			@RequestParam(required = false) String query) 
			throws Exception {
		logger.info("BoardController list 실행...");
		// 검색조건 파라메터

		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("query", query);
		
		// 서비스를 호출해서 게시물 목록을 리턴 받
		List<Board> boardList = boardService.selectBoardList(paramMap);
		
		model.addAttribute("boardList", boardList);
		return "board/list";
	}
	
	
	/**
	 * 등록화면 
	 */
	@GetMapping("/{boardSeq}")
	public String detail(Model model, @PathVariable int boardSeq) {
		Board board = boardService.selectBoard(boardSeq);
		Assert.notNull(board, "게시글 정보가 없습니다.");
		model.addAttribute("board", board);
		return "/board/detail";
	}
	
	/**
	 * 등록화면 
	 */
	@GetMapping("/form")
	public void form() {
		
	}
	
	/**
	 * 수정화면 
	 */
	@GetMapping("/edit/{boardSeq}")
	public String edit(Model model, @PathVariable int boardSeq) {
		Board board = boardService.selectBoard(boardSeq);
		Assert.notNull(board, "게시글 정보가 없습니다.");
		model.addAttribute("board", board);
		return "/board/form";
	}
	
	/**
	 * 게시물 저장기능
	 */
	@PostMapping("/save")
	public String save(Board board) {
		// 기본 검증로직
		Assert.hasLength(board.getTitle(), "제목은 필수 입니다.");
		Assert.hasLength(board.getContents(), "내용은 필수 입니다.");
		// 게시물 저장
		boardService.save(board);
		// 목록 페이지로 이동
		return "redirect:/board";
	}
	
	/**
	 * 게시물 업데이트 기능 처리.
	 */
	@PostMapping("/update")
	public String update(Board form) {
		// 기본 검증로직
		Board board = boardService.selectBoard(form.getBoardSeq());
		Assert.notNull(board, "게시글 정보가 없습니다.");
		
		Assert.hasLength(form.getTitle(), "제목은 필수 입니다.");
		Assert.hasLength(form.getContents(), "내용은 필수 입니다.");
		// 게시물 저장
		boardService.save(form);
		// 목록 페이지로 이동
		return "redirect:/board";
	}
	
	

}
