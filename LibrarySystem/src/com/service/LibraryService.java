package com.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.bean.Books;
import com.bean.User;
import com.dao.BooksDAO;

public class LibraryService {
	BooksDAO bd = new BooksDAO();

	public void newBook(Books b) {
		if (b.getBookname() == null || b.getQuantity() < 0) {
			System.out.println("Please Enter Valid Details.");
		} else {
			bd.addBook(b);
		}
	}

	public void update(Books b) {
		if (b.getQuantity() < 0) {
			System.out.println("Quantity Cannot be 0 or Negative.");
		} else {
			bd.updateBook(b);
		}
	}

	public void delete(int bookid) {
		if (bookid <= 0) {
			System.out.println("Please Enter Valid Book ID.");
		} else {
			bd.removeBook(bookid);
		}
	}

	public void issue(int bookid, User us) {
		if (bookid <= 0 || us.getUsername().length() < 3) {
			System.out.println("Please Enter Valid Details.");
		} else {
			bd.issueBook(bookid, us);
		}
	}

	public String duedate() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime then = now.plusDays(10);
		String duedate=then.format(format).toString();
		return duedate;

	}

}
