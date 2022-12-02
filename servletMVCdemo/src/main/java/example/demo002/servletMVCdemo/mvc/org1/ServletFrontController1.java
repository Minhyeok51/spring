package example.demo002.servletMVCdemo.mvc.org1;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import example.demo002.servletMVCdemo.mvc.Controller1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="servletFrontController1" , urlPatterns ="/controller/org1/*")
public class ServletFrontController1 extends HttpServlet{
	
	private Map<String, Controller1> map = new HashMap<>();
						//Map에 부모인 인터페이스를 담아둬 상속받은 나머지 컨트롤러들도 모두 포함시킨다
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uri = req.getRequestURI();
		
		Controller1 controller = map.get(uri);
		// /controller/org1/new
		controller.process(req, resp);
	}
	public ServletFrontController1() {
		map.put("/controller/org1/new", new DemoUnitNewController());
		map.put("/controller/org1/insert", new DemoUnitInsertController());
		map.put("/controller/org1/select", new DemoUnitSelectController());
	}
}
