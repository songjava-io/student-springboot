package kr.songjava.web.service;

import org.springframework.stereotype.Service;

import kr.songjava.web.domain.Member;
import kr.songjava.web.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;

/**
 * 회원 서비스
 * @author youtube
 */
@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberMapper memberMapper;
	
	/**
	 * 회원 계정 사용여부
	 * @param account
	 * @return
	 */
	public boolean isUseAccount(String account) {
		int count = memberMapper.selectMemberAccountCount(account);
		return count > 0;
	}
	
	/**
	 * 회원 등록
	 * @param member
	 */
	public void save(Member member) {
		memberMapper.insertMember(member);
	}
	
}
