package example.sample.project.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import example.sample.project.domain.FoodItem;
import example.sample.project.domain.FoodItemCond;

@Mapper
public interface FoodItemMapper {
	
	public Integer insert(FoodItem foodItem) ;
	
	public Integer insertFoodItemOptions(@Param("id") int id, @Param("options") String options);
	
	public Integer insertFoodItemOptionsList(@Param("id") int id, @Param("options") List<String> options);
	
	public FoodItem selectById(int id) ;
	
	public FoodItem selectByIdWithOptions(int id) ;
	
	public void deleteFoodItemOptions(int id);
	
	public List<String> selectFoodItemOptions(int id);
	
	public List<FoodItem> selectAll() ;
	
	public List<FoodItem> selectSearchAll(FoodItemCond serachCond);
	
	public void update(@Param("id") int id, @Param("updateItem") FoodItem foodItem);
	
	public void deleteAll();
}
