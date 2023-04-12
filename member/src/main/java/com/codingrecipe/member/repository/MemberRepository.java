package com.codingrecipe.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingrecipe.member.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>{
//										<어떤entity클래스를 다룰것이냐, entity클래스의 pk가 어떤타입인지>
	
}
