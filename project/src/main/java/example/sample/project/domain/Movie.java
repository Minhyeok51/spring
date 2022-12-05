package example.sample.project.domain;

import lombok.Data;

@Data
public class Movie {
	private String mName;
	private int mNum;
	
	public Movie(String mName) {
		super();
		this.mName = mName;
	}
}
