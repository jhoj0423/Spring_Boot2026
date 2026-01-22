package com.green;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

//DAO는 쿼리문의 집합장소 => 데이터를 직접 처라하는 객체
// 데이터 저장소야 라는 DAO의 객체
@Repository
public class MemberDAO {
	
	// 원래 DB 커넥션이 존재햐야 하지만, 현재 DB가 존재하지 않으므로
	// HashMap<> 을 이용해 DB처럼 사용하고자 한다.
	private Map<String, MemberDTO> memberDB = new HashMap<>();
	
	
	//insertMember 메소드 생성
	public void insertMember(MemberDTO mdto) {
		System.out.println("회원가입을 추가하는 메소드야");
		memberDB.put(mdto.getId(),mdto);
		printMember();
		
	}
	
	//selectMember 메소드 생성
		public MemberDTO selectMember(MemberDTO mdto) {
			System.out.println("로그인의 정보를 확인하는 메소드야");
			// 아이디와 비밀번호를 비교해서 같으면 로그인 성공 아니면 실패
			MemberDTO loginMember = memberDB.get(mdto.getId());
			return loginMember;
		}
	
	//회원정보 출력
	public void printMember() {
		for(String key : memberDB.keySet()) {
			MemberDTO mdto = memberDB.get(key);
			System.out.println("아이디 : "+mdto.getId()+"비번 :"+mdto.getPw());
		}
	}
	
	
	
}
