package com.green.member;

import java.util.List;

import com.green.homeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		System.out.println("MemberController memberList()메소드 확인");
		//MemberService 의 selectMembersAll() 메서드
		List<MemberDTO>memberlist = memberservice.selectMembersAll();
		model.addAttribute("list",memberlist);
		
		
		String nextPage = "member/memberList";
		return nextPage;
	}
	
	// ==================== 2026-01-27 컨트롤러 수정 =======================
	// 개인의 정보를 상세보기하는 핸들러
	@GetMapping("/member/memberInfo")
	public String memberInfo(Model model, MemberDTO mdto) {
		System.out.println("MemberController memberInfo()메소드 확인"+mdto.getId());
		MemberDTO oneMemberInfo = memberservice.oneSelect(mdto.getId());
		model.addAttribute("onelist",oneMemberInfo);
		String nextPage = "member/memberInfo";
		return nextPage;
	}
	
	//개인 정보 수정하는 핸들러
	@GetMapping("/member/modify")
	public String modifyForm(MemberDTO mdto,Model model) {
		System.out.println("MemberController modifyForm()메소드 확인" + mdto);
		// 개인수정 화면 그릴때 필요한 정보 : 한사람의 데이터
		// oneSelect(String id) : memberservice의 메소드
		MemberDTO oneModify = memberservice.oneSelect(mdto.getId());
		model.addAttribute("mmb",oneModify);
		String nextPage = "member/member_modify";
		return nextPage;
	}
	
	// 개인정보 수정을 처리하는 핸들러
	// 비밀번호가 일치하는 지의 비교에 관련된 핸들러
	// redirect 사용해볼것
	// modifyMember() 메소드 이용해볼것
	
	@PostMapping("/member/modify")
	public String modifysubmit(MemberDTO mdto, RedirectAttributes ra) {
		System.out.println("MemberController modifysubmit()메소드 확인");
		boolean result = memberservice.modifyMember(mdto);
		// 지금 result의 값은 true 또는 false
		
		System.out.println(mdto.getId());
		
		if(result) {
			// update 성공
			//RedirectAttributes 한번만 데이터를 넘길 수 있다.
			ra.addFlashAttribute("msg","회원정보가 수정되었습니다.");
			// 수정이 완료되면 list의 url => /member/list 
			return "redirect:/member/list";
		}else {
			//비밀번호가 틀렸을때
			ra.addFlashAttribute("msg","비밀번호가 잘못되었습니다");
			return "redirect:/member/modify?id="+mdto.getId();
		}
		// 내가 가게를 갔는데 가게가 이전을 했어 -> redirect model 적용안되는 이유?
	}
	
	
	// 개인 한사람의 정보를 삭제하는 핸들러
	@GetMapping("/member/delete")
	public String deleteMember(@RequestParam("id") String id, RedirectAttributes ra) {
		System.out.println("MemberController deleteMember()메소드 확인");
		boolean result =  memberservice.oneDelete(id);
		// result 삭제가 된경우 true, 삭제가 되지 않은 경우 false
		if(result) {
			// 입력된 id가 존재하는 그래서 삭제된 경우
			ra.addFlashAttribute("msg","회원이 삭제되었습니다.");
			//삭제된 경우 List URL => /member/list
			return "redirect:/member/list";
		}else {
			//삭제가 진행되지 않은경우
			ra.addFlashAttribute("msg","삭제 실패");
			//삭제가 실패한 경우 
			return "redirect:/member/memberInfo?id="+id;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
