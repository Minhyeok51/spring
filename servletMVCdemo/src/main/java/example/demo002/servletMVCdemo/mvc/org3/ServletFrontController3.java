package example.demo002.servletMVCdemo.mvc.org3;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import example.demo002.servletMVCdemo.mvc.Controller3;
import example.demo002.servletMVCdemo.mvc.DemoModelView;
import example.demo002.servletMVCdemo.mvc.DemoView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="servletFrontController3" , urlPatterns ="/controller/org3/*")
public class ServletFrontController3 extends HttpServlet{
	
	private Map<String, Controller3> map = new HashMap<>();
						//Map에 부모인 인터페이스를 담아둬 상속받은 나머지 컨트롤러들도 모두 포함시킨다
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uri = req.getRequestURI();
		Controller3 controller = map.get(uri);
		
		Map<String, String> paramsMap = new HashMap<>();
		Enumeration<String> params = req.getParameterNames();
//		paramsMap.put("key", "value")
//		paramsMap.put("파라미터key", "파라미터value")
		
		while(params.hasMoreElements()) {
			String name = params.nextElement();
			String value = req.getParameter(name);
			paramsMap.put(name, value);
		}
		DemoModelView dmv = controller.process(paramsMap);
		
		DemoView dv = viewResolver(dmv.getView()); //demoUnitList만 들어간 dmv.getView()
//		DemoView dv = new DemoView(dmv.getView());

		//		dv.render(req, resp);
		dv.render(req, resp, dmv.getModel());
	}
	public ServletFrontController3() {
		map.put("/controller/org3/new", new DemoUnitNewController3());
		map.put("/controller/org3/insert", new DemoUnitInsertController3());
		map.put("/controller/org3/select", new DemoUnitSelectController3());
	}
	
	private DemoView viewResolver(String view) {//경로가 바뀌어도 쉽게 수정가능 
		view = "/WEB-INF/views/" + view + ".jsp";
		DemoView dv = new DemoView(view);
		return dv;
		
	}
}
