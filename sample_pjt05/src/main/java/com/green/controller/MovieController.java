package com.green.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieController {
	@GetMapping("/movie/reserve")
	public String reserve() {
		System.out.println("reserve()");
		return "reserve";
	}
	
	@PostMapping("/movie/reserveConfirm")
	public String reserveConfirm(
			@RequestParam("movieTitle") String movieTitle,
			@RequestParam("reserveDate") String reserveDate,
			@RequestParam("reserveTime") String reserveTime,
			@RequestParam("people") String people,
			@RequestParam("reserverName") String reserverName,
			Model model
			) {
		
		model.addAttribute("movieTitle",movieTitle);
		model.addAttribute("reserveDate",reserveDate);
		model.addAttribute("reserveTime",reserveTime);
		model.addAttribute("people",people);
		model.addAttribute("reserverName",reserverName);
		
		
		
		
		System.out.println("reserveConfirm()");
		return "reserveConfirm";
	}
}
