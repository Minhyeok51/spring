package example.sample.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
public class FoodItem {
	private int id;
	private String itemName;
	private String content;
	private int price;
	
	public FoodItem(String itemName, String content, int price) {
		super();
		this.itemName = itemName;
		this.content = content;
		this.price = price;
	}
}
