package com.example.mapper;

import com.example.domain.Member;

public interface MemberMapper {
	
	int selectMemberAccountCount(String account);
	
	Member selectMemberAccount(String account);

	void insertMember(Member member);
	
}
