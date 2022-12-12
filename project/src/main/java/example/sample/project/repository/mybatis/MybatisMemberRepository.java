package example.sample.project.repository.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import example.sample.project.domain.Member;
import example.sample.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MybatisMemberRepository implements MemberRepository{

	private final MemberMapper memberMapper;
	
	@Override
	public Member insert(Member member) {
		memberMapper.insert(member);
		return member;
	}

	@Override
	public Member selectById(int id) {
		Member member = memberMapper.selectById(id);
		return member;
	}

	@Override
	public Member selectByLoginId(String loginId) {
		Member member = memberMapper.selectByLoginId(loginId);
		return member;
	}

	@Override
	public List<Member> selectAll() {
		List<Member> members = memberMapper.selectAll();
		return members;
	}

	@Override
	public void deleteAll() {
		memberMapper.deleteAll();
	}

}
