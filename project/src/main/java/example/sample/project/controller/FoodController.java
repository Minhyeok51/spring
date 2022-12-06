package example.sample.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import example.sample.project.domain.FoodItem;
import example.sample.project.repository.FoodRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
//	@PostMapping("/food") //내용물을 body에 담아온다
//	public String food2(Model model, @RequestParam int foodId) {
//		FoodItem foodItem = foodRepository.selectById(foodId);
//		model.addAttribute("food", foodItem);
//		
//		return "foods/food";
//	}
	
	//locallhost:8080/foods/5
	@GetMapping("/{foodId}")
	public String food(Model model, @PathVariable("foodId") int foodId) {
		FoodItem foodItem = foodRepository.selectById(foodId);
		model.addAttribute("food", foodItem);
		
		return "foods/food";
	}
	@GetMapping("/new")
	public String newFood(Model model) {
		model.addAttribute("foodItem", new FoodItem());
		return "foods/new";
	}
	
	//model로 받아 한번에 처리하기 아래의 requestParam과의 차이
	@PostMapping("/new")
	public String newFoodInsertModel(@ModelAttribute FoodItem foodItem
//								,Model model
								,RedirectAttributes rAttr) {
		log.info(foodItem.toString());
		
		foodRepository.insert(foodItem);
		
//		model.addAttribute("food", foodItem);
		
		rAttr.addAttribute("foodId", foodItem.getId());
		rAttr.addAttribute("test", "ok");
//		return "foods/food";
//		return "redirect:/foods/" + foodItem.getId();
		return "redirect:/foods/{foodId}";
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
	

	@GetMapping("/update/{foodId}")
	public String updateFood(Model model, @PathVariable("foodId") int foodId) {
		FoodItem foodItem = foodRepository.selectById(foodId);
		model.addAttribute("food", foodItem);
				
		return "foods/update.html";
	}
	
	@PostMapping("/update/{foodId}")
	public String updateFoodProcess(
			Model model//2번의 경우 model받아올 필요없다
					, @PathVariable("foodId") int foodId
					, @ModelAttribute FoodItem foodItem) {
		log.info("/update/{}",foodId);
		log.info(foodItem.toString());
		foodRepository.update(foodId, foodItem);
		
		//1번.
		//여기서 바로 foods/id에 해당하는 foods/food페이지를 보여주면서 <-food객체를 주입
//		model.addAttribute("food", foodItem);
//		return "foods/food";
		//2번.
		//food상세정보를 보여주는 경로가 이미 존재. ->이미 존재하는 메소드를 활용
		return "redirect:/foods/{foodId}";
	}
	
//	메서드명 위에 붙는 ModelAttribute 
	@ModelAttribute("options")
	public Map<String, String> options(){
		Map<String, String> options= new HashMap<>();
		
		options.put("1번", "탄수화물");
		options.put("2번", "단백질");
		options.put("3번", "지방");
		
		return options;
	}
	@PostConstruct //생성자 불린후 그 다음에 밑에 메소드 실행해 주는 어노테이션
	public void insertInit() {
		FoodItem foodItem1 = new FoodItem("김밥", "돈가스", 3500);
		foodRepository.insert(foodItem1);
		FoodItem foodItem2 = new FoodItem("우동", "김치", 4000);
		foodRepository.insert(foodItem2);
	}
	
	
}
