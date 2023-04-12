package com.codingrecipe.member.entity;

import com.codingrecipe.member.dto.MemberDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

//Entity 일종의 테이블역할을 할것이다. spring data jpa - db테이블을 일종의 자바 객체처럼 사용하게 해줌
@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity {
	@Id //pk지정
	@GeneratedValue(strategy = GenerationType.AUTO) //auto_increment 오라클의 시퀀스 지정
	private Long id;
	
	@Column(unique = true, name="member_email") // unique 제약조건 추가
	private String memberEmail;
	
	@Column(name="member_password")
	private String memberPassword;
	
	@Column(name="member_name")
	private String memberName;
	
	public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
		MemberEntity memberEntity = new MemberEntity();
		memberEntity.setMemberEmail(memberDTO.getMemberEmail());
		memberEntity.setMemberPassword(memberDTO.getMemberPassword());
		memberEntity.setMemberName(memberDTO.getMemberName());
		return memberEntity;
	}
	
}
