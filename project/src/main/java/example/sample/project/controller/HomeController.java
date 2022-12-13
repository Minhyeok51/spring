package example.sample.project.controller;

import java.util.Enumeration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import example.sample.project.domain.Member;
import example.sample.project.repository.MemberRepository;
import example.sample.project.session.SessionManager;
import example.sample.project.session.SessionVar;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

//	private final ListMemberRepository memberRepository;
	private final MemberRepository memberRepository;
	private final SessionManager sessionManager;
	
	@GetMapping("/") 
	public String home(Model model
			,HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if(session == null) {//로그인이 안된사람
			return "home";
		}
		//이 위치. session 존재.
		Enumeration<String> sessionName = session.getAttributeNames();	
		while(sessionName.hasMoreElements()) {
			String name = sessionName.nextElement();
			log.info("session {}, {}",name,session.getAttribute(name));
		}
		log.info("{}, {}, {}, {}, {}"
				,session.getId()
				,session.getMaxInactiveInterval() //최대로 유지할수 있는 시간 1800초 30분
				,session.getCreationTime()
				,session.getLastAccessedTime()
				,session.isNew());
		Member member = (Member)session.getAttribute(SessionVar.LOGIN_MEMBER);
		if(member == null) {
			return "home";
		}
		model.addAttribute("member", member);
		return "loginHome";
	}
	
//	@GetMapping("/") //session 으로 처리하는 버전
	public String home_Session(Model model
			,HttpServletRequest req) {
		Member member = (Member)sessionManager.getSessionObject(req);
		if(member == null) {
			return "home";
		}
		model.addAttribute("member", member);
		return "loginHome";
	}
	
//	@GetMapping("/") 매핑되는거 없애고 그냥 일반 메소드로 두기
	public String home_cookie(Model model, @CookieValue(name="memberId", required=false) Integer memberId
			, @CookieValue(name="loginId", required=false) String loginId) {
		
		log.info("memberId ={}, loginId={}", memberId,loginId);
		if(memberId == null) {
			return "home";
		}
		
		Member member = memberRepository.selectById(memberId);
		if(member == null) {
			return "home";
		}
		model.addAttribute("member", member);
		return "loginHome";
	}
}
