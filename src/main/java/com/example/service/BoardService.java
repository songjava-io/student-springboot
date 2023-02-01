package com.example.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.controller.form.BoardSaveForm;
import com.example.domain.Board;
import com.example.mapper.BoardMapper;
import com.example.security.userdetails.SecurityUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardMapper boardMapper;

	/**
	 * 게시물 목록 조회 후 리턴
	 * 
	 * @return
	 */
	public List<Board> selectBoardList() {
		return boardMapper.selectBoardList();
	}

	public Board selectBoard(int boardSeq) {
		return boardMapper.selectBoard(boardSeq);
	}

	public void save(BoardSaveForm form, Authentication authentication) {
		SecurityUserDetails details = (SecurityUserDetails) 
			authentication.getPrincipal();
		Board board = new Board();
		board.setBoardSeq(form.getBoardSeq());	
		board.setBoardType(form.getBoardType());
		board.setTitle(form.getTitle());
		board.setContents(form.getContents());
		board.setUserName(details.getNickname());
		board.setMemberSeq(details.getMemberSeq());
		boardMapper.insertBoard(board);
	}
	
	public void update(BoardSaveForm form) {
		Board board = new Board();
		board.setBoardSeq(form.getBoardSeq());	
		board.setBoardType(form.getBoardType());
		board.setTitle(form.getTitle());
		board.setContents(form.getContents());
		boardMapper.updateBoard(board);
	}

	public void delete(int boardSeq) {
		boardMapper.deleteBoard(boardSeq);
	}

}
