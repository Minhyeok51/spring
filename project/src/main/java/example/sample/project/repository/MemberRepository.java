package example.sample.project.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import example.sample.project.domain.Member;

@Repository
public class MemberRepository {
	private static List<Member> db = new ArrayList<>();
	private static int seq = 1;
	

	public Member insert(Member member) {
		member.setId(seq++);
		db.add(member);
		return member;
	}
	public Member selectById(int id) {
		for(Member member : db) {
			if(member.getId() == id) {
				return member;
			}
		}
		return null;
	}
	
	public Member selectByLoginId(String loginId) {
		for(Member member : db) {
			if(member.getLoginId().equals(loginId)) {
				return member;
			}
		}
		return null;
	}
	
	public List<Member> selectAll() {
		return db;
	}
	
	public void deleteAll() {
		db.clear();
	}
}
