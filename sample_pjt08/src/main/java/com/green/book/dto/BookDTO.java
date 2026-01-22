package com.green.book.dto;

import java.util.Date;

public class BookDTO {
	private String BookName;
	private String author;
	private String isbn;
	private String renterName;
	private Date renterStart;
	private Date renterEnd;
	
	public String getBookName() {
		return BookName;
	}
	public void setBookName(String bookName) {
		BookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getRenterName() {
		return renterName;
	}
	public void setRenterName(String renterName) {
		this.renterName = renterName;
	}
	public Date getRenterStart() {
		return renterStart;
	}
	public void setRenterStart(Date renterStart) {
		this.renterStart = renterStart;
	}
	public Date getRenterEnd() {
		return renterEnd;
	}
	public void setRenterEnd(Date renterEnd) {
		this.renterEnd = renterEnd;
	}
}
