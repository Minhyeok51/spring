package example.demo002.servletMVCdemo.springmvc;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import example.demo002.servletMVCdemo.dto.DemoUnit;
import example.demo002.servletMVCdemo.repository.DemoUnitArrRepository;

@Controller
@RequestMapping("/spring/demoUnit/2")
public class SpringDemoUnitController2 {

	DemoUnitArrRepository duRepository = DemoUnitArrRepository.getInstance();

	@RequestMapping("/new")
	public String newDemoUnit() {

		return "newDemoUnit";
	}
//	@RequestMapping("/insert")
//	public String insertDemoUnit(HttpServletRequest req 
//								, HttpServletResponse resp
//								,Model model) {
//		String id = req.getParameter("id");
//		String name = req.getParameter("name");
//
//		DemoUnit du = new DemoUnit(id,name);
//
//		duRepository.insert(du);
//		
//		model.addAttribute("demoUnit", du);
//
//		return "demoUnitInserted";
//
//	}
								//newDemoUnit.jsp form태그 method
//	@RequestMapping(value = "/insert", method = RequestMethod.GET)//get방식이어야만 들어올수 있다.
//	@GetMapping("/insert") //이렇게 써도 됨 
//	@RequestMapping(value = "/insert", method = RequestMethod.POST)//post 방식이어야만 들어올수 있다.
	@PostMapping("/insert")
	public String insertDemoUnit(
			@RequestParam("id") String id,
			@RequestParam("name") String name,
			Model model) {
		
		//@RequestParam("id") String id  == String id = req.getParamaeter("id") 어노테이션을 활용하여 편하게 사용가능
		//Request req
		
		DemoUnit du = new DemoUnit(id,name);

		duRepository.insert(du);
		
		model.addAttribute("demoUnit", du);

		return "demoUnitInserted";

	}
	

//	@RequestMapping(value = "/select" , method = RequestMethod.POST)
	@PostMapping
	public String selectDemoUnit(Model model) {
		List<DemoUnit> unitList = duRepository.selectAll();
		model.addAttribute("unitList", unitList);

		return "demoUnitList";
	}

}
