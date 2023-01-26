package kr.songjava.web.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.songjava.web.domain.Member;
import kr.songjava.web.form.MemberSaveForm;
import kr.songjava.web.service.MemberService;
import lombok.RequiredArgsConstructor;

/**
 * 회원 컨트롤러
 */
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	/**
	 * 회원 정보 입력 화면
	 */
	@GetMapping("/form")
	public void form() {
		
	}
	
	/**
	 * 회원 가입 처리.
	 * @param form
	 * @return
	 */
	@PostMapping("/save")
	@ResponseBody
	public HttpEntity<Boolean> save(MemberSaveForm form) {
		// form -> member 로 변환
		Member member = Member.builder()
			.account(form.getAccount())
			.password(form.getPassword())
			.nickname(form.getNickname())
			.build();
		// 등록 처리
		memberService.save(member);
		return ResponseEntity.ok(true);
	}
	
	
	
	
	
	
	
	
}
