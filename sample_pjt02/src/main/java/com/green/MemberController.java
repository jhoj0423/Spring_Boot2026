package com.green;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class MemberController {

    private final SamplePjt02Application samplePjt02Application;

    MemberController(SamplePjt02Application samplePjt02Application) {
        this.samplePjt02Application = samplePjt02Application;
    }
	
	// 아래 작성 메소드 핸드러 메소드들이다
	// 회원가입 양식
	//http://localhost:8090/member/signup의 경로를 매핑(=연결)한다.
	@GetMapping("/member/signup")
	public String signUpForm() {
		// 아래 프린트문을 log역활
		System.out.println("signUpForm()");
		return "signUpForm";// 응답에 사용하는 html파일 이름
	}
	
//	
//	//로그인 양식
	@GetMapping("/member/signin")
	public String signInForm() {
		System.out.println("signInForm()");
		//src/main/resours/templates/signinForm.html
		//매핑한다. => 매핑 URL 주소는 
		return "signinForm";// 응답에 사용하는 html파일 이름
	}
//	// 회원가입한 데이터가 signUpResult페이지로 전달되는 메소드
//	// 숨겨서 가는 @PostMapping()사용한다.
//	// 가입한 자료를 매개변수로 넘겨줘야 하므로 @RequestParam 사용한다.
//	//public String sign
//	@PostMapping("/member/signUp_confirm")
//	public String signupconfirm(
//			@RequestParam(value="id") String id,
//			@RequestParam("pw") String pw,
//			@RequestParam("email") String email,
//			Model model //모델에 (id,pw,email)담아서 데이터 전달
//			) {
//		
//		//현재 가입한 시간을 출력하는 로직을 작성
//		Date date = new Date();
//		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		
//		
//		
//		
//		model.addAttribute("now", time.format(date));
//		model.addAttribute("new_member_id", id);
//		model.addAttribute("new_member_pw", pw);
//		model.addAttribute("new_member_email", email);
//		
//		System.out.println("signupconfirm()");
//		return "signUpResult";
//	}
//	
//	@PostMapping("/member/signIn_confirm")
//	public String signinconfirm(
//			@RequestParam("id") String id,
//			@RequestParam("pw") String pw,
//			Model model
//			) {
//		
//		model.addAttribute("id", id);
//		model.addAttribute("pw", pw);
//		
//		System.out.println("signinconfirm()");
//		return "signinResult";
//	}
	
	

	@PostMapping("/member/signUp_confirm")
	public String signupconfirm(
			@RequestParam(value="id") String id,
			@RequestParam("pw") String pw,
			@RequestParam("email") String email,
			Model model //모델에 (id,pw,email)담아서 데이터 전달
			) {
		
		//현재 가입한 시간을 출력하는 로직을 작성
		Date date = new Date();
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		
		
		model.addAttribute("now", time.format(date));
		model.addAttribute("new_member_id", id);
		model.addAttribute("new_member_pw", pw);
		model.addAttribute("new_member_email", email);
		
		System.out.println("signupconfirm()");
		return "signUpResult";
	}
	// modelAndView 클래스는 model과 view를 하나로 합쳐서 클라이언트에 전달한다.
	@PostMapping("/member/signIn_confirm")
	public ModelAndView signinconfirm(
			@RequestParam("id") String id,
			@RequestParam("pw") String pw
			//Model model
			) {
		
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("id", id);
		modelview.addObject("pw", pw);
		//view페이지 signinResult.html은 어떻게 추가하나?
		modelview.setViewName("signinResult");
		
		//model.addAttribute("id", id);
		//model.addAttribute("pw", pw);
		
		System.out.println("signinconfirm()");
		return modelview;
	}
}
