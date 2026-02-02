package com.green.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.member.MemberDTO;

@Mapper// MemberMapper은 매퍼(연결 : SQL=xml파일)역활을 한다.
public interface MemberMapper {
	//MemberDAO의 메소드들을 추상메소드로 작성한다.
	// 설정된 메소드들은 IoC컨테이너에 탑재한다.
	
	// 회원가입 추가하는 추상메소드
	public int insertMember(MemberDTO mdto);
	// 회원가입한 유저 모두 출력하는 추상 메소드
	public List<MemberDTO> selectMemberAll();
	// 아이디 중복체크 입력된 아이디 존재하는데 그 아이디로 회원가입하면
	// 회원가입 실패 출력
	public boolean isMember(String id);
	
	
	// 개인 한사람의 정보를 검색하는 추상 메소드
	public MemberDTO oneSelectMember(String id);
	// 개인 한사람의 정보를 수정하는 추상 메소드
	// 단 정보수정 할때 password 일치하는지확인
	public int updateMember(MemberDTO mdto);
	
	// 개인 한사람의 패스워드 리턴
	public String getPass(String id);
	
	// 한사람 개인의 정보를 삭제하는 추상 메소드 작성
	public int deleteMember(String id);
}
