package example.demo003.demo003.request;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/goods")
public class GoodsRestController {

	///rest/goods/getGoods
//	@RequestMapping("/getGoods")
	
	@GetMapping	//GET 정보를 달라 goods -> goods 전체 정보리스트
	public String goods() {
		return "Get Goods";
	}
	
	@GetMapping("/{goodsId}")	//GET 정보를 달라 특정상품의 정보를 달라
	public String selectGoods(@PathVariable("goodsId") String goodsId) {
		return "Get selectGoods" + goodsId;
	}
	
	@PostMapping //POST -> Insert 추가 저장
	public String addGoods() {
		return "Post addGoods";
	}
	
	@DeleteMapping("/{goodsId}") //Delete -> 특정 상품정보를 삭제해 달라
	public String deleteGoods(@PathVariable("goodsId") String goodsId) {
		return "Delete Goods" +goodsId;
	}
	
	@PutMapping("/{goodsId}") //Put -> 특정상품 정보를 (전체를) 수정해달라
	public String putGoods(@PathVariable("goodsId") String goodsId) {
		return "Put Goods" + goodsId;
	}
	
	@PatchMapping("/{goodsId}")//Patch -> 특정상품 정보를 (부분을) 수정해달라
	public String patchGoods(@PathVariable("goodsId") String goodsId) {
		return "Patch Goods" + goodsId;
	}
}
