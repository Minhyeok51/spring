package example.demo001.servletdemo.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="servletParams" , urlPatterns ="/servletParams")
public class ServletParams extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		
		System.out.println(req.getParameter("id1"));
		
		Enumeration<String> params = req.getParameterNames();
		
		while(params.hasMoreElements()) {
			String name = params.nextElement();
			System.out.println(name+": " + req.getParameter(name));
		}
		
		//하나의 네임에 여러개 밸류가 들어있을때 
		String[] paramValues = req.getParameterValues("id5");
		for(String s : paramValues) {
			System.out.println(s);
		}
		Map<String, String[]> paramMap = req.getParameterMap();
		
		System.out.println(paramMap.get("id3"));
		
		//List랑 비슷 중복값 알아서 제거해줌 
		Set<String> keySet = paramMap.keySet();
		for(String key : keySet) {
			String[] values = paramMap.get(key);
			for(String val : values) {
				System.out.println(key + " : " + val);
			}
		}
		resp.getWriter().write("response");
	}
}
