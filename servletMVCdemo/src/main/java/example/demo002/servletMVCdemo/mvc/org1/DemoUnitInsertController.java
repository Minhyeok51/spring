package example.demo002.servletMVCdemo.mvc.org1;

import java.io.IOException;

import example.demo002.servletMVCdemo.dto.DemoUnit;
import example.demo002.servletMVCdemo.mvc.Controller1;
import example.demo002.servletMVCdemo.repository.DemoUnitArrRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DemoUnitInsertController implements Controller1{
	
	DemoUnitArrRepository duRepository = DemoUnitArrRepository.getInstance();
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
