package example.demo003.demo003.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SampleResponseController {
	@RequestMapping("/viewMapping")
	public String responseViewMapping() {
		return "unitList";
	}
	@RequestMapping("/viewMapping2")
	public String responseViewMapping2() {
		return "demounit/unitList";
	}
	@RequestMapping("/viewMapping3")
	public ModelAndView responseViewMapping3() {
		ModelAndView mv = new ModelAndView("demounit/sampleObject");
		
		mv.addObject("id", "mvAddId");
		mv.addObject("name", "mvAddName");
		
		return mv;
	}
	@RequestMapping("/viewMapping4")
	public String responseViewMapping4(Model model) {
		
		model.addAttribute("id", "mvAddId");
		model.addAttribute("name", "mvAddName");
		return "demounit/sampleObject";
	}
}
