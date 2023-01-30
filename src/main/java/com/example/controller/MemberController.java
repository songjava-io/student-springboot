package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.controller.form.MemberSaveForm;
import com.example.service.MemberService;

import lombok.RequiredArgsConstructor;

/**
 * 회원 컨트롤러
 */
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final MemberService memberService;
	
	/**
	 * 정보입력 화면
	 * @param model
	 * @return
	 */
	@GetMapping("/form")
	public String form(Model model) {
		return "/member/form";
	}
	
	/**
	 * 가입 처리
	 * @param form
	 * @return
	 */
	@PostMapping("/join")
	@ResponseBody
	public HttpEntity<Boolean> join(@Validated MemberSaveForm form) {
		// 계정 중복체크
		boolean isUseAccount = memberService.isUseAccount(form.getAccount());
		logger.info("isUseAccount : {}", isUseAccount);
		Assert.state(!isUseAccount, "이미 사용중인 계정 입니다.");
	
		logger.info("form profileImage : {}", form.getProfileImage());
		// 회원가입 처리
		memberService.insertMember(form);
		// 가입 완료 화면
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	/**
	 * 가입완료 화면
	 * @param model
	 * @return
	 */
	@GetMapping("/join-complete")
	public String joinComplete(Model model) {
		return "/member/join-complete";
	}
	
}
