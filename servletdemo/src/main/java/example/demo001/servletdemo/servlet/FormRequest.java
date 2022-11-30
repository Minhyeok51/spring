package example.demo001.servletdemo.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="FormRequest" , urlPatterns ="/formRequest")
public class FormRequest extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		
		String id=req.getParameter("id");
		String name=req.getParameter("name");
		String age=req.getParameter("age");
		resp.getWriter().write("id = "+id + "\nname = "+name+"\nage = "+age);
	}
	
}
