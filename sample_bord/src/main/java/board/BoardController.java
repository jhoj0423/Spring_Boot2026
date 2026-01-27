package board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	// 게시판 리스트 출력
	@GetMapping("/board/boardList")
	public String boardList(Model model) {
		System.out.println("boardList() 확인용입니다");
		List<BoardDTO> boardList = boardService.selectBoardAll();
		model.addAttribute("List",boardList);
		return "board/boardList";
	}
	// 게시판 내용 페이지 출력
	@GetMapping("/board/boarderWrite")
	public String boarderWrite() {
		System.out.println("boarderWrite() 확인용입니다");
		return "board/boarderWrite";
	}
	//게시판 작성 페이지 출력
	@GetMapping("/board/boardResult")
	public String boardResult() {
		System.out.println("boardResult() 확인용입니다");
		return "board/boardResult";
	}
	//게시판 수정 페이지 출력
	@GetMapping("/board/boardReWrite")
	public String boardReWrite() {
		System.out.println("boardReWrite() 확인용입니다");
		return "board/boardReWrite";
	}
	//게시판 내용 페이지 출력
	@GetMapping("/board/boardWrite_confrim")
	public String boardWrite_confrim(BoardDTO bdto,Model model) {
		System.out.println("boardWrite_confrim() 확인용입니다");
		int result = boardService.writeConfirm(bdto);
		System.out.println("boardWrite_confrim() reulst값 = "+result);
		
		return "redirect:/board/boardList";
	}
	
	
}
