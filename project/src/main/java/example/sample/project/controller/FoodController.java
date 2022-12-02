package example.sample.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import example.sample.project.domain.FoodItem;
import example.sample.project.repository.FoodRepository;
import jakarta.annotation.PostConstruct;

@Controller
@RequestMapping("/foods")
public class FoodController {
	private final FoodRepository foodRepository = FoodRepository.getInstance();
	
	@GetMapping
	public String foods(Model model) {
		List<FoodItem> foodList = foodRepository.selectAll();
		
		model.addAttribute("foods", foodList);
		
		return "foods/foods";
	}
	
	@GetMapping("/{foodId}")
	public String food(Model model, @PathVariable("foodId") int foodId) {
		FoodItem foodItem = foodRepository.selectById(foodId);
		model.addAttribute("food", foodItem);
		
		return "foods/food";
	}
	@GetMapping("/new")
	public String newFood() {
		return "foods/new";
	}
	
//	@PostMapping("/new")
//	public String newFoodInsert(@RequestParam("itemName") String itemName
//								,@RequestParam("content") String content
//								,@RequestParam("price") int price
//								,Model model) {
//		FoodItem foodItem = new FoodItem(itemName, content, price);
//		foodRepository.insert(foodItem);
//		
//		model.addAttribute("food", foodItem);
//		
//		return "foods/food";
//	}
	
	@PostMapping("/new")
	public String newFoodInsertModel(@ModelAttribute FoodItem foodItem
								,Model model) {
		foodRepository.insert(foodItem);
		
		model.addAttribute("food", foodItem);
		
		return "foods/food";
	}
	
	
	@PostConstruct //생성자 불린후 그 다음에 밑에 메소드 실행해 주는 어노테이션
	public void insertInit() {
		FoodItem foodItem1 = new FoodItem("김밥", "돈가스", 3500);
		foodRepository.insert(foodItem1);
		FoodItem foodItem2 = new FoodItem("우동", "김치", 4000);
		foodRepository.insert(foodItem2);
	}
	
	
}
