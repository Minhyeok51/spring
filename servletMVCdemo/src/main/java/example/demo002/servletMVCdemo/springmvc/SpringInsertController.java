package example.demo002.servletMVCdemo.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import example.demo002.servletMVCdemo.dto.DemoUnit;
import example.demo002.servletMVCdemo.mvc.org3.DemoUnitInsertController3;
import example.demo002.servletMVCdemo.repository.DemoUnitArrRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class SpringInsertController {
	
	DemoUnitArrRepository duRepository = DemoUnitArrRepository.getInstance();
	
	@RequestMapping("/spring/insert")
	public ModelAndView process(HttpServletRequest req , HttpServletResponse resp) {
		
		
		//요청데이터 ->저장 ->저장된 DemoUnit 넘겨줘야
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		DemoUnit du = new DemoUnit(id,name);
		
		duRepository.insert(du);
		
		ModelAndView mv = new ModelAndView("demoUnitInserted");
		mv.addObject("demoUnit", du);
		
		return mv;
	}
	
}
