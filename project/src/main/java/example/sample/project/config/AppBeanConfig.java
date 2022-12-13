package example.sample.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.sample.project.repository.FoodRepository;
import example.sample.project.repository.MemberRepository;
import example.sample.project.repository.mybatis.FoodItemMapper;
import example.sample.project.repository.mybatis.MemberMapper;
import example.sample.project.repository.mybatis.MybatisFoodRepository;
import example.sample.project.repository.mybatis.MybatisMemberRepository;
import lombok.RequiredArgsConstructor;

@Configuration //설정에 관련된 것
@RequiredArgsConstructor
public class AppBeanConfig {

	private final MemberMapper memberMapper;
	private final FoodItemMapper foodItemMapper;
	@Bean
	public MemberRepository memberRepository() {
		return new MybatisMemberRepository(memberMapper);
//		return new ListMemberRepository();
	}
	
	@Bean
	public FoodRepository foodRepository() {
		return new MybatisFoodRepository(foodItemMapper);
		
	}
}
