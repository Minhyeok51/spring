package example.demo001.servletdemo.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="servletDemo2" , urlPatterns ="/demo2")
public class ServletDemo2 extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		System.out.println("Service 메소드 수행");
		
		System.out.println("id : "+req.getParameter("id"));
		System.out.println("name : "+req.getParameter("name"));
		resp.getWriter().write("Response Servlet Demo2");
	}
	
}
