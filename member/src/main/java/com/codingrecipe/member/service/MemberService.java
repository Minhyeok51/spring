package com.codingrecipe.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingrecipe.member.dto.MemberDTO;
import com.codingrecipe.member.entity.MemberEntity;
import com.codingrecipe.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service	//spring이 관리해주는 객체 spring bean으로 등록
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
 
	public void save(MemberDTO memberDTO) {
		//1. dto -> entity변환
		//2. repository의 save 메서드 호출
		MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
		//repository의 save메서드 호출(조건. entity객체를 넘겨줘야 함) 
		memberRepository.save(memberEntity);
	}

	public MemberDTO login(MemberDTO memberDTO) {
		/*
		 	1.회원이 입력한 이메일로 DB에서 조회를 함
		 	2.DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
		 */
		//사용할건 entity객체인데 그걸 optional객체로 한번 더 감싸는 느낌 포장지가 2개인느낌
		Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
		if(byMemberEmail.isPresent()) {
			//조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다)
			MemberEntity memberEntity = byMemberEmail.get();
			if(memberEntity.getMemberEmail().equals(memberDTO.getMemberPassword())) {
				//비밀번호 일치
				//entity -> dto객체로 변환 후 리턴
				//entity는 service에서만 사용 dto는 controller에서 사용하게끔 코딩할거
				MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
				return dto;
			}else {
				//비밀번호 불일치(로그인 실패)
				return null;
			}
		}else {
			//조회 결과가 없다(해당 이메일을 가진 회원이 없다)
			return null;
		}
	}

	public List<MemberDTO> findAll() {
		List<MemberEntity> memberEntityList = memberRepository.findAll();
		List<MemberDTO> memberDTOList = new ArrayList<>();
		for(MemberEntity memberEntity : memberEntityList) {
			memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
			//MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
			//memberDTOList.add(memberDTO);	랑 같은거
		}
		return memberDTOList;
	}

	public MemberDTO findById(Long id) {
		//하나를 조회할떄는 기본적으로 옵셔널객체로 감싸서 조회
		Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
		if(optionalMemberEntity.isPresent()) {
//			MemberEntity memberEntity = optionalMemberEntity.get();
//			MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
//			return memberDTO;
			return MemberDTO.toMemberDTO(optionalMemberEntity.get());
		} else {
			return null;
		}
	}

	public MemberDTO updateForm(String myEmail) {
		Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);
		if(optionalMemberEntity.isPresent()) {
			return MemberDTO.toMemberDTO(optionalMemberEntity.get());
		}else {
			return null;
		}
	}

	public void update(MemberDTO memberDTO) {
		//jpa에 update는 없음 save통해서 가능.
		//id는 애초에 자동생성되었음
		//그래서 toUpdateMemberEntity메소드에 id도 세팅해주는 부분 추가해줘야함
		//안그러면 update쿼리가 아닌 insert쿼리가 날려짐
		memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
	}

}
