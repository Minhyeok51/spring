package example.demo002.servletMVCdemo.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringNewController {
	
	@RequestMapping("/spring/new")
	public ModelAndView process() {
		ModelAndView mv = new ModelAndView("newDemoUnit");
		return mv;
	}
	
}
