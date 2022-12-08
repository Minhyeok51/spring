package example.sample.project.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import example.sample.project.domain.FoodItem;

public class FoodRepositoryTest {
	@Autowired
	FoodRepository foodRepository;
	
	@Test
	void updateTest() {
		FoodItem foodItem = new FoodItem("햄버거","싸이버거",6000);
		
		FoodItem foodItemInserted = foodRepository.insert(foodItem);
		
		FoodItem updateFoodItem = new FoodItem("햄버거","싸이버거",7000);
		boolean result = foodRepository.update(foodItemInserted.getId(), updateFoodItem);
		if(result) {
			//정상 업데이트 된 경우
		}else {
			//업데이트 실패한 경우
		}
		
		// id:1 햄버거를 6천원
		// id:1 햄버거를 7천원 (updateFoodItem)
		
		FoodItem targetFoodItem = foodRepository.selectById(foodItemInserted.getId());
		
		assertThat(targetFoodItem.getPrice()).isEqualTo(updateFoodItem.getPrice());
		assertThat(targetFoodItem.getItemName()).isEqualTo(updateFoodItem.getItemName());
		assertThat(targetFoodItem.getContent()).isEqualTo(updateFoodItem.getContent());
	}
}
