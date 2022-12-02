package example.demo002.servletMVCdemo.mvc.org4;

import java.io.IOException;
import java.util.Map;

import example.demo002.servletMVCdemo.mvc.Controller3;
import example.demo002.servletMVCdemo.mvc.Controller4;
import example.demo002.servletMVCdemo.mvc.DemoModelView;


public class DemoUnitNewController4 implements Controller4{

	@Override
	public String process(Map<String, String> params, Map<String, Object> model) {
		// TODO Auto-generated method stub
		//return String -> view name
		return "newDemoUnit";
	}



}
