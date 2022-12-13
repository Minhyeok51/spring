package example.sample.project.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import example.sample.project.domain.Member;

@Mapper //mybatis용도로 사용하는 부분
public interface MemberMapper {

	//선언부만 남겨놓기 
	public Integer insert(Member member);

	public Member selectById(int id);

	public Member selectByLoginId(String loginId);
	
	public List<Member> selectAll();
	
	public void deleteAll();
}
