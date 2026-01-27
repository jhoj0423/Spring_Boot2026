package com.green.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//컨트롤러에서 -> 서비스 : 다오 메소드 찾아서 있어? 
//다오 -> 디비
//디비 -> 아이디,비번 값 가지고 -> 다오로 보냄 -> 서비스의 메소드로 보냄
//-> 컨트롤러에게 아이디 비번 찾아서 보냄
// 컨트롤러 다오 연결다리=> 서비스

@Service
public class MemberService {
	
	// id중복체크, 성공, 실패 상수변수 정의
	// 회원가입의 중복을 확인하는 상수
	public final static int user_id_alreday_exit=0;
	//회원가입의 성공 여부를 확인하는 상수
	public final static int user_id_success = 1;
	// 회원가입의 실패를 확인하는 상수
	public final static int user_id_fail = -1;

	@Autowired
	MemberDAO memberdao;
	
	public int signupConfirm(MemberDTO mdto) {
		System.out.println("MemberService signConfirm() 메소드 확인");
		//회원가입 중복 체크
		boolean isMember = memberdao.isMember(mdto.getId());
		if(isMember == false) {
			//중복된 아이디가 존재하지 않을때 DB에 삽입되어야 한다.
			int result = memberdao.insertMember(mdto);
			if(result>0) {
				return user_id_success; // result =1
			}else {
				return user_id_fail; // result = -1
			}
		}else {
			return user_id_alreday_exit; // result = 0
		}
		
	}
	
	// 회원 전체 목록 출력하는 메소드
	public List<MemberDTO> selectMembersAll() {
		System.out.println("MemberService selectMembersAll() 메소드 확인");
		
		return memberdao.selectMemberAll();
	}
	//============================ 2026-01-27 서비스 로직 작성 시작 ==============================
	public MemberDTO oneSelect(String id) {
		System.out.println("MemberService oneSelect() 메소드 확인");
		
		return memberdao.oneSelectMember(id);
	}

	public String onePass(String id) {
		System.out.println("MemberService onePass() 메소드 확인");
		
		// void가 아닌이상 데이터 타입이 존재하면 반드시return반환값이 필요
		
		return memberdao.getPass(id);
	}
	
	//DB의 패스워드와 같은지 비교
	//DB의 패스워드와 내가 입력한 패스워드가 같을 때 실행문
	//DB의 패스워드와 내가 입력한 패스워드가 다를 때 실행문
	public boolean modifyMember(MemberDTO mdto) {
		System.out.println("MemberService modifyMember() 메소드 확인");
		//1. DB조회
		String dbPass = memberdao.getPass(mdto.getId());
		//2. if문 비교
		if(dbPass.equals(mdto.getPw()) && dbPass != null) {
			//내가 입력한 DB의 패스워드가 존재할 때
			return memberdao.updateMember(mdto) == 1;
		}else {
			//내가 입력한 DB의 패스워드가 존재하지 않을때
			
			return false;
		}
	}
	
	
	// 한사람의 회원만 정보를 삭제하는 메서드
	public boolean oneDelete(String id) {
		System.out.println("MemberService oneDelete() 메소드 확인");
		//현재 deleteMember() DAO의 결과 값이 result=0 또는 1 
		
		return memberdao.deleteMember(id)==1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
