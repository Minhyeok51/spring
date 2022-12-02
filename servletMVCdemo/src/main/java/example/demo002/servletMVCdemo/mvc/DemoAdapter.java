package example.demo002.servletMVCdemo.mvc;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface DemoAdapter {
	DemoModelView process(HttpServletRequest req, HttpServletResponse resp, Object obj)
	        throws ServletException, IOException; 
	
	boolean isHandled(Object obj);
}
