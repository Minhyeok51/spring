package example.demo002.servletMVCdemo.mvc.org2;

import java.io.IOException;

import example.demo002.servletMVCdemo.dto.DemoUnit;
import example.demo002.servletMVCdemo.mvc.Controller2;
import example.demo002.servletMVCdemo.mvc.DemoView;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DemoUnitNewController2 implements Controller2{

	@Override
	public DemoView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		return new DemoView("/WEB-INF/views/newDemoUnit.jsp"); 한줄로 끝낼수있다
		String view = "/WEB-INF/views/newDemoUnit.jsp";
		DemoView dv = new DemoView(view);
		return dv;
	}

}
