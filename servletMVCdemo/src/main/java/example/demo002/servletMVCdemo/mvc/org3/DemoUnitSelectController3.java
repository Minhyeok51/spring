package example.demo002.servletMVCdemo.mvc.org3;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import example.demo002.servletMVCdemo.dto.DemoUnit;
import example.demo002.servletMVCdemo.mvc.Controller2;
import example.demo002.servletMVCdemo.mvc.Controller3;
import example.demo002.servletMVCdemo.mvc.DemoModelView;
import example.demo002.servletMVCdemo.mvc.DemoView;
import example.demo002.servletMVCdemo.repository.DemoUnitArrRepository;


public class DemoUnitSelectController3 implements Controller3{
	
	DemoUnitArrRepository duRepository = DemoUnitArrRepository.getInstance();

	@Override
	public DemoModelView process(Map<String, String> params) {
		// TODO Auto-generated method stub
		//DemoModelView
		//1.어던 뷰인지 파일명 필요
		DemoModelView dmv = new DemoModelView("demoUnitList");
		//2.전달할 데이터가 모델에 들어가야 함 <-- repository.selectAll()
		List<DemoUnit> demoUnitList =  duRepository.selectAll();
		//3.데모모델뷰를 반환 -> 반환받은 쪽에서continue
//		Map<String, Object> model = dmv.getModel();
//		model.put("unitList", demoUnitList);
		dmv.getModel().put("unitList", demoUnitList);
		return dmv;
	}
	

}
