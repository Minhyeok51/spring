package example.demo003.demo003.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/checkLog")
	public String checkLog() {
		logger.info("checkLog");
		System.out.println("checkLog");
		
		return "checkLog";
	}
}
