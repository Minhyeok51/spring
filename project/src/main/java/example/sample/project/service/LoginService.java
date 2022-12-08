package example.sample.project.service;

import org.springframework.stereotype.Service;

import example.sample.project.domain.Member;
import example.sample.project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoginService {

	private final MemberRepository memberRepository;
	
	public Member login(String loginId,String password) {
		Member member = memberRepository.selectByLoginId(loginId);
		if(member != null) {
			if(member.getPassword().equals(password)) {
				return member;
			}
		}
		return null;
	}
}
