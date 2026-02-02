package board.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.member.mapper.MemberMapper;

@Service
public class memberService {
	
	@Autowired
	MemberMapper memberMapper;
	
	// 회원 중복 확인
	public boolean ismember(String id) {
		System.out.println("ismember(^-^) 메서드 확인");
		
		if()
		
		return memberMapper.isMember(id);
	}
	
}
