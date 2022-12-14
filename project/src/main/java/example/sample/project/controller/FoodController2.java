package example.sample.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import example.sample.project.domain.FoodItem;
import example.sample.project.domain.FoodType;
import example.sample.project.domain.ShopCode;
import example.sample.project.repository.FoodRepository;
import example.sample.project.repository.ListFoodRepository;
import example.sample.project.validation.form.FoodItemNewForm;
import example.sample.project.validation.form.UpdateCheck;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor //붙이면 FoodItemValidator foodItemValidator;초기화 안됐단 오류 사라짐 
@RequestMapping("/foods2")
public class FoodController2 {
	//	private final FoodRepository foodRepository = FoodRepository.getInstance();
//	private final ListFoodRepository foodRepository;
	private final FoodRepository foodRepository;
	//	@Autowired //final을 생략해야 가능
	//	private FoodItemValidator foodItemValidator;


	//	@Autowired //자동으로 연결해달라 컴포넌트 빈에서 찾아 알아서 매핑해줌, 생성자가 하나일땐 생략가능
	//	public FoodController(FoodItemValidator foodItemValidator) {
	//		this.foodItemValidator = foodItemValidator;
	//	}


	@GetMapping
	public String foods2(Model model) {
		List<FoodItem> foodList = foodRepository.selectAll();

		model.addAttribute("foods", foodList);
		return "foods2/foods";
	}


	@GetMapping("/{foodId}")
	public String food(Model model, @PathVariable("foodId") int foodId) {
		FoodItem foodItem = foodRepository.selectById(foodId);
		model.addAttribute("food", foodItem);

		return "foods2/food";
	}
	@GetMapping("/new")
	public String newFood(Model model) {
		model.addAttribute("foodItem", new FoodItem());
		return "foods2/new";
	}

	@PostMapping("/new_old")
	public String newFoodInsertModelOld(@ModelAttribute FoodItem foodItem
			,Model model
			,RedirectAttributes rAttr) {
		log.info(foodItem.toString());
		//		model.addAttribute("food", foodItem);

		//저장전에 검증
		//		if(foodItem.getItemName() == null || foodItem.getItemName().trim().equals("")) {
		//			return "foods2/new";
		//		}
		Map<String, String> errors = new HashMap<>();
		//null인거 글없는거 공밳만 있는거 다 비교해줌
		//검증을 스프링 프레임워크에 있는 라이브러리로 처리
		if(!StringUtils.hasText(foodItem.getItemName())) {
			errors.put("itemName", "아이템 이름은 필수입력.");
			//			model.addAttribute("errors", errors);
			//			return "foods2/new";
		}
		if(!StringUtils.hasText(foodItem.getContent())) {
			errors.put("content", "컨텐츠는 필수입력.");
		}
		if(foodItem.getPrice() > 10000) {
			errors.put("price", "너무 비싸다 ~만원까지 상품만 취급");
		}
		if(foodItem.getSoldout()) {
			errors.put("globalError", "똑바로 입력하세요.");
		}
		if(!errors.isEmpty()) {//errors가 비어있지 않으면
			model.addAttribute("errors", errors);
			return "foods2/new";
		}

		foodRepository.insert(foodItem);
		rAttr.addAttribute("foodId", foodItem.getId());
		rAttr.addAttribute("test", "ok");
		//		return "foods2/food";
		//		return "redirect:/foods2/" + foodItem.getId();
		return "redirect:/foods2/{foodId}";
	}
	//model로 받아 한번에 처리하기 아래의 requestParam과의 차이

	@PostMapping("/new_old2")
	public String newFoodInsertModelOld2(@ModelAttribute FoodItem foodItem
			//								,Model model 모델 안쓰고 BindingResult쓰는 버전 
			,BindingResult bindingResult
			,RedirectAttributes rAttr) {
		log.info(foodItem.toString());


		if(!StringUtils.hasText(foodItem.getItemName())) {
			bindingResult.addError(new FieldError("foodItem", "itemName", "아이템 이름은 필수입력."));
		}
		if(!StringUtils.hasText(foodItem.getContent())) {
			bindingResult.addError(new FieldError("foodItem", "content", "컨텐츠는 필수입력."));
		}
		if(foodItem.getPrice() > 10000) {
			bindingResult.addError(new FieldError("foodItem", "price", "너무 비싸다 ~만원까지 상품만 취급"));
		}
		if(foodItem.getSoldout()) {
			bindingResult.addError(new ObjectError("foodItem", "똑바로 입력하세요."));
			bindingResult.addError(new ObjectError("foodItem", "에러 발생"));
			bindingResult.addError(new ObjectError("foodItem", "아차! 오타!"));
		}
		if(bindingResult.hasErrors()) {
			log.info("bindingResult={}",bindingResult);
			return "foods2/new";
		}

		foodRepository.insert(foodItem);
		rAttr.addAttribute("foodId", foodItem.getId());
		rAttr.addAttribute("test", "ok");
		//		return "foods2/food";
		//		return "redirect:/foods2/" + foodItem.getId();
		return "redirect:/foods2/{foodId}";
	}

	@PostMapping("/new_old3")
	public String newFoodInsertModelOld3(@ModelAttribute FoodItem foodItem
			,BindingResult bindingResult
			,RedirectAttributes rAttr) {
		log.info(foodItem.toString());


		if(!StringUtils.hasText(foodItem.getItemName())) {
			bindingResult.addError(new FieldError("foodItem", "itemName", foodItem.getItemName(),false,new String[] {"required.foodItem.itemName"},null,"아이템 이름은 필수입력."));
			//			FieldError(오류 발생 객체 이름, 오류 필드, 사용자가 입력한 값(거절된 값), 바인딩실패?, 메시지 코드, 메시지에 이용하는 인자, 기본 메시지)
			//			ObjectError도 field부분만 빼고 동일하다.
		}
		if(!StringUtils.hasText(foodItem.getContent())) {
			bindingResult.addError(new FieldError("foodItem", "content", foodItem.getContent(),false,new String[] {"required.foodItem.content"},null,"컨텐츠는 필수입력."));
		}
		if(foodItem.getPrice() > 10000) {
			bindingResult.addError(new FieldError("foodItem", "price",foodItem.getPrice(),false,new String[] {"max.foodItem.price"},new Object[] {1,10000} ,"너무 비싸다 ~만원까지 상품만 취급"));
		}
		if(foodItem.getSoldout()) {
			//			bindingResult.addError(new ObjectError("foodItem", "똑바로 입력하세요."));
			//			bindingResult.addError(new ObjectError("foodItem", "에러 발생"));
			//			bindingResult.addError(new ObjectError("foodItem", "아차! 오타!"));
			bindingResult.addError(new ObjectError("foodItem", new String[] {"failureMsg"},null,"DefaultMessage"));
			bindingResult.addError(new ObjectError("foodItem", "에러 발생"));
			bindingResult.addError(new ObjectError("foodItem", "아차! 오타!"));
		}
		if(bindingResult.hasErrors()) {
			log.info("bindingResult={}",bindingResult);
			return "foods2/new";
		}

		foodRepository.insert(foodItem);
		rAttr.addAttribute("foodId", foodItem.getId());
		rAttr.addAttribute("test", "ok");
		return "redirect:/foods2/{foodId}";
	}

	
	@PostMapping("/new")
	public String newFoodInsertModel(
//			@Validated @ModelAttribute("foodItem") FoodItemNewForm foodItem
//			@Validated(UpdateCheck.class) @ModelAttribute("foodItem") FoodItem foodItem
//			@Validated(NewCheck.class) @ModelAttribute("foodItem") FoodItem foodItem
			@Validated @ModelAttribute("foodItem") FoodItem foodItem
			,BindingResult bindingResult
			//	BindingResult bindingResult 파라미터의 위치는 반드시 @ModelAttribute FoodItem foodItem 다음에 바로 와야 한다. 에러들의 묶음?
			,RedirectAttributes rAttr) {
		log.info(foodItem.toString());

		log.info(bindingResult.getObjectName());
		log.info("{}",bindingResult.getTarget());

		//		foodItemValidator.validate(foodItem, bindingResult);
		
		if(foodItem.getSoldout()) {
			bindingResult.reject("failureMsg",null);
		}
		
		if(bindingResult.hasErrors()) {
			log.info("bindingResult={}",bindingResult);
			return "foods2/new";
		}
//		FoodItem insertFoodItem = new FoodItem();
//		insertFoodItem.setId(foodItem.getId());
//		insertFoodItem.setItemName(foodItem.getItemName());
//		insertFoodItem.setContent(foodItem.getContent());
//		insertFoodItem.setPrice(foodItem.getPrice());
//		foodRepository.insert(insertFoodItem);
//		rAttr.addAttribute("foodId", insertFoodItem.getId());
		
		foodRepository.insert(foodItem);
		rAttr.addAttribute("foodId", foodItem.getId());
		rAttr.addAttribute("test", "ok");
		return "redirect:/foods2/{foodId}";
	}


	@GetMapping("/update/{foodId}")
	public String updateFood(Model model, @PathVariable("foodId") int foodId) {
		FoodItem foodItem = foodRepository.selectById(foodId);
		model.addAttribute("food", foodItem);

		return "foods2/update.html";
	}

	@PostMapping("/update/{foodId}")
	public String updateFoodProcess(
			Model model//2번의 경우 model받아올 필요없다
			, @PathVariable("foodId") int foodId
			, @ModelAttribute FoodItem foodItem) {
		log.info("/update/{}",foodId);
		log.info(foodItem.toString());
//		foodRepository.update(foodId, foodItem);

		return "redirect:/foods2/{foodId}";
	}

	//	메서드명 위에 붙는 ModelAttribute 
	@ModelAttribute("options")
	public Map<String, String> options(){
		//		Map<String, String> options= new HashMap<>(); 불러올때 3,2,1순으로 불림
		Map<String, String> options= new LinkedHashMap<>(); //순서맞춰서 불림


		options.put("1번", "탄수화물");
		options.put("2번", "단백질");
		options.put("3번", "지방");

		return options;
	}

	@ModelAttribute("foodTypes")
	public FoodType[] FoodTypes() {
		return FoodType.values();
	}

	@ModelAttribute("shopCodes")
	public List<ShopCode> shopCodes(){
		List<ShopCode> shopCodes = new ArrayList<ShopCode>();
		shopCodes.add(new ShopCode("A", "청정"));
		shopCodes.add(new ShopCode("B", "인공"));
		shopCodes.add(new ShopCode("B", "재활용"));
		return shopCodes;
	}

//	@PostConstruct //생성자 불린후 그 다음에 밑에 메소드 실행해 주는 어노테이션
	public void insertInit() {
		FoodItem foodItem1 = new FoodItem("김밥", "돈가스", 3500);
		foodRepository.insert(foodItem1);
		FoodItem foodItem2 = new FoodItem("우동", "김치", 4000);
		foodRepository.insert(foodItem2);
	}


}
