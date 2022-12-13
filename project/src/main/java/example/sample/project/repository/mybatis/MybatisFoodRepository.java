package example.sample.project.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

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
	public FoodItem insert(FoodItem foodItem) {
		Integer result = foodItemMapper.insert(foodItem);
		log.info("FoodItem insert result {}", result);
		return foodItem;
	}

	@Override
	public FoodItem selectById(int id) {
		FoodItem foodItem = foodItemMapper.selectById(id);
		return foodItem;
	}

	@Override
	public List<FoodItem> selectAll() {
		List<FoodItem> foodItemList = foodItemMapper.selectAll();
		return foodItemList;
	}

	@Override
	public boolean update(int id, FoodItem foodItem) {
		boolean result = false;
		try {
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
