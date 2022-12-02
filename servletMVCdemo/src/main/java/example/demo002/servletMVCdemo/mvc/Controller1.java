package example.demo002.servletMVCdemo.mvc;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Controller1 {
	 void process(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException; 
}
