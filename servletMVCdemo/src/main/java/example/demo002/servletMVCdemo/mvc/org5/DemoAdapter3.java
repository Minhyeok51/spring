package example.demo002.servletMVCdemo.mvc.org5;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import example.demo002.servletMVCdemo.mvc.Controller3;
import example.demo002.servletMVCdemo.mvc.DemoAdapter;
import example.demo002.servletMVCdemo.mvc.DemoModelView;
import example.demo002.servletMVCdemo.mvc.DemoView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DemoAdapter3 implements DemoAdapter{

	@Override
	public DemoModelView process(HttpServletRequest req, HttpServletResponse resp, Object obj)
			throws ServletException, IOException {
		Controller3 controller = (Controller3)obj;
		Map<String, String> paramsMap = reqToParamsMap(req);
		DemoModelView dmv = controller.process(paramsMap);
		
		return dmv;
	}
	
	public Map<String,String> reqToParamsMap(HttpServletRequest req){
		Map<String, String> paramsMap = new HashMap<>();
		Enumeration<String> params = req.getParameterNames();
		
		while(params.hasMoreElements()) {
			String name = params.nextElement();
			String value = req.getParameter(name);
			paramsMap.put(name, value);
		}
		return paramsMap;
	}
	@Override
	public boolean isHandled(Object obj) {
		//부모인터페이스 타입 으로 자식 객체 들어오는
		boolean result = (obj instanceof Controller3);
		
		return result;
	}

	

}
