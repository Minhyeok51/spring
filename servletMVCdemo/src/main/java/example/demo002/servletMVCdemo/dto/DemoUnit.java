package example.demo002.servletMVCdemo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter //이것이 있어서 밑에 겟셋 메소드는 없어도 알아서 생성된것. lombok 이다
public class DemoUnit {
	private int no;
	private String id;
	private String name;
	
	public DemoUnit() {
	}
	public DemoUnit( String id, String name) {
		this.id = id;
		this.name = name;
	}
}
