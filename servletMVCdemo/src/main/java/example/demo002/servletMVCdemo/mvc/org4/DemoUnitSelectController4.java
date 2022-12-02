package example.demo002.servletMVCdemo.mvc.org4;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import example.demo002.servletMVCdemo.dto.DemoUnit;
import example.demo002.servletMVCdemo.mvc.Controller2;
import example.demo002.servletMVCdemo.mvc.Controller3;
import example.demo002.servletMVCdemo.mvc.Controller4;
import example.demo002.servletMVCdemo.mvc.DemoModelView;
import example.demo002.servletMVCdemo.mvc.DemoView;
import example.demo002.servletMVCdemo.repository.DemoUnitArrRepository;


public class DemoUnitSelectController4 implements Controller4{
	
	DemoUnitArrRepository duRepository = DemoUnitArrRepository.getInstance();

	@Override
	public String process(Map<String, String> params, Map<String, Object> model) {
		// TODO Auto-generated method stub
		List<DemoUnit> unitList = duRepository.selectAll();
		
		model.put("unitList", unitList);
		
		return "demoUnitList";
	}

	
	

}
