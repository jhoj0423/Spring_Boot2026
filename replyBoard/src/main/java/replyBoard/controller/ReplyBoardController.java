package replyBoard.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import replyBoard.dto.ReplyBoardDTO;
import replyBoard.service.ReplyBoardService;

@Controller
public class ReplyBoardController {
	
	// 반드시 ReplyBoardService() 인터페이스를 의존객체로 삽입함을 주의하자!
	@Autowired
	ReplyBoardService replyBoardService;
	
	// 게시글 이동하는 컨트롤러
	@GetMapping("/board/list")
	public String boardList(Model model) {
		System.out.println("ReplyBoardController boardList(-@-) 메서드 확인");
		List<ReplyBoardDTO> replyList = replyBoardService.getAllReplyBoard();
		
		model.addAttribute("rlist",replyList);
		
		return "/replyBoard/replyboardList";
	}
	
	@GetMapping("/board/writer")
	public String boardWriterForm() {
		System.out.println("ReplyBoardController boardWriter(-@-) 메서드 확인");
		
		return "/replyBoard/repliboardWrite_Form";
	}
	
//	@PostMapping("/board/writerPro")
//	public String boardWriterPro(ReplyBoardDTO rdto) {
//		System.out.println("ReplyBoardController boardWriterPro(-@-) 메서드 확인");
//		replyBoardService.insertReplyBoard(rdto);
//		return "redirect:/board/list";
//	}
	
	// 파일 업로드는 POSTMapping() 만 가능
	@PostMapping("/board/writerPro")
	public String boardWriterPro(ReplyBoardDTO rdto, 
			@RequestParam("file1") MultipartFile upload1,
			@RequestParam("file2") MultipartFile upload2)
					throws IllegalStateException, IOException {
		System.out.println("ReplyBoardController boardWriterPro(-@-) 메서드 확인");
		//1. 파일을 저장할 실제 하드디스크 위치를 지정한다
		// WebConfig에서 설정한 'file:///c:/upload/' 이경로와
		// 반드시 일치 하여야 한다.
		String savePath = "c:/upload/";
		
		//2. 안전장치 만약 c:/upload/폴더가 존재하지 않으면 
		// 프로그램을 통해 자동으로 생성되도록 작성한다.
		File saveDir = new File(savePath);
		if(!saveDir.exists()) {
			// mkdirs() 메소드는 폴더가 없어도 한꺼번에 만들어주는 메서드
			saveDir.mkdirs();
		}
		
		// 3. 첫번째 이미지 업로드 처리
		// 예외처리 이미지가 비어있으면 추가되면 안된다
		if(!upload1.isEmpty()) {// 사용자가 실제 파일을 선택해서 보냈는지 확인
			// 사용자가 올린 원래 파일명(예: 20.jpg) 을 가지고 온다
			String originalName1 = upload1.getOriginalFilename();
			String saveName1 = originalName1;
			
			// c://upload/20.jpg
			File file1 =new File(savePath + saveName1);
			
			// transferTo() : 이 명령어가 실행되는 순간 서버 메모리에서
			//					존재하던 파일이 실제 하드디스크 c:/upload로 복사된다.
			upload1.transferTo(file1);
			// DB에 저장할 파일명 DTO세팅
			rdto.setUpload1(saveName1);
		}
		
		
		if(!upload2.isEmpty()) {// 사용자가 실제 파일을 선택해서 보냈는지 확인
			// 사용자가 올린 원래 파일명(예: 20.jpg) 을 가지고 온다
			String originalName2 = upload2.getOriginalFilename();
			String saveName2 = originalName2;
			
			// c://upload/20.jpg
			File file2 =new File(savePath + saveName2);
			
			// transferTo() : 이 명령어가 실행되는 순간 서버 메모리에서
			//					존재하던 파일이 실제 하드디스크 c:/upload로 복사된다.
			upload2.transferTo(file2);
			// DB에 저장할 파일명 DTO세팅
			rdto.setUpload2(saveName2);
		}
		
		
		replyBoardService.insertReplyBoard(rdto);
		return "redirect:/board/list";
	}
	
	
	@GetMapping("/board/detail")
	public String getOneBoard(
			@RequestParam("num") int num,
			Model model
			) {
		System.out.println("ReplyBoardController getOneBoard(-@-) 메서드 확인");
		ReplyBoardDTO oneList = replyBoardService.getOneBoard(num);
		model.addAttribute("onelist",oneList);
		return "/replyBoard/replyboardDetail";
		
	}
	
	// 답글 작성하는 폼으로 이동하는 컨트롤러
	@GetMapping("/board/reply")
	public String reWriteForm(Model model,
			@RequestParam("num") int num,
			@RequestParam("ref") int ref,
			@RequestParam("re_step") int re_step,
			@RequestParam("re_level") int re_level
			) {
		System.out.println("ReplyBoardController reWriteForm(-@-) 메서드 확인");
		model.addAttribute("num",num);
		model.addAttribute("ref",ref);
		model.addAttribute("re_step",re_step);
		model.addAttribute("re_level",re_level);
		
		return "/replyBoard/replyboardReWrite_Form";
	}
	
	@PostMapping("/board/reWritePro")
	public String reWitePro(ReplyBoardDTO rdto) {
		System.out.println("ReplyBoardController reWitePro(-@-) 메서드 확인");
		replyBoardService.replyProcess(rdto);
		return "redirect:/board/list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
