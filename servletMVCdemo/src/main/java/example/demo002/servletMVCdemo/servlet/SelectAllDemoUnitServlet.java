package example.demo002.servletMVCdemo.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import example.demo002.servletMVCdemo.dto.DemoUnit;
import example.demo002.servletMVCdemo.repository.DemoUnitArrRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="selectAllDemoUnitServlet" , urlPatterns ="/demounit/selectAll")
public class SelectAllDemoUnitServlet extends HttpServlet{
	
	DemoUnitArrRepository duRepository = DemoUnitArrRepository.getInstance();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		System.out.println("selectAll 메소드 수행");
		
		List<DemoUnit> duList = duRepository.selectAll();
		
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
//		resp.getWriter().write(duIn.getNo() + " " + duIn.getId() + " " + duIn.getName());
		PrintWriter pw = resp.getWriter();
		pw.write(
				"<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Document</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <h1>저장된 데모유닛 리스트</h1>\r\n"
				+ "    <ul>\r\n"
				);
				for(DemoUnit du : duList) {
				pw.write("<li>");
				pw.write(du.getNo() + " " + du.getId() + " " + du.getName());
				pw.write("</li>");
				}
				pw.write(
				"  </ul>\r\n"
				+ "</body>\r\n"
				+ "</html>"
				);
	}
}

