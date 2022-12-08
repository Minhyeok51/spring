package example.sample.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import example.sample.project.validation.form.LoginForm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login(Model model) {
		LoginForm loginForm = new LoginForm();
		model.addAttribute("loginForm", loginForm);
		
		return "login/login";
	}
	
	@PostMapping("/login")
	public String doLogin(@ModelAttribute LoginForm loginForm,
			BindingResult bindingResult) {
		log.info("loginForm {}", loginForm);
		
		validateLoginForm(loginForm, bindingResult);
		
		//if()
		
		return "";
	}
	
	public void validateLoginForm(LoginForm loginForm,Errors errors) {
		if(!StringUtils.hasText(loginForm.getLoginId())) {
			errors.rejectValue("loginId", null, "아이디 필수 입력");
		}
		if(!StringUtils.hasText(loginForm.getPassword())) {
			errors.rejectValue("password", null, "비밀번호 필수 입력.");
		}
	}
}
