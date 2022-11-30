package example.demo001.servletdemo.servlet;

import java.io.IOException;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="servletResponse" , urlPatterns ="/servletResponse")
public class ServletResponse extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		System.out.println("servletResponse");
		
		resp.setStatus(HttpServletResponse.SC_OK); //200 OK
//		resp.setHeader("Content-type","text/plain" );
//		resp.setContentType("text/pain");
//		resp.getWriter().write("servletResponse");
		resp.setCharacterEncoding("utf-8");
		
		//Session 서버가 관리하는 정보, Cookie 사용자가(사용자로컬에) 관리하는 정보
		Cookie cookie = new Cookie("money","50000");
		cookie.setMaxAge(30);
		resp.addCookie(cookie);
		
		Cookie today = new Cookie("today","friday");
		today.setMaxAge(50);
		resp.addCookie(today);

//		resp.sendRedirect("/resp.html");
		
		resp.setContentType("text/html");

		resp.getWriter().write("<html>");
		resp.getWriter().write("<head>");
		resp.getWriter().write("</head>");
		resp.getWriter().write("<body>");
		resp.getWriter().write("<h1> servlet response page</h1>");
		resp.getWriter().write("</body>");
		resp.getWriter().write("</html>");
	}
	
}
