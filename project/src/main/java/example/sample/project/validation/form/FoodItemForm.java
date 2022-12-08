package example.sample.project.validation.form;

import java.util.List;

import org.hibernate.validator.constraints.Range;

import example.sample.project.domain.FoodType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FoodItemForm {
	private int id;
	
	//FoodItemNewForm, FoodItemUpdateForm
	//FoodItemForm
	
	@NotBlank(groups = UpdateCheck.class)//UpdateCheck가 걸려있는 애들만 검증을 처리하고
	private String itemName;
	@NotBlank(groups = NewCheck.class) //NewCheck가 걸려있는 애들만 검증을 처리하고
	private String content;
	@Range(min=1, max=10000 , groups = {UpdateCheck.class, NewCheck.class})
	private int price;
	
	private Boolean soldout;
	private List<String> options;
	private FoodType foodType;
	private String shopCode; 
	
	public FoodItemForm() {}
	public FoodItemForm(String itemName, String content, int price) {
		super();
		this.itemName = itemName;
		this.content = content;
		this.price = price;
	}
}
