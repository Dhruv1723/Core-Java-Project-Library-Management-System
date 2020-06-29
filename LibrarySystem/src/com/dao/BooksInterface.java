package com.dao;

import java.util.List;

import com.bean.Books;
import com.bean.User;

public interface BooksInterface {

	public void addBook(Books b);
	public void updateBook(Books b);
	public void removeBook(int bookid);
	public void issueBook(int bookid, User us);
	public List<Books> showDetails();
	public List<Books> selectall();
}
