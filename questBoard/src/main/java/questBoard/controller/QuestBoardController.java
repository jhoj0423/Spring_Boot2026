package questBoard.controller;

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

import questBoard.dto.PageHandler;
import questBoard.dto.QuestBoardDTO;
import questBoard.service.QuestBoardService;

@Controller
public class QuestBoardController {
	
	@Autowired
	QuestBoardService questboardService;
	
	@GetMapping("/quest/list")
	public String questList_Form(Model model,
			//페이지 번호 1부터 시작이므로 초기값 1로 정의 
			@RequestParam(value="page",defaultValue="1") int page,
			// 한화면에 보여지는 페이지 수를 5로 초기화 한다.
			@RequestParam(value="pageSize",defaultValue="5") int pageSize
			) {
		System.out.println("QuestBoardController questList_Form(v_v) 메서드 확인용");
		int totalCnt=questboardService.getAllcount();
		
		PageHandler ph = new PageHandler(totalCnt, page, pageSize);
		questboardService.getPagelist(0,0);
		
		List<QuestBoardDTO> qlist = questboardService.getPagelist(ph.getStartRow(), pageSize);
		
		model.addAttribute("list",qlist);
		model.addAttribute("p",ph);
		
		return "questboardList";
	}
	
	@GetMapping("/quest/writer")
	public String questWrite_Form() {
		System.out.println("QuestBoardController questWrite_Form(v_v) 메서드 확인용");
		return "questboardWrite_Form";
	}
	
	@PostMapping("/quset/writerPro")
	public String questWritePro(QuestBoardDTO qdto,
			@RequestParam("file1") MultipartFile upload1,
			@RequestParam("file2") MultipartFile upload2
			) throws IllegalStateException, IOException {
		System.out.println("QuestBoardController questWritePro(v_v) 메서드 확인용");
		
		String savePath ="c:/upload/";
		
		File saveDir = new File(savePath);
		
		if(saveDir.exists()) {
			saveDir.mkdirs();
		}
		
		if(!upload1.isEmpty()) {
			String originalName1 = upload1.getOriginalFilename();
			String saveName1 = originalName1;
			
			File file1 = new File(savePath+saveName1);
			upload1.transferTo(file1);
			qdto.setUpload1(saveName1);
		}
		
		if(!upload2.isEmpty()) {
			String originalName2 = upload2.getOriginalFilename();
			String saveName2 = originalName2;
			
			File file2 = new File(savePath+saveName2);
			upload2.transferTo(file2);
			qdto.setUpload2(saveName2);
		}
		
		questboardService.insertQuestBoard(qdto);
		return "redirect:/quest/list";
	}
	
	@GetMapping("/quest/detail")
	public String getOneBoard(@RequestParam("num") int num,Model model) {
		System.out.println("QuestBoardController getOneBoard(v_v) 메서드 확인용");
		QuestBoardDTO oneList = questboardService.getOneQuestBoard(num);
		
		model.addAttribute("onelist",oneList);
		questboardService.readCountUpdate(oneList);
		return "questboardDetail";
	}
	
	@GetMapping("/quest/reply")
	public String reWriteForm(Model model,@RequestParam("num") int num,@RequestParam("ref") int ref) {
		System.out.println("QuestBoardController reWriteForm(v_v) 메서드 확인용");
		model.addAttribute("num",num);
		model.addAttribute("ref",ref);
		return "questboardReWrite_Form";
	}
	
	@PostMapping("/quest/reWritePro")
	public String reWitePro(QuestBoardDTO qdto) {
		System.out.println("QuestBoardController reWitePro(^_^) 메서드 확인용");
		questboardService.reWriteQuestBoard(qdto);
		return "redirect:/quest/list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
