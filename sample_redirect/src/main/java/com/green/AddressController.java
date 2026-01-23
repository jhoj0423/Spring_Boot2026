package com.green;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddressController {
	
	private List<AddressDTO> addressList = new ArrayList<AddressDTO>();
	
	
	
	@GetMapping("/address")
	public String addresslist(Model model){
		model.addAttribute("list",addressList);
		return "address-list";
	}
	
	@PostMapping("/addr")
	public String addr(AddressDTO addressDto) {
		//ArrayList 삽입
		//삽입 메소드 add(value)
		addressList.add(addressDto);
		// 현재 url은 add-address인데 => 이사 address로 이동
		return "redirect:/address";
	}
	
	
}
