package example.demo003.demo003.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Getter @Setter @ToString
//@RequiredArgsConstructor
@Data //위에것들 다 포함
@AllArgsConstructor
public class FoodItem {
	private String id;
	private String itemName;
	private String content;
	private int price;
}
