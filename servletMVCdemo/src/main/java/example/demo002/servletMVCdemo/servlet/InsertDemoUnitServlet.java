package example.demo002.servletMVCdemo.servlet;

import java.io.IOException;

import example.demo002.servletMVCdemo.dto.DemoUnit;
import example.demo002.servletMVCdemo.repository.DemoUnitArrRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="saveDemoUnitServlet" , urlPatterns ="/demounit/insert")
public class InsertDemoUnitServlet extends HttpServlet{
	
	DemoUnitArrRepository duRepository = DemoUnitArrRepository.getInstance();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		System.out.println("insert 메소드 수행");
		
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		
		DemoUnit du = new DemoUnit(id,name);
		
		DemoUnit duIn = duRepository.insert(du);
		
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
//		resp.getWriter().write(duIn.getNo() + " " + duIn.getId() + " " + duIn.getName());
		resp.getWriter().write(
				"<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Document</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <h1>저장 성공</h1>\r\n"
				+ "    <ul>\r\n"
				+ "        <li>no"+duIn.getNo()+"</li>\r\n"
				+ "        <li>id"+duIn.getId()+"</li>\r\n"
				+ "        <li>name"+duIn.getName()+"</li>\r\n"
				+ "    </ul>\r\n"
				+ "</body>\r\n"
				+ "</html>"
				);
	}
}

