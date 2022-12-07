package example.sample.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor //	모든 필드 값을 파라미터로 받는 생성자를 만듦
public class ShopCode {
	private String code;
	private String name;
}
