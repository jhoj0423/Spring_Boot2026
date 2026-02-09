package reviewboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {
	
	@GetMapping("/")
	public String home_from() {
		System.out.println("home_from() 메서드 확인용");
		return "home";
	}
}
