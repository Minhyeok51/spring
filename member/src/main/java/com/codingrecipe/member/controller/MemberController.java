package com.codingrecipe.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingrecipe.member.dto.MemberDTO;
import com.codingrecipe.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	//객체 주입방식 3가지가 있는데
	//생성자 주입 방식 사용한다
	private final MemberService memberService;
	
	//회원가입 페이지 출력 요청
	@GetMapping("/member/save")
	public String saveForm() {
		return "save";
	}

	@PostMapping("/member/save")   //save.html파일에서 name에 적어준 변수명을 적어줌
	public String save(@ModelAttribute MemberDTO memberDTO
//			@RequestParam("memberEmail") String memberEmail,
//			@RequestParam("memberPassword") String memberPassword,
//			@RequestParam("memberName") String memberName
			) {
		System.out.println("MemberController.save");
		System.out.println("memberDTO = " + memberDTO);
		memberService.save(memberDTO);
		return "login";
	}

}
