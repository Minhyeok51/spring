package example.demo002.servletMVCdemo.mvc.org3;

import java.io.IOException;
import java.util.Map;

import example.demo002.servletMVCdemo.dto.DemoUnit;
import example.demo002.servletMVCdemo.mvc.Controller3;
import example.demo002.servletMVCdemo.mvc.DemoModelView;
import example.demo002.servletMVCdemo.mvc.DemoView;
import example.demo002.servletMVCdemo.repository.DemoUnitArrRepository;


public class DemoUnitInsertController3 implements Controller3{
	
	DemoUnitArrRepository duRepository = DemoUnitArrRepository.getInstance();

	@Override
	public DemoModelView process(Map<String, String> params) {
		// TODO Auto-generated method stub
		String id = params.get("id");
		String name = params.get("name");

		DemoUnit du = new DemoUnit(id,name);
		duRepository.insert(du);
		
		DemoModelView dmv = new DemoModelView("demoUnitInserted");
		Map<String,Object> model = dmv.getModel();
		model.put("demoUnit", model);
		
		return dmv;
	}

	
	
	}


