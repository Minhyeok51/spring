package example.demo001.servletdemo.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="servletDemo1" , urlPatterns ="/demo1")
public class ServletDemo1 extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		System.out.println("Service 메소드 수행");
		for(int i=1; i<7; i++) {
		resp.getWriter().write("<h"+i+">Servlet Demo1</h"+i+">");
		}
	}
	
}
