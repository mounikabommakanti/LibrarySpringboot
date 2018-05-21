package com.grabnotes.lib.biz;

public class Book {
	
	String booktitle;
	String author;
	String genre;
	String edition;
	String message;
	
	public Book(String booktitle, String author, String genre, String edition) {
		super();
		this.booktitle = booktitle;
		this.author = author;
		this.genre = genre;
		this.edition = edition;
	}
	public Book(String message)
	{
		this.message=message;
		
	}
	public Book(Book book) {
		this.booktitle=book.getBooktitle();
		this.author = book.getAuthor();
		this.genre = book.getGenre();
		this.edition = book.getEdition();
	}
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	
	

}
