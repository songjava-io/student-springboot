package com.example.domain;

import lombok.Data;

@Data
public class Member {
	
	private int memberSeq;
	private String account;
	private String password;
	private String nickname;
	private String profileImagePath;
	private String profileImageName;
	private String joinDate;
	
}
