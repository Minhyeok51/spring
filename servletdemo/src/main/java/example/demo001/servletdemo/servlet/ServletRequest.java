package example.demo001.servletdemo.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="ServletRequest" , urlPatterns ="/servletRequest")
public class ServletRequest extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		
		System.out.println("--------------Start Line----------------");
		System.out.println("getMethod : " + req.getMethod());
		System.out.println("getProtocol : " + req.getProtocol());
		System.out.println("getRequestURL : " + req.getRequestURL());
		System.out.println("getRequestURI : " + req.getRequestURI());
		System.out.println("getQueryString : " + req.getQueryString());
		
		System.out.println("--------------Header----------------");
		System.out.println("getServerName : " + req.getServerName());
		System.out.println("getServerPort : " + req.getServerPort());
		
		System.out.println("getLocalAddr : " + req.getLocalAddr());
		System.out.println("getLocalPort : " + req.getLocalPort());
		System.out.println("getLocalName : " + req.getLocalName());
		
		System.out.println("getRemoteAddr : " + req.getRemoteAddr());
		System.out.println("getRemotePort : " + req.getRemotePort());
		System.out.println("getRemoteHost : " + req.getRemoteHost());
		
		System.out.println("getLocale : " + req.getLocale());
		
		System.out.println("getContentType : " + req.getContentType());
		System.out.println("getContentLength : " + req.getContentLength());
		resp.getWriter().write("ServletRequest");
	}
	
}
