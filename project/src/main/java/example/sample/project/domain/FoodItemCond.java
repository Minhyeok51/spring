package example.sample.project.domain;

import java.util.List;

import lombok.Data;
@Data
public class FoodItemCond {//검색 조건에 대한 데이터를 모아둔 곳
	private Integer id; //Integer클래스타입
	private String itemName;
	private String content;
	private Integer price;
	private List<Integer> ids;
	
	private Boolean soldout;
	private List<String> options;
	private FoodType foodType;
	private String shopCode; 
}
