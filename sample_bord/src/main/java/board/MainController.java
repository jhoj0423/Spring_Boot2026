package board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping({"","/"})
	public String boardMain() {
		System.out.println("boardMain() 메소드 확인용");
		return "Main";
	}
}
