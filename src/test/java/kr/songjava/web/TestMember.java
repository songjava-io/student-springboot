package kr.songjava.web;

import kr.songjava.web.domain.Member;

public class TestMember {

	public void test1() {
		
		Member member = new Member();
		member.setAge("17");
		member.setName("송영문");
		
		Member member2 = Member.builder()
			.name("송영문")
			.age("17")
			.build();
		
		System.out.println(member2);
		
		
	}
}
