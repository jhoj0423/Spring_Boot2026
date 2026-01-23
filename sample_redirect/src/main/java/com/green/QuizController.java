package com.green;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class QuizController {
	
	@GetMapping("/quiz")
	public String quizForm() {
		System.out.println("확인용1");
		return "quiz-view";
	}
	
	@GetMapping("/errquiz")
	public String errquizForm(
			@RequestParam("num") String num,
			RedirectAttributes re
			) {
		System.out.println("확인용2");
		if(num.equals("1234")) {
			System.out.println("확인용3");
			return "redirect:/main";
		}else {
			System.out.println("확인용4");
			re.addFlashAttribute("msg", "비밀번호가 틀렸습니다. 다시시도하세요");
			return "redirect:/quiz";
		}
			
	}
	
	@GetMapping("/main")
	public String main() {
		// main
		System.out.println("확인용5");
		return "Mainview";
	}
}
