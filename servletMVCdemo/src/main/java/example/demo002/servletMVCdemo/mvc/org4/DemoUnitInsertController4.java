package example.demo002.servletMVCdemo.mvc.org4;

import java.io.IOException;
import java.util.Map;

import example.demo002.servletMVCdemo.dto.DemoUnit;
import example.demo002.servletMVCdemo.mvc.Controller3;
import example.demo002.servletMVCdemo.mvc.Controller4;
import example.demo002.servletMVCdemo.mvc.DemoModelView;
import example.demo002.servletMVCdemo.mvc.DemoView;
import example.demo002.servletMVCdemo.repository.DemoUnitArrRepository;


public class DemoUnitInsertController4 implements Controller4{

	DemoUnitArrRepository duRepository = DemoUnitArrRepository.getInstance();

	@Override
	public String process(Map<String, String> params, Map<String, Object> model) {
		// TODO Auto-generated method stub
		
		String id = params.get("id");
		String name = params.get("name");
		
		DemoUnit du = new DemoUnit(id, name);
		duRepository.insert(du);
		
		model.put("demoUnit", du);
		
		return "demoUnitInserted";
	}


}






