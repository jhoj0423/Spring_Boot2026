package questBoard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import questBoard.service.QuestBoardService;

@Controller
public class QuestBoardController {
	
	@Autowired
	QuestBoardService questboardService;
	
	@GetMapping("/quest/list")
	public String questList_Form() {
		System.out.println("QuestBoardController questList_Form(v_v) 메서드 확인용");
		return "/questBoard/questboardList";
	}
}
