package board.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class memberController {
	
	@Autowired
	memberService membersevice;
	
	
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
	
	// 회원가입 메서드
	@PostMapping("/member/signupPro")
	public String signupPro(memberDTO mdto,Model model) {
		System.out.println("signupPro(-_-) 확인용");
		boolean memberchk = membersevice.ismember(mdto.getId());
		if(memberchk) {
			//
		}
		
		return "redirect:/";
	}
	
}
