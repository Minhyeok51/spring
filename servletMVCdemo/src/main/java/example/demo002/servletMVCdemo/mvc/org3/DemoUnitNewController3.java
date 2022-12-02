package example.demo002.servletMVCdemo.mvc.org3;

import java.io.IOException;
import java.util.Map;

import example.demo002.servletMVCdemo.mvc.Controller3;
import example.demo002.servletMVCdemo.mvc.DemoModelView;


public class DemoUnitNewController3 implements Controller3{

	@Override
	public DemoModelView process(Map<String, String> params) {
		// TODO Auto-generated method stub
		DemoModelView dmv = new DemoModelView("newDemoUnit");
		return dmv;
	}



}
