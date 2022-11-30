package example.demo001.servletdemo.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import example.demo001.servletdemo.dto.DemoUnit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//WebServlet annotation 선언해줘야함
@WebServlet(name="servletResponseJson" , urlPatterns ="/servletResponseJson")
public class ServletResponseJson extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		System.out.println("ServletResponseJson");
		
		resp.setStatus(HttpServletResponse.SC_OK); //200 OK
		resp.setContentType("appllication/josn");
		resp.setCharacterEncoding("utf-8");
		
		DemoUnit unit = new DemoUnit();
		unit.setId("123");
		unit.setJob("warriror");
		unit.setLevel(30);
		unit.setName("gohome");
		
		DemoUnit unit2 = new DemoUnit();
		unit2.setId("124");
		unit2.setJob("warriror2");
		unit2.setLevel(35);
		unit2.setName("welcome");
		
		ObjectMapper objectMapper = new ObjectMapper();
//		String result = objectMapper.writeValueAsString(unit);
//		resp.getWriter().write(result);
		
		List<DemoUnit> units = new ArrayList<DemoUnit>();
		units.add(unit);
		units.add(unit2);
		
		String result = objectMapper.writeValueAsString(units);
		resp.getWriter().write(result);
	}
	
}
