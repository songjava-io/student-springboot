package com.example.domain;

import lombok.Data;

@Data
public class Board {
	
	private int boardSeq;
	private String boardType;
	private String title;
	private String contents;
	private int memberSeq;
	private String regDate;
	private String userName; // 회원 이름
	
}
