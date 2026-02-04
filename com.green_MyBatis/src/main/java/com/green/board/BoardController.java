package com.green.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.green.Application;
import com.green.member.MemberDTO;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {

    private final Application application;
	
	@Autowired
	BoardService boardservice;

    BoardController(Application application) {
        this.application = application;
    }
	
	// 1. 게시글 작성 폼화면으로 이동하는 핸들러
	@GetMapping("/board/write")
	public String boardWrite_form() {
		System.out.println("boardWrite메서드 구동 확인");
		String nextPage ="board/boardWrite_form";
		return nextPage;
	}
	
	//2. 폼에서 입력한 데이터를 DB에 영구저장하는 데이터 추가를 처리하는 컨트롤러
	// ~~Pro => ~~을 처리하는 컨트롤러
	@PostMapping("/board/writePro")
	public String boardWritePro(BoardDTO bdto, HttpSession session) {
		System.out.println("boardWritePro메서드 구동 확인");
		
		//session.setAttritube("loginmember") 저장된 데이터를 꺼내와야 한다.
		//세션에서 값 꺼내오는 메소드 session.getAttribute("loginmember")
		//session 자바의 Object 최상위 객체이므로 다운 캐스팅 하여야 한다.
		MemberDTO loginedMember = (MemberDTO)session.getAttribute("loginmember");
		
		//로그인 정보가 존재하는지 체크하는 코드가 필요
		if(loginedMember != null) {
			//지금현재 로그인된 id => loginedMember.getId()
			bdto.setId(loginedMember.getId());
			System.out.println("DB에 담길 아이디 확인(^o^)"+loginedMember.getId());
		}else {
			System.out.println("로그인 필요");
			return "redirect:/member/login";
		}
		
		//서비스의 addBoard()매소드를 호출하여 DB에 저장
		boardservice.addBoard(bdto);
		
		// 저장 후에는 => 게시판 목록으로 페이지 이동(redirect)
		return "redirect:/board/list";
	}
	
	// 3. DB에서 전체 게시글 목록 select로 검색하여 추출 -> 모델객체 담는다
	// 전체목록 화면 boardList.html로 이동한다.
	
//	@GetMapping("/board/list")
//	public String boardList_form(Model model) {
//		System.out.println("boardList_form메서드 구동 확인");
//		List<BoardDTO> list = boardservice.allboard();
//		
//		
//		model.addAttribute("list",list);
//		String nextPage = "board/boardList";
//		return nextPage;
//	}
	// 검색을 위한 리스트 커스텀
	@GetMapping("/board/list")
	public String boardList_form(Model model,
			@RequestParam(value="searchType", required = false ) String searchType,
			@RequestParam(value="searchKeyword", required = false) String searchKeyword,
			//페이지 번호 1부터 시작이므로 초기값 1로 정의 
			@RequestParam(value="page",defaultValue="1") int page,
			// 한화면에 보여지는 페이지 수를 5로 초기화 한다.
			@RequestParam(value="pageSize",defaultValue="5") int pageSize
			) {
		System.out.println("boardList_form메서드 구동 확인");
		// 전체 게시글의 수
//		int totalCnt = boardservice.getAllcount();
		int totalCnt;
		
		if(searchType != null && !searchKeyword.trim().isEmpty()) {
			totalCnt=boardservice.getSearchCount(searchType, searchKeyword);
		}else {
			totalCnt=boardservice.getAllcount();
		}
		
		
		
		// 페이지 핸들러 인스터스화
		PageHandler p = new PageHandler(totalCnt, page, pageSize);
		
		
		boardservice.getPagelist(0, 0);
		List<BoardDTO> list;
		// 검색 종료 후 => 검색내용이 list 나오기
		if(searchType != null && !searchKeyword.trim().isEmpty()) {
			// service 에서 SearchBoard
			list = boardservice.getSearchPageList(searchType, searchKeyword, p.getStartRow(), pageSize);
		}else {
//			list = boardservice.allboard();
			list = boardservice.getPagelist(p.getStartRow(), pageSize);
		}
		
		// 검색하지 않고 전체 보기 list 나오기
		model.addAttribute("list",list);
		// PageHandler 클래스 모두 model객체에 담아서 html로 보내야 application
		// 그래야 UI 화면에 페이지 그릴 수 있다
		model.addAttribute("p",p); // PageHandler클래스를 인스턴스한 참조변수이다.
		
		// 서치하는 항목이 타입과, 항목을 UI 넘겨주지 않으면 ,오류 난다
		//반드시 searchType,searchKeyword 를 model담아서 boardList.html로 넘겨준다.
		model.addAttribute("searchType",searchType);
		model.addAttribute("searchKeyword",searchKeyword);
		String nextPage = "board/boardList";
		return nextPage;
	}
	
	//4. 하나의 게시글 상세정보 확인 핸들러
	// nym 글번호 받아-> 해당 게시긓 DB에서 조회하소 그상세정보를 
	//boradInfo 저장하는 컨트롤러
	@GetMapping("/board/boardInfo")
	public String boardInfo(Model model,@RequestParam("num") int num) {
		System.out.println("boardInfo메서드 구동 확인");
		BoardDTO oneboardInfo = boardservice.oneboard(num);
		
		model.addAttribute("oneboard",oneboardInfo);
		String nextPage = "board/boardInfo";
		return nextPage;
	}
	
	// 5. 게시글의 수정폼으로 이동하는 컨트롤러
	@GetMapping("/board/update")
	public String boardUpdateForm(Model model,@RequestParam("num") int num) {
		System.out.println("boardUpdateForm메서드 구동 확인");
		// 기존의 하나의 게시글을 불러오는 쿼리를 이용하여 수정한다.
		BoardDTO oneboardInfo = boardservice.oneboard(num);
		
		model.addAttribute("oneboard",oneboardInfo);
		String nextPage = "board/boardUpdate_form";
		return nextPage;
	}
	
	// 6. 하나의 게시글 수정을 처리하는 컨트롤러
	@PostMapping("/board/updatePro")
	public String boardUpdatePro(Model model,BoardDTO bdto) {
		System.out.println("boardUpdatePro(^^)메서드 구동 확인");
		
		boolean chk = boardservice.modify(bdto);
		//수정완료면 true, 아니면 false
		if(chk) {
			// 수정 성공시 리스트로
			return "redirect:/board/list";
		}else {
			// 실패시 현재 수정하고있는 폼에 num을 가지고 존재해야함
			return "redirect:/board/update?num="+bdto.getNum();
		}
		
	}
	
	
	// ====================== 2026-01-29 수정부분 ====================
	//7. 하나의 게시글을 삭제하는 컨트롤러
	//현재 boardInfo.html의 [삭제하기]버튼 클릭하면 삭제됨
	//삭제된 후 board/list로 이동
	// 삭제 실패 후는 boardInfo.html에 머문다.
	@GetMapping("/board/deletePro")
	public String boardDeletePro(
			@RequestParam("num") int num,
			@RequestParam("writerPw") String writerPw
			) {
		System.out.println("boardDeletePro(^o^)메서드 구동 확인");
		// boardService removeBoard()메소드 삭제 :true 실패: false
		boolean ischk = boardservice.removeBoard(num, writerPw);
		
		if(ischk) {
			// 성공시 리스트로 이동
			return "redirect:/board/list";
		}else {
			//삭제 실패시 페이지 그대로
			return "redirect:/board/boardInfo?num"+num;			
		}
	}
	
	// 로그인된 나의 게시글 목록을 검색하는 핸들러
	@GetMapping("/board/mypage")
	public String myBoardList(Model model, HttpSession session,@RequestParam(value = "page",defaultValue = "1") int page ) {
		
		// 세션 키 이름을 loginmember로 가져오기
		// 세션 키 값 가져오는 메소드 : getAttribute("loginmember")
		// id="kkk" 해당하는 행 전체
		// 다운캐스팅 하기
		// loginId 에 MemberDTO의 멤버변수 모두 저장됨을 주의하자
		MemberDTO loginId = (MemberDTO)session.getAttribute("loginmember");
		
		// 로그인이 실패 또는 로그인이 안된 상태라면 => member/login 로 이동
		if(loginId == null) {
			System.out.println("로그인 정보가 없습니다.");
			return "redirect:/member/login";
		}
		
		int pageSize = 5;
		//로그인된 내 게시긓의 개수 조회
		int totalCnt = boardservice.getMyBoardCount(loginId.getId());
		
		PageHandler ph = new PageHandler(totalCnt, page, pageSize);
		
		// 로그인된 내 게시글의 목록
		List<BoardDTO> mylist = boardservice.getMyBoardList(loginId.getId(), ph.getStartRow(), pageSize);
		
		model.addAttribute("list",mylist);
		model.addAttribute("ph",ph);
		
		
		return "/board/mypage";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
