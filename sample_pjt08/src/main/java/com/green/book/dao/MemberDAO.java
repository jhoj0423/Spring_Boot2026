package com.green.book.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.green.book.dto.MemberDTO;

@Repository
public class MemberDAO {
	
	private Map<String, MemberDTO> memberDB = new HashMap<>();
	
	public void insertMember(MemberDTO mdto) {
		System.out.println("회원 추가");
		memberDB.put(mdto.getId(), mdto);
	}
	
	public MemberDTO chkMember(MemberDTO mdto) {
		System.out.println(mdto.getId());
		MemberDTO logMem = memberDB.get(mdto.getId());
		System.out.println(logMem+"확인용1");
		return logMem;
	}

	public MemberDTO getMemberDB(MemberDTO mdto) {
		return memberDB.get(mdto.getId());
	}
	

	public Map<String, MemberDTO> getMemberDBAll() {
		return memberDB;
	}

	public void setMemberDB(Map<String, MemberDTO> memberDB) {
		this.memberDB = memberDB;
	}
	
	
}
