package example.sample.project.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import example.sample.project.domain.FoodItem;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class SampleTLController { //Thymeleaf

	@RequestMapping("/basic")
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
		model.addAttribute("dateTime", LocalDateTime.now());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String strDateTime = LocalDateTime.now().format(formatter);
		
		model.addAttribute("strDateTime", strDateTime);
		
		return "sampleTL/object";
	}
	
	@GetMapping("/object/{param12da}")
	public String updateFoodprocess(Model model
			,@PathVariable("param12da") String param1
			,@RequestParam("param2") String param2) {
		log.info(param1);
		log.info(param2);
		
		return "sampleTL/object";
	}
	
	//Component붙어있으면 하나씩 관리돼서 들어간다? 스프링이 관리하는 하나씩의 객체
	@Component("helloBean")
	static class HelloBean{
		public String hello(String data) {
			return data;
		}
	}
	
	@GetMapping("/link")
	public String link(Model model) {
		model.addAttribute("param1", "param1");
		model.addAttribute("param2", "param2");
		return "sampleTL/link";
	}
	
	
	
	@GetMapping("/literal")
	public String literal(Model model) {
		
		FoodItem foodItem1 = new FoodItem("name1", "content1", 1000);
		FoodItem foodItem2 = new FoodItem("name2", "content2", 1000);
		
		model.addAttribute("foodItem1", foodItem1);
		model.addAttribute("foodItem2", foodItem2);
		model.addAttribute("nullData", null);
		
		return "sampleTL/literal";
	}
	
	@GetMapping("/comments")
	public String comments(Model model) {
		model.addAttribute("text", "text");
		return "sampleTL/comments";
	}
	
	@GetMapping("/javascript")
	public String javascript(Model model) {
		model.addAttribute("text", "text");
		
		FoodItem foodItem1 = new FoodItem("name1", "content1", 1000);
		FoodItem foodItem2 = new FoodItem("name2", "content2", 1000);
		
		model.addAttribute("foodItem1", foodItem1);
		model.addAttribute("foodItem2", foodItem2);
		
		List<FoodItem> foods = new ArrayList<FoodItem>();
		foods.add(foodItem1);
		foods.add(foodItem2);
		
		model.addAttribute("foods", foods);
		
		return "sampleTL/javascript";
	}
	
	@GetMapping("/fragment")
	public String fragment() {
		return "sampleTL/fragment";
	}
	
}
