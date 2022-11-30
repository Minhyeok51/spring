package example.demo001.servletdemo.servlet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import example.demo001.servletdemo.dto.DemoUnit;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="servletRequestBody" , urlPatterns ="/servletRequestBody")
public class ServletRequestBody extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		
		//request Body에 있는 Text 읽기
		ServletInputStream stream = req.getInputStream();
		String reqBody = StreamUtils.copyToString(stream, StandardCharsets.UTF_8);
		System.out.println(reqBody);
//		System.out.println(reqBody.length());
		
		//Text로 읽은 상태 ->JSON Format이라서 파싱해서 쓰고싶다.
		//자바에서 썼던 jsonparsing 라이브러리 기능 스프링에선-> jackson라이브러리 파일 따로 받을 필요가 없음
		ObjectMapper objectMapper = new ObjectMapper();
		DemoUnit demoUnit = objectMapper.readValue(reqBody, DemoUnit.class);
		
		System.out.println(demoUnit.getId());
		System.out.println(demoUnit.getName());
		System.out.println(demoUnit.getLevel());
		System.out.println(demoUnit.getJob());
		
		resp.getWriter().write("servletRequestBody");
	}
	
}
