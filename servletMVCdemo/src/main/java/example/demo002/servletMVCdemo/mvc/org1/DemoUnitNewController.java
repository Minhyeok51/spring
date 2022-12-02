package example.demo002.servletMVCdemo.mvc.org1;

import java.io.IOException;

import example.demo002.servletMVCdemo.mvc.Controller1;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DemoUnitNewController implements Controller1{

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String view = "/WEB-INF/views/newDemoUnit.jsp";
		RequestDispatcher rDispatcher = req.getRequestDispatcher(view);
		
		rDispatcher.forward(req, resp);
	}

}
