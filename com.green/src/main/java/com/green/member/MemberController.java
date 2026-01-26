package com.green.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
	
	//MemberService 클래스를 DI로 의존객체화 해야한다
	@Autowired
	MemberService memberservice;
	
	//회원가입 양식 폼
	@GetMapping("/member/signup")
	public String signup() {
		System.out.println("MemberController signup()메소드 확인");
		String nextPage ="member/signup_form";
		return nextPage;
	}
	
	//회원가입 확인
	@PostMapping("/member/signup_confirm")
	public String signupConfirm(MemberDTO mdto, Model model) {
		System.out.println("MemberController signupConfirm()메소드 확인");
		String nextPage ="member/signup_result";
		//회원가입이 제대로 되었는지 , 실패하였는지 예외처리
		int result = memberservice.signupConfirm(mdto);
		
		//회원가입이 성공하였을 경우 => 회원 목록인 새로운 주소로 이동
		if(result == MemberService.user_id_success) {
			return "redirect:/member/list";
		}else {
			model.addAttribute("result",result);
			return nextPage;
		}
		
		
	}
	
	// 회원 전체 목록 화면 호출
	@GetMapping("/member/list")
	public String memberList(Model model) {
		//MemberService 의 selectMembersAll() 메서드
		List<MemberDTO>memberlist = memberservice.selectMembersAll();
		model.addAttribute("list",memberlist);
		
		
		String nextPage = "member/memberList";
		return nextPage;
	}
}
