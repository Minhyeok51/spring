package example.demo002.servletMVCdemo.mvc.org1;

import java.io.IOException;
import java.util.List;

import example.demo002.servletMVCdemo.dto.DemoUnit;
import example.demo002.servletMVCdemo.mvc.Controller1;
import example.demo002.servletMVCdemo.repository.DemoUnitArrRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DemoUnitSelectController implements Controller1{
	
	DemoUnitArrRepository duRepository = DemoUnitArrRepository.getInstance();
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<DemoUnit> unitList =  duRepository.selectAll();
		req.setAttribute("unitList", unitList);
		
		String view = "/WEB-INF/views/demoUnitList.jsp";
		RequestDispatcher rDispatcher = req.getRequestDispatcher(view);
		
		rDispatcher.forward(req, resp);
	}

}
