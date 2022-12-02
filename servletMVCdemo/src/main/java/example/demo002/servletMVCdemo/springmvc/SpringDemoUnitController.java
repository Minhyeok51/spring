package example.demo002.servletMVCdemo.springmvc;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import example.demo002.servletMVCdemo.dto.DemoUnit;
import example.demo002.servletMVCdemo.repository.DemoUnitArrRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/spring/demoUnit")
public class SpringDemoUnitController {

	DemoUnitArrRepository duRepository = DemoUnitArrRepository.getInstance();

	@RequestMapping("/new")
	public ModelAndView newDemoUnit() {

		return new ModelAndView("newDemoUnit");
	}
	@RequestMapping("/insert")
	public ModelAndView insertDemoUnit(HttpServletRequest req , HttpServletResponse resp) {
		String id = req.getParameter("id");
		String name = req.getParameter("name");

		DemoUnit du = new DemoUnit(id,name);

		duRepository.insert(du);

		ModelAndView mv = new ModelAndView("demoUnitInserted");
		mv.addObject("demoUnit", du);

		return mv;

	}
	@RequestMapping("/select")
	public ModelAndView selectDemoUnit() {
		List<DemoUnit> unitList = duRepository.selectAll();

		ModelAndView mv = new ModelAndView("demoUnitList");
		mv.addObject("unitList", unitList);

		return mv;
	}
}
