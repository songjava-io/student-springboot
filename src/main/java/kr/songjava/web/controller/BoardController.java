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
	
	@GetMapping("/list")
	public void list(Model model,
			@RequestParam(required = false) String query) 
			throws Exception {
		logger.info("BoardController list 실행...");
		// 검색조건 파라메터

		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("query", query);
		
		// 서비스를 호출해서 게시물 목록을 리턴 받
		List<Board> boardList = boardService.selectBoardList(paramMap);
		
		model.addAttribute("boardList", boardList);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
