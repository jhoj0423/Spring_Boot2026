package com.green.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.book.dao.MemberDAO;
import com.green.book.dto.MemberDTO;

@Service
public class MemberService {
	
	@Autowired
	MemberDAO memberDao;
	
	public void signupconfirm(MemberDTO mdto) {
		memberDao.insertMember(mdto);
	}
	
	public void loginconfirm(MemberDTO mdto) {
		MemberDTO logMem = memberDao.chkMember(mdto);
		
		
		if(logMem != null && logMem.getPw().equals(mdto.getPw())) {
			System.out.println("로그인 성공");
		}else {
			System.out.println("로그인 실패");
		}
	}
}
