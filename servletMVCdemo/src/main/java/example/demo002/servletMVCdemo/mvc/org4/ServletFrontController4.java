package example.demo002.servletMVCdemo.mvc.org4;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import example.demo002.servletMVCdemo.mvc.Controller4;
import example.demo002.servletMVCdemo.mvc.DemoModelView;
import example.demo002.servletMVCdemo.mvc.DemoView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="servletFrontController4" , urlPatterns ="/controller/org4/*")
public class ServletFrontController4 extends HttpServlet{
	
	private Map<String, Controller4> map = new HashMap<>();
						//Map에 부모인 인터페이스를 담아둬 상속받은 나머지 컨트롤러들도 모두 포함시킨다
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uri = req.getRequestURI();
		Controller4 controller = map.get(uri);
		
		Map<String, String> paramsMap = new HashMap<>();
		Enumeration<String> params = req.getParameterNames();
//		paramsMap.put("key", "value")
//		paramsMap.put("파라미터key", "파라미터value")
		
		while(params.hasMoreElements()) {
			String name = params.nextElement();
			String value = req.getParameter(name);
			paramsMap.put(name, value);
		}
		Map<String, Object> model = new HashMap<>();
		String view = controller.process(paramsMap,model);
		
		DemoView dv = viewResolver(view);
		dv.render(req, resp, model);
	}
	public ServletFrontController4() {
		map.put("/controller/org4/new", new DemoUnitNewController4());
		map.put("/controller/org4/insert", new DemoUnitInsertController4());
		map.put("/controller/org4/select", new DemoUnitSelectController4());
	}
	
	private DemoView viewResolver(String view) {//경로가 바뀌어도 쉽게 수정가능 
		view = "/WEB-INF/views/" + view + ".jsp";
		DemoView dv = new DemoView(view);
		return dv;
		
	}
}
