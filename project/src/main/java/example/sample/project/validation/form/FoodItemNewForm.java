package example.sample.project.validation.form;

import java.util.List;

import org.hibernate.validator.constraints.Range;

import example.sample.project.domain.FoodType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FoodItemNewForm {
	private int id;
	
	//FoodItemNewForm, FoodItemUpdateForm
	//FoodItemForm
	
//	@NotBlank(message="입력좀...") errors.properties에 적을수도 있다
	@NotBlank
	private String itemName;
	@NotBlank
	private String content;
	@Range(min=1, max=10000)
	private int price;
	
	private Boolean soldout;
	private List<String> options;
	private FoodType foodType;
	private String shopCode; 
	
	public FoodItemNewForm() {}
	public FoodItemNewForm(String itemName, String content, int price) {
		super();
		this.itemName = itemName;
		this.content = content;
		this.price = price;
	}
}
