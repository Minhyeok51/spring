package example.sample.project.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import example.sample.project.domain.FoodItem;
import example.sample.project.domain.FoodItemCond;
import example.sample.project.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MybatisFoodRepository implements FoodRepository{

	private final FoodItemMapper foodItemMapper;
	
	@Override
	@Transactional
	public FoodItem insert(FoodItem foodItem) {
		Integer result = foodItemMapper.insert(foodItem);
		log.info("FoodItem insert result {}", result);
		
//		for(String options : foodItem.getOptions()) { //options가 리스트로 되어있어서 포문으로 돌림
//		foodItemMapper.insertFoodItemOptions(foodItem.getId(),options);
//		} //-->insertFoodItemOptionsList으로 대체
		foodItemMapper.insertFoodItemOptionsList(foodItem.getId(),foodItem.getOptions());
		return foodItem;
	}

	@Override
	public FoodItem selectById(int id) {
//		FoodItem foodItem = foodItemMapper.selectById(id);
		FoodItem foodItem = foodItemMapper.selectByIdWithOptions(id);
		log.info("foodItem {}", foodItem);
		
//		List<String> options = foodItemMapper.selectFoodItemOptions(id);
//		foodItem.setOptions(options);
		return foodItem;
	}

	@Override
	public List<FoodItem> selectAll() {
		List<FoodItem> foodItemList = foodItemMapper.selectAll();
		return foodItemList;
	}

	@Override
	@Transactional
	public boolean update(int id, FoodItem foodItem) {
		boolean result = false;
		try {
			//해당 id options 한번 삭제
			foodItemMapper.deleteFoodItemOptions(id);
			log.info("foodItem delete options {}", foodItem);
			
			//id options 다시 insert
//			for(String options : foodItem.getOptions()) {
//				foodItemMapper.insertFoodItemOptions(id,options);
//				}    //-->insertFoodItemOptionsList으로 대체
			foodItemMapper.insertFoodItemOptionsList(id, foodItem.getOptions());

			foodItemMapper.update(id, foodItem);
			result = true;
		}catch (Exception e) {
			log.error("FoodItemMapper Update Error {} {}",id,foodItem);
		}
		return result;
	}

	@Override
	public void deleteAll() {
		foodItemMapper.deleteAll();
	}

	@Override
	public List<FoodItem> selectSearchAll(FoodItemCond serachCond) {
		List<FoodItem> foods = foodItemMapper.selectSearchAll(serachCond);
		return foods;
	}

}
