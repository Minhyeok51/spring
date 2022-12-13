package example.sample.project.repository;

import java.util.List;

import example.sample.project.domain.FoodItem;
import example.sample.project.domain.FoodItemCond;

public interface FoodRepository {

	public FoodItem insert(FoodItem foodItem) ;
	
	public FoodItem selectById(int id) ;
	
	public List<FoodItem> selectAll() ;
	
	public List<FoodItem> selectSearchAll(FoodItemCond serachCond);
	
	public boolean update(int id, FoodItem foodItem);
	
	public void deleteAll();
}
