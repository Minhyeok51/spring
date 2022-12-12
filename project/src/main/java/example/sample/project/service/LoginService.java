package example.sample.project.service;

import org.springframework.stereotype.Service;

import example.sample.project.domain.Member;
import example.sample.project.repository.ListMemberRepository;
import example.sample.project.repository.mybatis.MybatisMemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoginService {

//	private final ListMemberRepository memberRepository;
	private final MybatisMemberRepository memberRepository;
	
	public Member login(String loginId, String password) {
		Member member = memberRepository.selectByLoginId(loginId);
		
//		if(member == null) {
//			return null;
//		} else {
//			if(member.getPassword().equals(password)) {
//				return member;
//			}
//		}
//		return null;
		
		if(member != null) {
			if(member.getPassword().equals(password)) {
				return member;
			}
		}
		
		return null;
	}
}
