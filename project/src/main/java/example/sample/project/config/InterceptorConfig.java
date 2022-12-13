package example.sample.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import example.sample.project.interceptor.LogInterceptor;
import example.sample.project.interceptor.LoginInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

	public void addInterceptors(InterceptorRegistry registry) {//별표 두개붙여야 전체경로로 인식함
//		registry.addInterceptor(new LogInterceptor()).order(1).addPathPatterns("/**").excludePathPatterns("/css/**","/js/**");
//		
//		registry.addInterceptor(new LoginInterceptor()).order(2).addPathPatterns("/**").excludePathPatterns("/","/login","/logout","/members/new");
	}
}
