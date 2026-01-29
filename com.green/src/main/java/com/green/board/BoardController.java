package com.green.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardservice;
	
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
	public String boardWritePro(BoardDTO bdto) {
		System.out.println("boardWritePro메서드 구동 확인");
		
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
			@RequestParam(value="searchKeyword", required = false) String searchKeyword
			) {
		System.out.println("boardList_form메서드 구동 확인");
		List<BoardDTO> list;
		// 검색 종료 후 => 검색내용이 list 나오기
		if(searchType != null && !searchKeyword.trim().isEmpty()) {
			// service 에서 SearchBoard
			list = boardservice.SearchBoard(searchType, searchKeyword);
		}else {
			list = boardservice.allboard();
		}
		
		// 검색하지 않고 전체 보기 list 나오기
		model.addAttribute("list",list);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
