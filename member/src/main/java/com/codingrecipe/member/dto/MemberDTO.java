package com.codingrecipe.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor //기본생성자를 자동으로 만들어주는 기능
@AllArgsConstructor //필드를 모두다 매개변수로하는 생성자를 만들어준다
@ToString
public class MemberDTO {
	private Long id;
	private String memberEmail;
	private String memberPassword;
	private String memberName;
}
