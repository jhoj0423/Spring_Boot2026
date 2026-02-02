package com.green;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {
	//오로지 HOME만 컨트롤
	// http://localhost:8090, 또는  http://localhost:8090
	@GetMapping({"","/"})
	public String home() {
		// syso => log찍는용 -> 반드시 필요
		System.out.println("HOMEController 확인");
		return "home";
	}
}
