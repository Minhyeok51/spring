package example.demo002.servletMVCdemo.servlet;

import java.io.IOException;
import java.util.List;

import example.demo002.servletMVCdemo.dto.DemoUnit;
import example.demo002.servletMVCdemo.repository.DemoUnitArrRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="mVCSelectDemoUnitServlet" , urlPatterns ="/mvc/demounit/select")
public class MVCSelectDemoUnitServlet extends HttpServlet{
	
	DemoUnitArrRepository duRepository = DemoUnitArrRepository.getInstance();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		
		List<DemoUnit> unitList =  duRepository.selectAll();
		req.setAttribute("unitList", unitList);
		
		String view = "/WEB-INF/views/demoUnitList.jsp";
		RequestDispatcher rDispatcher = req.getRequestDispatcher(view);
		
		rDispatcher.forward(req, resp);
	}
}


