package example.sample.project.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import example.sample.project.domain.FoodItem;
import example.sample.project.domain.FoodItemCond;

@Repository //선언하면 싱글톤 만드는 과정(코드)필요없음
public class ListFoodRepository implements FoodRepository{
	private static List<FoodItem> db = new ArrayList<>();
	private static int seq = 1;
	
//	private static final FoodRepository instance = new FoodRepository();
//	public static FoodRepository getInstance() {
//		return instance;
//	}
//	private FoodRepository() {
//	}
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
		targetFoodItem.setOptions(foodItem.getOptions());
		targetFoodItem.setFoodType(foodItem.getFoodType());
		targetFoodItem.setShopCode(foodItem.getShopCode());
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
	@Override
	public List<FoodItem> selectSearchAll(FoodItemCond serachCond) {
		// TODO Auto-generated method stub
		return null;
	}
}