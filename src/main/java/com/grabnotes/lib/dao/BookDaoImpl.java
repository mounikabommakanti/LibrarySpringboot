package com.grabnotes.lib.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.grabnotes.lib.biz.Book;

@Component
public class BookDaoImpl implements BookDAO {
	private static Logger log = Logger.getLogger(BookDaoImpl.class.getName());
	private static final String INSERT_TABLE_SQL = "INSERT INTO book" + "(booktitle,author,edition,genre) VALUES"
			+ "(?,?,?,?)";
	private static final String UPDATE_TABLE_SQL = "UPDATE Book SET booktitle=? where author=? ";
	private static final String SELECT_TABLE_SQL = "SELECT *FROM Book";
	private static final String SEARCH_TABLE_SQL = "SELECT *FROM Book where booktitle=?";
	private static final String DELETE_TABLE_SQL = "Delete FROM Book where booktitle=? ";

	@Override
	public List<Book> insertBook(String booktitle, String author, String genre, String edition) {
		Connection con = null;
		PreparedStatement ps1 = null, ps2 = null;
		ResultSet rs = null;
		List<Book> listbook = null;
		try {
			con = MySqlConnection.getConnection();
			ps1 = con.prepareStatement(INSERT_TABLE_SQL);
			ps1.setString(1, booktitle);
			ps1.setString(2, author);
			ps1.setString(3, edition);
			ps1.setString(4, genre);
			ps1.executeUpdate();
			ps2 = con.prepareStatement(SELECT_TABLE_SQL);
			rs = ps2.executeQuery();
			listbook = new ArrayList<Book>();
			while (rs.next()) {
				Book b = new Book(rs.getString("booktitle"), rs.getString("author"), rs.getString("genre"),
						rs.getString("edition"));
				listbook.add(b);
			}
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (ps1 != null || ps2 != null) {
				try {
					ps1.close();
					ps2.close();
				} catch (SQLException e) {
					log.error(e);
				}
			}
		}
		return listbook;
	}

	@Override
	public List<Book> getAllBooks() {
		Connection con = null;
		PreparedStatement ps = null;
		List<Book> listbook = null;
		ResultSet rs = null;
		try {
			con = MySqlConnection.getConnection();
			ps = con.prepareStatement(SELECT_TABLE_SQL);
			rs = ps.executeQuery();
			listbook = new ArrayList<Book>();
			while (rs.next()) {
				Book b = new Book(rs.getString("booktitle"), rs.getString("author"), rs.getString("genre"),
						rs.getString("edition"));
				listbook.add(b);
			}
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		return listbook;
	}

	public Book searchBook(String booktitle) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Book book = null;
		try {
			con = MySqlConnection.getConnection();
			ps = con.prepareStatement(SEARCH_TABLE_SQL);
			ps.setString(1, booktitle);
			log.info(booktitle);
			rs = ps.executeQuery();
			while (rs.next()) {
				book = new Book(rs.getString("booktitle"), rs.getString("author"), rs.getString("genre"),
						rs.getString("edition"));
			}
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					log.error(e);
				}
			}
		}

		return book;
	}

	public Book updateBook(String booktitle, String author) {
		Connection con = null;
		PreparedStatement ps1 = null, ps2 = null;
		Book book = null;
		ResultSet rs = null;
		try {
			con = MySqlConnection.getConnection();
			ps1 = con.prepareStatement(UPDATE_TABLE_SQL);
			ps1.setString(1, booktitle);
			ps1.setString(2, author);
			ps1.executeUpdate();
			ps2 = con.prepareStatement(SEARCH_TABLE_SQL);
			ps2.setString(1, booktitle);
			rs = ps2.executeQuery();
			while (rs.next()) {
				book = new Book(rs.getString("booktitle"), rs.getString("author"), rs.getString("genre"),
						rs.getString("edition"));
			}
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (ps1 != null || ps2 != null) {
				try {
					ps1.close();
					ps2.close();
				} catch (SQLException e) {
					log.error(e);
				}
			}
		}
		return book;
	}

	public List<Book> deleteBook(String booktitle) {
		Connection con = null;
		PreparedStatement ps1 = null, ps2 = null;
		ResultSet rs = null;
		List<Book> listbook = null;
		try {
			con = MySqlConnection.getConnection();
			ps1 = con.prepareStatement(DELETE_TABLE_SQL);
			ps1.setString(1, booktitle);
			ps1.executeUpdate();
			ps2 = con.prepareStatement(SELECT_TABLE_SQL);
			rs = ps2.executeQuery();
			listbook = new ArrayList<Book>();
			while (rs.next()) {
				Book b = new Book(rs.getString("booktitle"), rs.getString("author"), rs.getString("genre"),
						rs.getString("edition"));
				listbook.add(b);
			}
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (ps1 != null || ps2 != null) {
				try {
					ps1.close();
					ps2.close();
				} catch (SQLException e) {
					log.error(e);
				}
			}
		}
		return listbook;
	}

}
