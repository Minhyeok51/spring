package example.demo002.servletMVCdemo.mvc.org2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import example.demo002.servletMVCdemo.mvc.Controller2;
import example.demo002.servletMVCdemo.mvc.DemoView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="servletFrontController2" , urlPatterns ="/controller/org2/*")
public class ServletFrontController2 extends HttpServlet{
	
	private Map<String, Controller2> map = new HashMap<>();
						//Map에 부모인 인터페이스를 담아둬 상속받은 나머지 컨트롤러들도 모두 포함시킨다
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//uri
		//map -> controller
		//controller process
		//response(req,resp) dispatcher..
		String uri = req.getRequestURI();
		Controller2 controller = map.get(uri);
		if(controller == null) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		DemoView dv = controller.process(req, resp);
		dv.render(req, resp);
	}
	public ServletFrontController2() {
		map.put("/controller/org2/new", new DemoUnitNewController2());
		map.put("/controller/org2/insert", new DemoUnitInsertController2());
		map.put("/controller/org2/select", new DemoUnitSelectController2());
	}
}
