package example.demo002.servletMVCdemo.mvc.org2;

import java.io.IOException;

import example.demo002.servletMVCdemo.dto.DemoUnit;
import example.demo002.servletMVCdemo.mvc.Controller2;
import example.demo002.servletMVCdemo.mvc.DemoView;
import example.demo002.servletMVCdemo.repository.DemoUnitArrRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DemoUnitInsertController2 implements Controller2{
	
	DemoUnitArrRepository duRepository = DemoUnitArrRepository.getInstance();
	@Override
	public DemoView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		DemoUnit du = new DemoUnit(id,name);
		
		duRepository.insert(du);
		req.setAttribute("demoUnit", du);
		
		String view = "/WEB-INF/views/demoUnitInserted.jsp";
		DemoView dv = new DemoView(view);
//		RequestDispatcher rDispatcher = req.getRequestDispatcher(view);
//		
//		rDispatcher.forward(req, resp);
		return dv;
	}

}
