package example.sample.project.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import example.sample.project.domain.FoodItem;

@Component //스프링이 로딩할때 Bean으로 등록한다. Bean = 스프링이 관리하는 자바 객체
public class FoodItemValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		//사용가능한 녀석인지 체크하는거
		return FoodItem.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//errors 엔 bindingResult가 들어올것이고 target엔 검증된 클래스들어옴
		FoodItem foodItem = (FoodItem)target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemName", "required.foodItem.itemName");

		if(!StringUtils.hasText(foodItem.getContent())) {
			errors.rejectValue("content", "required.foodItem.content", "default message");
		}
		if(foodItem.getPrice() > 10000 || foodItem.getPrice() < 1) {
			errors.rejectValue("price", "max.foodItem.price",new Object[] {1,10000,foodItem.getPrice()} ,"default message");
		}
		if(foodItem.getSoldout()) {
			errors.reject("failureMsg",null);
		}
	}

}
