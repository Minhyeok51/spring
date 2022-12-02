package example.demo002.servletMVCdemo.mvc;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DemoView {
	private String view;

	public DemoView(String view) {
		this.view = view;
	}

	public void render(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rDispatcher = req.getRequestDispatcher(view);
		rDispatcher.forward(req, resp);
	}
	public void render(HttpServletRequest req, HttpServletResponse resp, Map<String,Object> model) throws ServletException, IOException {
		//model을 request에 잘 담아서 보내야함
		//request.getparameter -> Map
		//Map -> request.setAttribute
		for(String key : model.keySet()) {
//			model.get(key); 밸류들을 싹다 가져온다
			req.setAttribute(key, model.get(key));
		}
		RequestDispatcher rDispatcher = req.getRequestDispatcher(view);
		rDispatcher.forward(req, resp);
	}
}
