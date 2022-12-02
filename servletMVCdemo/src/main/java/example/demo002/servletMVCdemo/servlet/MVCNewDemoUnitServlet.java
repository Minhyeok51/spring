package example.demo002.servletMVCdemo.servlet;

import java.io.IOException;

import example.demo002.servletMVCdemo.repository.DemoUnitArrRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="mVCNewDemoUnitServlet" , urlPatterns ="/mvc/demounit/new")
public class MVCNewDemoUnitServlet extends HttpServlet{
	
	DemoUnitArrRepository duRepository = DemoUnitArrRepository.getInstance();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		
		String view = "/WEB-INF/views/newDemoUnit.jsp";
		RequestDispatcher rDispatcher = req.getRequestDispatcher(view);
		//화면보여주기
		rDispatcher.forward(req, resp);
	}
}

