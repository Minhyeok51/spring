package com.codingrecipe.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingrecipe.member.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>{
//										<어떤entity클래스를 다룰것이냐, entity클래스의 pk가 어떤타입인지>
	
	//이메일로 회원 정보 조회(select * from member_table where member_email=?)
	//규칙을 잘 지켜주면 메서드에 맞는 쿼리가 알아서 만들어짐
	//Optional null방지해주는거
	Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
