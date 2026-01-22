package com.green.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class bookController {
	@GetMapping("/book/bookList")
	public String bookListform() {
		System.out.println("bookListform()");
		return "bookList";
	}
}
