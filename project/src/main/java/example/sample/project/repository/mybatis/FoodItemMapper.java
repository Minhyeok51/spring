package example.sample.project.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import example.sample.project.domain.FoodItem;
import example.sample.project.domain.FoodItemCond;

@Mapper
public interface FoodItemMapper {
	
	public Integer insert(FoodItem foodItem) ;
	
	public FoodItem selectById(int id) ;
	
	public List<FoodItem> selectAll() ;
	
	public List<FoodItem> selectSearchAll(FoodItemCond serachCond);
	
	public void update(@Param("id") int id, @Param("updateItem") FoodItem foodItem);
	
	public void deleteAll();
}
