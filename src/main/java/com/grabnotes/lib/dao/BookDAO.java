package com.grabnotes.lib.dao;

import java.util.List;

import com.grabnotes.lib.biz.Book;

public interface BookDAO {
	public List<Book> insertBook(String booktitle, String author, String genre, String edition);

	public List<Book> getAllBooks();

	public Book searchBook(String booktitle);

	public Book updateBook(String booktitle, String author);

	public List<Book> deleteBook(String booktitle);// correction isDeleteBook

}
