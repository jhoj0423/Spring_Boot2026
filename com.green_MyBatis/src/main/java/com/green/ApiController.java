package com.green;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
// @RestController는 @controller + @ResponseBody를 합친 어노테이션이다
// -> 컨트롤러 역활 + 데이터를 JSON으로 응답하여 사용
// @ResponseBody는 메소드가 변환하는 데이터를 HTML 뷰를 찾는 용도가
// 아니라, 데이터 그 자체(JSON)로 응답 받아 직접쓰겠다는 의미
// @Restcontroller 하나만 맨 위에 적어주면 모든 메서드들은
// @ResponseBody 붙이지 않아도 된다
import org.springframework.web.multipart.MultipartFile;

import com.green.carproduct.CarProductDTO;
import com.green.carproduct.CarProductService;
import com.green.member.MemberDTO;
import com.green.member.MemberService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	CarProductService carProductservice; // carList 메서드
	
	@Autowired
	MemberService memberService;
	
	// 자동차 리스트 JSON으로 변환하는 API
	@GetMapping("/cars")
	public List<CarProductDTO> getCarList(){
		System.out.println("ApiController getCarList(UvU) 메소드 확인");
		//DB에서 데이터를 가져와서 그대로 리턴(Spring가 자동으로 JSON배열로 변환)
		return carProductservice.getAllCarProduct();
	}
	
	
	// 회원가입 API(POST방식)
	// @RequestBody 는 React에서 보낸 JSON데이터를 
	// -> 자바 객체(MemberDTO)로 자동 변환해준다.
	@PostMapping("/member/signup")
	public int signup(@RequestBody MemberDTO mdto) {
		System.out.println("ApiController signup(UvU) 메소드 확인");
		
		return memberService.signupConfirm(mdto);
	}
	
	@PostMapping("/member/login")
	public MemberDTO login(@RequestBody MemberDTO mdto,HttpSession session) {
		System.out.println("ApiController login(UvU) 메소드 확인");
		MemberDTO loginUser = memberService.loginConfirm(mdto);
		
		if(loginUser != null) {
			session.setAttribute("loginUser", loginUser.getId());
			System.out.println("ApiController session( OㅠO) 확인");
		}
		
		// React로 Json변환
		return loginUser;
	}
	
	
	// 로그아웃
	@PostMapping("/member/logout")
	public int logout(HttpSession session) {
		session.invalidate(); // 세션 삭제
		return 1;
	}
	
	// 한사람의 개인정보를 조회하는 메서드
	// select
	@GetMapping("/member/myinfo")
	public MemberDTO mypage_From(MemberDTO mdto,HttpSession session) {
		System.out.println("ApiController mypage_From( OㅠO) 확인"+mdto.getId());
		// 세션에서 로그인한 사용자 꺼내기
		String loginId = (String) session.getAttribute("loginUser");
		if(loginId == null) {
			return null;
		}
		
		return memberService.oneSelect(loginId);
	}
	
	// 한사람의 개인정보를 삭제하는 메서드
	// 삭제한다 => DeletMMapping
	@DeleteMapping("/member/delete")
	public int delete(HttpSession session) {
		System.out.println("ApiController delete( OㅠO) 확인");
		// 세션에서 로그인한 사용자 꺼내기
		String loginId = (String) session.getAttribute("loginUser");
		if(loginId == null) {
			return 0;
		}
		
		// 삭제 서비스 메소드
		boolean result = memberService.oneDelete(loginId);
		if(result) {
			session.invalidate();
			return 1;
		}else {
			return 0;
		}
		
	}
	
	
	// 이미지 React에서 업로드해서 DTO에 한번애 받기
	// @ModelAttribute는 스프링 프레임 워크에서 클라이언트가 ㅂ낸
	// 데이터를 자바 객체(DTO)로 자동 바인딩(연결)해주는 어노테이션이다
	@PostMapping("/cars/insert")
	public int insertCarProduct(
			@ModelAttribute CarProductDTO cdto ,
			@RequestParam("uploadFile")MultipartFile file
			) throws Exception{
		System.out.println("자동차 등록 요청");
		
		//저장경로
		String savePath = "C:/Spring_Boot/com.green_MyBatis/frontend/public/img/car/";
				
		//저장할 경로가 졵하지 않으면 자동 생성해주는 코드
		File dir = new File(savePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		
		String fileName="";
		if(!file.isEmpty()) {
			//사용자가 올린 파일명을 가져온다.
			String originalName = file.getOriginalFilename();
			
			// 사용자명 중복해서 입력되지 않도록 UUID클래스 이용
			fileName = UUID.randomUUID().toString().substring(0,4)+"_"+originalName;
			
			File saveFile = new File(savePath + fileName);
			file.transferTo(saveFile);
		}
		
		// DTO중 setImg()에 파일명만 세팅한다.
		cdto.setImg(fileName);
		
		// DB에 저장
		carProductservice.insertCarProduct(cdto);
		
		return 1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
