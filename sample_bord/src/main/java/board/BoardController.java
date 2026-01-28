package board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	// 게시판 리스트 출력
	@GetMapping("/board/boardList")
	public String boardList(Model model) {
		System.out.println("boardList() 확인용입니다");
		List<BoardDTO> boardList = boardService.selectBoardAll();
		System.out.println(boardList+"boardList내용확인용");
		model.addAttribute("list",boardList);
		return "board/boardList";
	}
	// 게시판 내용 페이지 출력
	@GetMapping("/board/boarderWrite")
	public String boarderWrite() {
		System.out.println("boarderWrite() 확인용입니다");
		return "board/boarderWrite";
	}
	//게시판 내용 페이지 출력
	@GetMapping("/board/boardWrite_confrim")
	public String boardWrite_confrim(BoardDTO bdto,Model model) {
		System.out.println("boardWrite_confrim() 확인용입니다");
		int result = boardService.writeConfirm(bdto);
		System.out.println("boardWrite_confrim() reulst값 = "+result);
		
		return "redirect:/board/boardList";
	}
	//----------------------------- 2026-01-28 컨트롤러 수정 ---------------
	
	//게시판 작성 페이지 출력
	@GetMapping("/board/boardResult")
	public String boardResult(Model model ,BoardDTO bdto) {
		System.out.println("boardResult() 확인용입니다"+bdto.getId());
		BoardDTO oneBoardResult = boardService.oneSelect(bdto.getId());
		model.addAttribute("onelist",oneBoardResult);
		
		return "board/boardResult"; 
	}
	
	
	//게시판 수정 페이지 출력
	@GetMapping("/board/boardReWrite")
	public String boardReWrite(BoardDTO bdto, Model model) {
		System.out.println("boardReWrite() 확인용입니다");
		BoardDTO oneRe = boardService.oneSelect(bdto.getId());
		model.addAttribute("bwt",oneRe);
		
		return "board/boardReWrite";
	}
	
	@PostMapping("/board/boardReWrite1")
	public String RewriteSubmit(BoardDTO bdto, RedirectAttributes ra) {
		System.out.println("RewriteSubmit() 확인용입니다");
		boolean result = boardService.Rewriteboard(bdto);
		System.out.println(result + "RewriteSubmitdptj result확인");
		System.out.println("RewriteSubmit() 에서 아이디 확인"+bdto.getId());
		
		if(result) {
			//ra.addFlashAttribute("msg","게시글이 수정되었습니다.");
			return "redireat:/board/boardResult?id="+bdto.getId();
		}else {
			//ra.addFlashAttribute("msg","게시글 수정에 실패 하였습니다.");
			return "redireat:/board/boardReWrite?id="+bdto.getId();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
