package	com.green.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.green.member.mapper.MemberMapper;

@Service
public class MemberService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	// id중복체크, 성공, 실패 상수변수 정의
		// 회원가입의 중복을 확인하는 상수
		public final static int user_id_alreday_exit=0;
		//회원가입의 성공 여부를 확인하는 상수
		public final static int user_id_success = 1;
		// 회원가입의 실패를 확인하는 상수
		public final static int user_id_fail = -1;
	
	public int signup_config(MemberDTO mdto) {
		System.out.println("Service signup_config(o0o) 메서드 확인");
		boolean chking = memberMapper.isMember(mdto.getId());
		System.out.println("chking 메서드 확인" + chking);
		if(chking == false) {
			// 비밀 번호를 암호화
			String encodepw = passwordEncoder.encode(mdto.getPw());
			// 암호화한 비밀번호로 수정
			mdto.setPw(encodepw);
			
			int result = memberMapper.insertMember(mdto);
			if(result>0) {
				return user_id_success;
			}else {
				return user_id_fail;
			}
			
		}else {
			// 중복입니다 => 0
			return user_id_alreday_exit;
		}
		
	}

	public MemberDTO loginConfirm(MemberDTO mdto) {
		System.out.println("MemberService loginConfirm(-^-^) 메소드 확인");
		MemberDTO dbMember = memberMapper.oneSelectMember(mdto.getId());
		System.out.println("(g5g)"+dbMember);
		System.out.println("(g5g)"+mdto.getId());
		if(dbMember != null && dbMember.getPw() != null) {
			if(passwordEncoder.matches(mdto.getPw(), dbMember.getPw())) {
				//로그인 성공한 경우
				
				return dbMember;
			}
		}
		
		return null;
	}
	
}
