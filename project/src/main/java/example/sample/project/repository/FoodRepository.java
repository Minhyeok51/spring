package example.sample.project.repository;

import java.util.ArrayList;
import java.util.List;

import example.sample.project.domain.FoodItem;

public class FoodRepository {
	private static List<FoodItem> db = new ArrayList<>();
//	private List<DemoUnit> db;
	private static int seq = 1;
	private static final FoodRepository instance = new FoodRepository();
//	static -class레벨이다
	public static FoodRepository getInstance() {
//		DemoUnitArrRepository instance = new DemoUnitArrRepository(); 이렇게 되면 싱글톤이 깨지는것 
		return instance;
	}
	
	private FoodRepository() {
//		db = new ArrayList<>(); //new 할때마다 db 내용 날라가니까 그렇게 하지 못하게 하기 위해
	}
	public FoodItem insert(FoodItem foodItem) {
		foodItem.setId(seq++);
		db.add(foodItem);
		return foodItem;
	}
	public FoodItem selectById(int id) {
		for(FoodItem foodItem : db) {
			if(foodItem.getId() == id) {
				return foodItem;
			}
		}
		return null;
	}
	
	public List<FoodItem> selectAll() {
		return db;
	}
	
	public boolean update(int id, FoodItem foodItem) {
		boolean result = false;
		try {
		FoodItem targetFoodItem = selectById(id);
		targetFoodItem.setItemName(foodItem.getItemName());
		targetFoodItem.setContent(foodItem.getContent());
		targetFoodItem.setPrice(foodItem.getPrice());
		targetFoodItem.setSoldout(foodItem.getSoldout());
		result = true;
		//FoodItem 필드추가
		//화면에서 Input처리
		//>>전달받는 부분 @ModelAttribute -> 알아서 필드 맵핑
		//Controller -> Repository update
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public void deleteAll() {
		db.clear();
	}
}