package example.demo002.servletMVCdemo.servlet;

import java.io.IOException;

import example.demo002.servletMVCdemo.dto.DemoUnit;
import example.demo002.servletMVCdemo.repository.DemoUnitArrRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="mVCInsertDemoUnitServlet" , urlPatterns ="/mvc/demounit/insert")
public class MVCInsertDemoUnitServlet extends HttpServlet{
	
	DemoUnitArrRepository duRepository = DemoUnitArrRepository.getInstance();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		DemoUnit du = new DemoUnit(id,name);
		
		duRepository.insert(du);
		req.setAttribute("demoUnit", du);
		
		String view = "/WEB-INF/views/demoUnitInserted.jsp";
		RequestDispatcher rDispatcher = req.getRequestDispatcher(view);
		
		rDispatcher.forward(req, resp);
	}
}
