package com.grabnotes.lib.service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.grabnotes.lib.biz.Book;
import com.grabnotes.lib.dao.BookDAO;

@Controller

public class LibraryController {

	@Autowired
	private BookDAO bookdao;

	@GetMapping("/book")
	@ResponseBody
	String getHelloWorld( ) {
		return "Welcome to library";
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/getBooks", method = RequestMethod.POST)
	@ResponseBody
	public List<Book> getAllBooks() throws SQLException {

		List<Book> list = new ArrayList<Book>(bookdao.getAllBooks());
		return list;
	}

	/**
	 * 
	 * @param booktitle
	 * @return
	 * @throws SQLException
	 */

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public Book getBook(@RequestParam("booktitle") String booktitle) throws SQLException {

		Book book = new Book(bookdao.searchBook(booktitle));
		return book;

	}

	/**
	 * 
	 * @param book
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/saveBook", method = RequestMethod.POST)
	@ResponseBody
	public List<Book> addBook(@RequestBody Book book) throws SQLException {
		return bookdao.insertBook(book.getBooktitle(), book.getAuthor(), book.getGenre(), book.getEdition());
	}

	/**
	 * 
	 * @param booktitle
	 * @param author
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/updateBook", method = RequestMethod.POST)
	@ResponseBody
	public Book updateBook(@RequestParam("booktitle") String booktitle, @RequestParam("author") String author)
			throws SQLException {

		Book book = new Book(bookdao.updateBook(booktitle, author));
		return book;
	}

	/**
	 * 
	 * @param booktitle
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/deleteBook", method = RequestMethod.POST)
	@ResponseBody
	public List<Book> deleteBook(@RequestParam("booktitle") String booktitle) throws SQLException {
		List<Book> listbook = new ArrayList<Book>(bookdao.deleteBook(booktitle));
		return listbook;

	}

}
