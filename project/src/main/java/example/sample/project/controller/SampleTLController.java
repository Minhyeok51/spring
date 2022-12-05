package example.sample.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import example.sample.project.domain.FoodItem;

@Controller
public class SampleTLController { //Thymeleaf

	@RequestMapping("/text")
	public String text(Model model) {
		model.addAttribute("test", "Test String");
		model.addAttribute("boldtest", "<b>Test String</b>");
		
		FoodItem foodItem1 = new FoodItem("name1", "content1", 1000);
		FoodItem foodItem2 = new FoodItem("name2", "content2", 1000);
		
		model.addAttribute("foodItem1", foodItem1);
		model.addAttribute("foodItem2", foodItem2);
		
		List<FoodItem> foods = new ArrayList<FoodItem>();
		foods.add(foodItem1);
		foods.add(foodItem2);
		
		model.addAttribute("foods", foods);
		
		return "sampleTL/basic";
	}
	
	@RequestMapping("/object")
	public String object(Model model) {
		
		return "sampleTL/object";
	}
}
