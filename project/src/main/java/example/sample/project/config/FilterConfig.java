package example.sample.project.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.sample.project.filter.LogFilter;
import example.sample.project.filter.LoginFilter;
import jakarta.servlet.Filter;

@Configuration
public class FilterConfig {
	//필터 로그인 안된 사용자가 /foods 나 /foods/food로 바로 접속하는것을 막기위해 설정함
	//각각 걸었던 유효성검사를 필터를 통해 한번에 걸러줌
//	@Bean
	public FilterRegistrationBean<Filter> logFilter(){
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();
		filterRegistrationBean.setFilter(new LogFilter());
		filterRegistrationBean.setOrder(1); //필터 순서 잡아두기
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}
	
//	@Bean
	public FilterRegistrationBean<Filter> loginFilter(){
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();
		filterRegistrationBean.setFilter(new LoginFilter());
		filterRegistrationBean.setOrder(2); //필터 순서 잡아두기
		filterRegistrationBean.addUrlPatterns("/*"); //경로설정. 모든경우
		return filterRegistrationBean;
	}
}
