package com.codingrecipe.member.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingrecipe.member.dto.MemberDTO;
import com.codingrecipe.member.service.MemberService;

import jakarta.servlet.http.HttpSession;
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

	@GetMapping("/member/login")
	public String loginForm() {
		return "login";
	}

	@PostMapping("/member/login")
	public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
		MemberDTO loginResult = memberService.login(memberDTO);
		if(loginResult != null) {
			//로그인 성공
			System.out.println("123123");
			session.setAttribute("loginEmail", loginResult.getMemberEmail());
			return "main";
		}else {
			//로그인 실패
			System.out.println("salkdms");
			return "login";
		}
	}

	@GetMapping("/member/")
	public String findAll(Model model) {
		List<MemberDTO> memberDTOList=memberService.findAll();
		//어떠한 html로 가져갈 데이터가 있다면 스프링에서 제공하는 model사용
		model.addAttribute("memberList", memberDTOList);
		return "list";
	}
	
	@GetMapping("/member/{id}")//이 경로에 있는 값을 취하겠다
	public String findById(@PathVariable Long id, Model model) {//경로상에있는 값을 가져온다
		MemberDTO memberDTO = memberService.findById(id);
		model.addAttribute("member", memberDTO);
		return "detail";
	}
	
	@GetMapping("/member/update")
	public String updateForm(HttpSession session, Model model) {
		String myEmail = (String) session.getAttribute("loginEmail");
		MemberDTO memberDTO = memberService.updateForm(myEmail);
		model.addAttribute("updateMember", memberDTO);
		return "update";
	}
	
	@PostMapping("/member/update")
	public String update(@ModelAttribute MemberDTO memberDTO) {
		memberService.update(memberDTO);
		//바로 return detail; 을 시키면 detail파일에 th붙은 부분은 생략되고나옴
		//왜냐 getMapping부분을 보면 id값을 받아서 model에 담아 리턴을 하기때문에
		//이것을 방지하기위해
/*컨트롤러 메소드가 끝나고 바로다시 다른메소드가 가지고있는 주소를 요청할 수 있음*/
		return "redirect:/member/"+memberDTO.getId();
	}
	
}
