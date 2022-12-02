package example.demo003.demo003.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

//REST API (HTTP API)(RESTFUL API)
@Slf4j
@RestController
public class SampleRestController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/checkRestLog")
	public String checkLog() {
		System.out.println("checkRestLog");
		
		//Slf4j 선언하고 사용해도 된다
		log.info("log Info");
		//Trace 부터 아래쪽으로 갈수록 더 심각한거.
		log.trace("checkRestLog Trace");
		logger.debug("checkRestLog Debug ㅁㄴ암ㅇ느ㅏㅁㅇ나ㅡ");
		logger.info("checkRestLog Info");
		logger.warn("checkRestLog Warn");
		logger.error("checkRestLog Error");
//		System.out.println(LocalDateTime + "INFO"+ "12712");
		return "checkRestLog"; //String -> Response Body
							//String -> JSON Format  해서 보내냐
	}
	
	@RequestMapping(value = "/checkRest", method = RequestMethod.GET)
	public String checkRestGet() {
		
		log.info("checkRestGet");
		return "checkRestLog"; 
	}
	@RequestMapping(value = "/checkRest", method = RequestMethod.POST)
	public String checkRestPost() {
		
		log.info("checkRestPost");
		return "checkRestPost"; 
	}
	
//	@GetMapping("/goods")
	@GetMapping("/goods/{goodsId}")
	public String getGoods(@PathVariable("goodsId") String goodsId) {
		log.info("getGoods :{}",goodsId); //중괄호 안에 goodsId가 들어간다.
		//repository.selectGoodsById(goodsId);
		return "getGoods"; 
	}
	@GetMapping("/category/{categoryNo}/goods/{goodsId}")
	public String getCategoryGoods(@PathVariable("goodsId") String goodsId,
			@PathVariable("categoryNo") String categoryNo) {
		log.info("getCategoryGoods :{}, {}",categoryNo,goodsId); //중괄호 안에 goodsId가 들어간다.
		//repository.selectGoodsById(goodsId);
		return "getCategoryGoods"; 
	}
}
