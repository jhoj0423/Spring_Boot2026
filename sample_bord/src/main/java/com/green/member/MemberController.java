package com.green.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/member/login")
	public String loginForm() {
		System.out.println("loginForm(-_-) 확인용");
		return "/member/login_Form";
	}
	@GetMapping("/member/signup")
	public String signupForm() {
		System.out.println("signupForm(-_-) 확인용");
		return "/member/signup_Form";
	}
	
	@PostMapping("/member/signupPro")
	public String signupPro(MemberDTO mdto, Model model) {
		System.out.println("controller signupPro(*_*) 메소드확인");
		
		int result = memberService.signup_config(mdto);
		
		System.out.println("rrrr"+result);
		
		model.addAttribute("result",result);
		
		System.out.println("(*_*)"+result);
		if(result == memberService.user_id_success) {
			System.out.println("controller signupPro(*_*) 회원가입 성공");
			
			return "member/signup_result";
		}else {
			System.out.println("controller signupPro(*_*) 회원가입 실패");
			
			return "member/signup_result";
		}
		
	}
	
	@GetMapping("/member/signup_result")
	public String signupResultForm() {
		System.out.println("signup_result(-_-) 확인용");
		return "/member/signup_result";
	}
	
	@PostMapping("/member/loginPro")
	public String loginPro(MemberDTO mdto,HttpSession session) {
		System.out.println("MemberController loginPro(-v-)메소드 확인");
		
		MemberDTO loginMember = memberService.loginConfirm(mdto);
		
		if(loginMember != null) {
			//로그인 성공
			System.out.println("(--v--) 성공");
			session.setAttribute("loginmember", loginMember);
			return "redirect:/";
		}else {
			//로그인 실패
			System.out.println("(-v-) 실패");
			return"redirect:/member/login";
		}
	}
	
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		System.out.println("MemberController logout(-u-)메소드 확인");
		
		session.invalidate();
		
		return "redirect:/";
	}
	
}
