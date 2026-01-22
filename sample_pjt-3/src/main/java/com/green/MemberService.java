package com.green;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// MemberService 클래스는 비즈니스 로직 작성하는 크래스이다.
// Service 의 의미이다.
@Service
public class MemberService {

	//MemberDAO 클래스를 MemberService클래스에서 사용하는 방법
	//DI를 이용해 외부로 부터 객체를 주입해서 사용한다.
	//DI를 의미하는 @AutoWired를 사용한다.
	@Autowired
	MemberDAO memberDAO;
	
	public void signUpConfirm(MemberDTO mdto) {
		System.out.println("회원가입 출력화면 이야~");
		memberDAO.insertMember(mdto);
	}

	public void signInConfirm(MemberDTO mdto) {
		System.out.println("로그인 정보 출력화면 이야~");
		MemberDTO loginMember =memberDAO.selectMember(mdto);
		
		
				
		//if문을 이용해서 ID,PW 가 DB에 존재하면 로그인 성공 아니면 로그인 실패
		//=> 로그인할 id,pw입력하지 않은채로 로그인을 실행했을 때 발생하는 오류이다
		//그러므로 반드시 null을 예외 처리
		if(loginMember != null && mdto.getPw().equals(loginMember.getPw())) {
			System.out.println("id : "+loginMember.getId());
			System.out.println("pw : "+loginMember.getPw());
			System.out.println("로그인 성공");
		}else {
			System.out.println("로그인 실패");
			
		}
		
	}
	
	
	
	

}
