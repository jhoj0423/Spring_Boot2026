package com.green;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.green.carproduct.CarProductDTO;
import com.green.carproduct.CarProductService;


@Controller
public class homeController {
	//오로지 HOME만 컨트롤
	// http://localhost:8090, 또는  http://localhost:8090
	
	@Autowired
	CarProductService carproductservice;
	
	
	@GetMapping({"","/"})
	public String home(Model model) {
		// syso => log찍는용 -> 반드시 필요
		System.out.println("HOMEController 확인");
		
		List<CarProductDTO> carlist = carproductservice.getAllCarProduct();
		
		model.addAttribute("carlist",carlist);
		
		return "home";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
