package example.demo003.demo003.request;

import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SampleHeaderController {

	
	@RequestMapping("/sample/header")
	public String showHeader(HttpMethod httpMethod,
			@RequestHeader("host") String host,
			@RequestHeader MultiValueMap<String,String> headerMap) {
		log.info("showHeader");
		
		if(httpMethod == HttpMethod.POST || httpMethod == HttpMethod.PUT) {
			
		}
		log.info("method {}",httpMethod);
		log.info("host {}",host);
		log.info("headerMap {}",headerMap);
		return "showHeader";
	}
}
