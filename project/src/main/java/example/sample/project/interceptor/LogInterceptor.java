package example.sample.project.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogInterceptor implements HandlerInterceptor{

	//전에 수행
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String uri = request.getRequestURI();
		String test = "test text";
		request.setAttribute("test", test);
		
		log.info("LogInterceptor preHandle {},{}",uri,test);
		return true;//계속 진행
	}
	
	//후에 수행
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		log.info("LogInterceptor postHandle");
		log.info("test {}", request.getAttribute("test"));
		log.info("ModelAndView {}", modelAndView);
	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		log.info("LogInterceptor afterCompletion");
		log.info("test {}", request.getAttribute("test"));
		log.info("getDispatcherType {}",request.getDispatcherType());
		if(ex != null) {
			log.error("LogInterceptor afterCompletion Exception", ex);
		}
	}
	//try catch finally(끝나고 무조건 실행 = afterCompletion)
}
