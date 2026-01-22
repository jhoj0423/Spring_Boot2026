package com.green.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.book.dao.MemberDAO;
import com.green.book.dto.MemberDTO;
import com.green.book.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberservice;
	
	@Autowired
	MemberDAO memberDao;
	
	
	@GetMapping("/book/signup")
	public String signupForm(MemberDTO mdto,Model model) {
		System.out.println("signupForm()");
		
		
		model.addAttribute("Id",mdto.getId());
		model.addAttribute("Pw",mdto.getPw());
		model.addAttribute("userName",mdto.getUserName());
		model.addAttribute("email",mdto.getEmail());
		
		
		
		return "signup";
	}
	
	@PostMapping("/book/signin")
	public String loginForm(MemberDTO mdto) {
		System.out.println("loginForm()");
		memberservice.signupconfirm(mdto);
		
		System.out.println("회원가입 성공");
		System.out.println(mdto.getId());
		System.out.println(mdto.getPw());
		System.out.println(mdto.getUserName());
		System.out.println(mdto.getEmail());
		
		
		
		return "signin";
	}
	
	@PostMapping("/book/myPage")
	public String myPageForm(MemberDTO mdto,Model model) {
		System.out.println("myPageForm()");
		
		memberservice.loginconfirm(mdto);
		System.out.println(mdto.getUserName()+"확인용2");
		System.out.println(mdto+"확인용3");
		System.out.println(mdto.getId()+"확인용4");
		model.addAttribute("userName",memberDao.getMemberDB(mdto).getUserName());
		
		return "myPage";
	}
}
