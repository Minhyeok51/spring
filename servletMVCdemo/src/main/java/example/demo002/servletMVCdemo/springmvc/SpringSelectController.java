package example.demo002.servletMVCdemo.springmvc;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import example.demo002.servletMVCdemo.dto.DemoUnit;
import example.demo002.servletMVCdemo.repository.DemoUnitArrRepository;

@Controller
public class SpringSelectController {
	
	DemoUnitArrRepository duRepository = DemoUnitArrRepository.getInstance();
	
	@RequestMapping("/spring/select")
	public ModelAndView process() {
		List<DemoUnit> unitList = duRepository.selectAll();
		
		ModelAndView mv = new ModelAndView("demoUnitList");
		mv.addObject("unitList", unitList);
		
		return mv;
	}
}
