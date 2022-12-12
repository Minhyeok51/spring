package example.sample.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import example.sample.project.domain.Member;
import example.sample.project.service.LoginService;
import example.sample.project.session.SessionManager;
import example.sample.project.session.SessionVar;
import example.sample.project.validation.form.LoginForm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
	
//	private final MemberRepository memberRepository; 이렇게 하지말고 Service이용하기
	private final LoginService loginService;
	private final SessionManager sessionManager;
	
	@GetMapping("/login")
	public String login(Model model) {
		LoginForm loginForm = new LoginForm();
		model.addAttribute("loginForm", loginForm);
		
		return "login/login";
	}
	
	@PostMapping("/login")
	public String doLogin(@ModelAttribute LoginForm loginForm,
			BindingResult bindingResult, HttpServletResponse resp
			, HttpServletRequest req
			,@RequestParam(name="redirectURL", defaultValue="/") String redirectURL) {
		log.info("loginForm {}", loginForm);
		
		validateLoginForm(loginForm, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "login/login";
		}
	 	Member member = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
	 	log.info("login {}", member);
	 	
	 	if(member == null) { //계정정보가 없거나, 비밀번호가 안맞거나 로그인 실패
	 		bindingResult.reject("loginForm", "아이디 or 비밀번호 불일치");
	 		return "login/login";
	 	}
	 	//정상적으로 로그인 처리가 된 경우
	 	//세션에 추가
//	 	sessionManager.create(member, resp);
	 	HttpSession session = req.getSession(); 
	 	//getSession - session이 있으면 가져오고 없으면 새로 만들어줌
//	 	session.setMaxInactiveInterval(540); //유효시간 540초
	 	session.setAttribute(SessionVar.LOGIN_MEMBER, member);
		
		return "redirect:" +redirectURL; //입력값이 없으면 "/",있으면 ex)"/foods/new"
	}
	
	@PostMapping("/login_old")
	public String doLogin_old(@ModelAttribute LoginForm loginForm,
			BindingResult bindingResult, HttpServletResponse resp
			, HttpServletRequest req) {
		log.info("loginForm {}", loginForm);
		
		validateLoginForm(loginForm, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "login/login";
		}
	 	Member member = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
	 	log.info("login {}", member);
	 	
	 	if(member == null) { //계정정보가 없거나, 비밀번호가 안맞거나 로그인 실패
	 		bindingResult.reject("loginForm", "아이디 or 비밀번호 불일치");
	 		return "login/login";
	 	}
	 	//정상적으로 로그인 처리가 된 경우
	 	//세션에 추가
//	 	sessionManager.create(member, resp);
	 	HttpSession session = req.getSession(); 
	 	//getSession - session이 있으면 가져오고 없으면 새로 만들어줌
//	 	session.setMaxInactiveInterval(540); //유효시간 540초
	 	session.setAttribute(SessionVar.LOGIN_MEMBER, member);
		
		return "redirect:/";
	}
	
	@PostMapping("/login_Session")
	public String doLogin_Session(@ModelAttribute LoginForm loginForm,
			BindingResult bindingResult, HttpServletResponse resp) {
		log.info("loginForm {}", loginForm);
		
		validateLoginForm(loginForm, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "login/login";
		}
		
//		Member member = memberRepository.selectByLoginId(loginForm.getLoginId());
//		if(member.getLoginId().equals(loginForm.getLoginId()) 
//				&& member.getPassword().equals(loginForm.getPassword())) {
//			return "redirect:/";
//		}
//			return "redirect:/login/login";
//		}	이 방법이 아니야
		
	 	Member member = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
		//Ctrl+Shift+o
	 	//Ctrl+1
	 	log.info("login {}", member);
	 	
	 	if(member == null) { //계정정보가 없거나, 비밀번호가 안맞거나 로그인 실패
	 		bindingResult.reject("loginForm", "아이디 or 비밀번호 불일치");
	 		return "login/login";
	 	}
		
	 	//정상적으로 로그인 처리가 된 경우
	 	
	 	//세션에 추가
	 	sessionManager.create(member, resp);
	 	//1)sessionMap 세션정보가 추가
	 	//2)resp -> sessionId Cookie값을 추가
	 	
		
		return "redirect:/";
	}
	
	@PostMapping("/login_cookie")
	public String doLogin_cookie(@ModelAttribute LoginForm loginForm,
			BindingResult bindingResult, HttpServletResponse resp) {
		log.info("loginForm {}", loginForm);
		
		validateLoginForm(loginForm, bindingResult);
		
		if(bindingResult.hasErrors()) {
			return "login/login";
		}
		Member member = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
		log.info("login {}", member);
		
		if(member == null) {//계정정보가 없거나, 비밀먼호가 안맞거나, 그럼 로그린 실해
			bindingResult.reject("loginForm", "아이디 or 비밀번호 불일치");
			return "login/login";
		}
		//정상적으로 로그인 처리가 된 경우
		
		//쿠키를 추가
		//쿠키 홈페이지에서 조작가능
		Cookie cookie = new Cookie("loginId", member.getLoginId());
		Cookie cookie2 = new Cookie("memberId", member.getId().toString());
		resp.addCookie(cookie);
		resp.addCookie(cookie2);
		
		return "redirect:/";
		}
	
	public void validateLoginForm(LoginForm loginForm,Errors errors) {
		if(!StringUtils.hasText(loginForm.getLoginId())) {
			errors.rejectValue("loginId", null, "아이디 필수 입력");
		}
		if(!StringUtils.hasText(loginForm.getPassword())) {
			errors.rejectValue("password", null, "비밀번호 필수 입력.");
		}
	}
	
	@PostMapping("/logout_cookie")
	public String logout_cookie(HttpServletResponse resp) {
		Cookie cookie = new Cookie("memberId", null);
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
		
		return "redirect:/";
	}
	
	@PostMapping("/logout")
	public String logout(HttpServletResponse resp, HttpServletRequest req) {
		//세션 사용자쪽에 tempSessionId Cookie
		//세션의 주체는 서버
		//서버에 있는 세션매니저에 저장된 정보를 삭제해야함 
		HttpSession session = req.getSession(false); //다시 세션이 만들어지지않게 막음
		if(session != null) {
			session.invalidate(); //session없애버리겠다.
		}
		return "redirect:/";
	}
	
	@PostMapping("/logout_Session")
	public String logout_Session(HttpServletResponse resp, HttpServletRequest req) {
		//세션 사용자쪽에 tempSessionId Cookie
		//세션의 주체는 서버
		//서버에 있는 세션매니저에 저장된 정보를 삭제해야함 
		sessionManager.remove(req);
		
		return "redirect:/";
	}
}
