package example.demo003.demo003.request;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import example.demo003.demo003.domain.DemoItem;
import example.demo003.demo003.domain.FoodItem;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SampleParamController {

	//파라미터 받는 과정 확인 할때 -> id,name
	@RequestMapping("/paramBasic")
	public void paramBasic(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		
		log.info("id ={}, name ={}", id, name);
		
		resp.getWriter().write("paramBasic");
	}
	
	//Controller는 화면(view)연결 시키는게 기본인데, 
		//@ResponseBody 를 붙이면 화면이 아니라 ResponseBody부분에 Text로 바로 전송. 
	@ResponseBody 
	@RequestMapping("/paramReqParams")
	public String paramReqParams(@RequestParam("id") String id,
									@RequestParam("name") String name) {

		log.info("id ={}, name ={}", id, name);
		
		return "paramReqParams";
	}
	
	@ResponseBody 
	@RequestMapping("/paramReqParamsSimple")
	public String paramReqParamsSimple(@RequestParam String id, //파라미터 이름과 매개변수이름 같으면 파라미터이름 생략 가능 
									@RequestParam String name) {

		log.info("id ={}, name ={}", id, name);
		
		return "paramReqParamsSimple";
	}
	
	@ResponseBody 
	@RequestMapping("/paramReqParamsRequired")
	public String paramReqParamsRequired(@RequestParam String id, //(required = false) - true is default 
									@RequestParam(required = false, defaultValue = "DefaultName") String name) {

		log.info("id ={}, name ={}", id, name);
		
		return "paramReqParamsRequired";
	}
	
	@ResponseBody 
	@RequestMapping("/paramReqParamsMap")
	public String paramReqParamsMap(@RequestParam MultiValueMap<String, Object> paramsMap) {

		log.info("id ={}, name ={}", paramsMap.get("id"),paramsMap.get("name"));
		
		return "paramReqParamsMap";
	}
	
	//파라미터      -> 값 추출 -> 새로운 객체만들기 -> Repository Insert
	//파라미터(Map) -> 값 추출 -> 새로운 객체만들기 -> Repository Insert
	//RequestParam 맵핑      -> 새로운 객체만들기-> Repository Insert
	
	@ResponseBody
	@RequestMapping("/paramReqModel")
	public String paramReqModel(@ModelAttribute DemoItem demoItem) {
		log.info("id ={}, name ={}",demoItem.getId(), demoItem.getName());
		return "paramReqModel";
	}
	
	@ResponseBody
	@RequestMapping("/paramReqSimple")
	public String paramReqSimple(DemoItem demoItem) {//파라미터에 어노테이션 빠져있어도 값은 다 들어온다. 
												//안들어오는 경우도 있음(DemoItem에 여러타입의 메소드나 변수잇는경우)
		log.info("id ={}, name ={}",demoItem.getId(), demoItem.getName());
		return "paramReqSimple";
	}
	//HTTP(REST) API -> Request Body Text 형태
	@PostMapping("/reqBodyText")
	public void reqBodyText(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ServletInputStream servletInputStream = req.getInputStream();
		String bodyText = StreamUtils.copyToString(servletInputStream, StandardCharsets.UTF_8);
		
		log.info("bodyText : {}",bodyText );
		resp.getWriter().write("reqBodyText");
	}
	
	@PostMapping("/reqBodyTextSimple")
	public void reqBodyTextSimple(InputStream inputStream, Writer writer) throws IOException {
		String bodyText = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
		
		log.info("bodyText : {}",bodyText );
		writer.write("reqBodyTextSimple");
	}
	
	@PostMapping("/reqBodyTextEntity")
	//	String -> viewName반환, BodyString반환 <--- @ResponseBody
	public HttpEntity<String> reqBodyTextEntity(HttpEntity<String> httpEntity){
			//리턴 타입이 HttpEntity<String>면 view도 아니고 그냥 바디에 바로 String으로 넣어줌 
		String bodyText =httpEntity.getBody();
		
		log.info("bodyText : {}", bodyText);
		HttpEntity<String> entity = new HttpEntity<String>("reqBodyTextEntity");
		
		return entity;
	}
	
	@ResponseBody
	@PostMapping("/reqBodyTextSuperSimple")
	public String reqBodyTextSuperSimple(@RequestBody String bodyText) {
		log.info("bodyText : {}",bodyText);
		return "reqBodyTextSuperSimple";
	}
	
	@PostMapping("/reqBodyJson")
	public void reqBodyJson(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ServletInputStream servletInputStream = req.getInputStream();
		String bodyText = StreamUtils.copyToString(servletInputStream, StandardCharsets.UTF_8);
		log.info("bodyText : {}",bodyText );

		//JsonFormat
		ObjectMapper objectMapper = new ObjectMapper();
		DemoItem demoItem = objectMapper.readValue(bodyText, DemoItem.class);
		log.info("json id : {} , json name : {} ", demoItem.getId(),demoItem.getName());
		resp.getWriter().write("reqBodyJson");
	}
	@ResponseBody
	@PostMapping("/reqBodyJsonSuperSimple")
	public String reqBodyJsonSuperSimple(@RequestBody String bodyText) throws JsonMappingException, JsonProcessingException {
		log.info("bodyText : {}",bodyText);
		
		ObjectMapper objectMapper = new ObjectMapper();
		DemoItem demoItem = objectMapper.readValue(bodyText, DemoItem.class);
		log.info("json id : {} , json name : {} ", demoItem.getId(),demoItem.getName());
		
		return "reqBodyJsonSuperSimple";
	}
	
	@ResponseBody
	@PostMapping("/reqBodyJsonSuperSuperSimple")
	//										@ModelAttribute DemoItem demoItem
	public String reqBodyJsonSuperSuperSimple(@RequestBody DemoItem demoItem) {
		log.info("json id : {} , json name : {} ", demoItem.getId(),demoItem.getName());
		return "reqBodyJsonSuperSuperSimple";
	}
	//@ModelAttribute -> parameter가 DemoItem객체의 필드변수에 맞게 넘어옴 -@ModelAttribute 생략가능
	//@RequestBody	  -> Requestbody에 텍스트가 넘어옴 (JsonFormat)으로 -@RequestBody생략하면 @ModelAttribute값으로 인식해서 parameter를 찾음
	
	@ResponseBody
	@PostMapping("/resqBodyJsonList")
	public DemoItem resqBodyJsonList(@RequestBody List<DemoItem> demoItem) {
		for(DemoItem data : demoItem) {
			log.info("json id : {} , json name : {} ", data.getId(),data.getName());
		}
		
		DemoItem di = new DemoItem();
		di.setId("respJsonId");
		di.setName("respJsonName");
		
		return di;
	}
	
	@ResponseBody
	@PostMapping("/resqBodyJsonListAll")
	public List<DemoItem> resqBodyJsonListAll() {
		log.info("resqBodyJsonListAll");
		List<DemoItem> demoItemList = new ArrayList<DemoItem>();
		
		DemoItem di1 = new DemoItem();
		di1.setId("respJsonId1");
		di1.setName("respJsonName1");
		
		DemoItem di2 = new DemoItem();
		di2.setId("respJsonId2");
		di2.setName("respJsonName2");
		
		DemoItem di3 = new DemoItem();
		di3.setId("respJsonId3");
		di3.setName("respJsonName3");
		
		demoItemList.add(di1);
		demoItemList.add(di2);
		demoItemList.add(di3);
		
		return demoItemList;
	}
	
	//test
	@ResponseBody
	@GetMapping("/foodList")
	public List<FoodItem> resqBodyJsonFoodList() {
		log.info("resqBodyJsonFoodList");
		List<FoodItem> foodList = new ArrayList<FoodItem>();
		
		FoodItem fi1 = new FoodItem("fd004","팬케이크","메이플 시럽 잔뜩",8000);
		FoodItem fi2 = new FoodItem("fd005","이름 모를 음식","보긴 많이 봤는데",13000);
		FoodItem fi3 = new FoodItem("fd006","피자","1인에 한판 피자",15000);
//		fi1.setId("fd004");
//		fi1.setItemName("팬케이크");
//		fi1.setContent("메이플 시럽 잔뜩");
//		fi1.setPrice(8000);
		
//		FoodItem fi2 = new FoodItem();
//		fi2.setId("fd005");
//		fi2.setItemName("이름 모를 음식");
//		fi2.setContent("보긴 많이 봤는데");
//		fi2.setPrice(13000);
//		
//		FoodItem fi3 = new FoodItem();
//		fi3.setId("fd006");
//		fi3.setItemName("피자");
//		fi3.setContent("1인에 한판 피자");
//		fi3.setPrice(15000);
//		
//		foodList.add(new FoodItem("fd004","팬케이크","메이플 시럽 잔뜩",8000));
		foodList.add(fi1);
		foodList.add(fi2);
		foodList.add(fi3);
		
		return foodList;
	}
	
}
