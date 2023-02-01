package com.example.mapper;

import java.util.List;

import com.example.domain.Board;

public interface BoardMapper {
	
	List<Board> selectBoardList();
	
	Board selectBoard(int boardSeq);
	
	void insertBoard(Board board);
	
	void deleteBoard(int boardSeq);

	void updateBoard(Board board);
	
}
