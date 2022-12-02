package example.demo002.servletMVCdemo.mvc;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DemoModelView {
	private String view;
	private Map<String, Object> model;
	
	public DemoModelView(String view) {
		this.view = view; //view 보여줄 파일
		this.model = new HashMap<>();
	}
}
