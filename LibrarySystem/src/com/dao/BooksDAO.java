package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Books;
import com.bean.User;
import com.connection.MyConnection;
import com.service.LibraryService;

public class BooksDAO implements BooksInterface {
	private Connection con;
	private PreparedStatement stat;

	public BooksDAO() {
		con = MyConnection.getConnection();
	}

	@Override
	public void addBook(Books b) {
		try {
			stat = con.prepareStatement("insert into books values(?,?,?,?)");
			stat.setInt(1, b.getBookid());
			stat.setString(2, b.getBookname());
			stat.setString(3, b.getAuthor());
			stat.setInt(4, b.getQuantity());
			int x = stat.executeUpdate();
			if (x > 0) {
				System.out.println("Book Successfully Added.");
			} else {
				System.out.println("Error in Adding Book.");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void updateBook(Books b) {
		try {
			stat = con.prepareStatement("update books set bookname=?, author=?, quantity=? where bookid=? ");
			stat.setString(1, b.getBookname());
			stat.setString(2, b.getAuthor());
			stat.setInt(3, b.getQuantity());
			stat.setInt(4, b.getBookid());
			int x = stat.executeUpdate();
			if (x > 0) {
				System.out.println("Data Updated Successfully.");
			} else {
				System.out.println("Error in Updating Data.");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void removeBook(int bookid) {
		try {
			stat = con.prepareStatement("delete from books where bookid=?");
			stat.setInt(1, bookid);
			int x = stat.executeUpdate();
			if (x > 0) {
				System.out.println("Data Removed Successfully.");
			} else {
				System.out.println("Error in Deleting Data.");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void issueBook(int bookid, User us) {
		int bid = 0;
		String bname = null;
		int quantity = 0;
		LibraryService ls = new LibraryService();
		try {
			stat = con.prepareStatement("select * from books where bookid=? ", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			stat.setInt(1, bookid);
			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				bid = rs.getInt(1);
				bname = rs.getString(2);
				quantity = rs.getInt(4);
			}
			if (quantity != 0) {
				int updatedquantity = rs.getInt(4) - 1;
				rs.updateInt("quantity", updatedquantity);
				rs.updateRow();

			} else {
				System.out.println("Cannot Issue Book Due to Shortage.");
			}

			PreparedStatement stat1 = con.prepareStatement("insert into issuedbooks values(?,?,?,now(),?)");
			stat1.setInt(1, bid);
			stat1.setString(2, bname);
			stat1.setString(3, us.getUsername());
			stat1.setString(4, ls.duedate());
			int x = stat1.executeUpdate();
			if (x > 0) {
				System.out.println("Book Issued.");
			} else {
				System.out.println("Error in Issuing Book.");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public List<Books> showDetails() {
		List<Books> booklist = new ArrayList<Books>();

		try {
			stat = con.prepareStatement("select * from issuedbooks");
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Books b = new Books();
				b.setBookid(rs.getInt(1));
				b.setBookname(rs.getString(2));
				b.setDuedate(rs.getString(5).toString());

				booklist.add(b);
			}
			for (Books bk : booklist) {
				System.out.println("Book ID: " + bk.getBookid());
				System.out.println("Book Name: " + bk.getBookname());
				System.out.println("Due Date: " + bk.getDuedate());
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return booklist;
	}

	@Override
	public List<Books> selectall() {
		List<Books> booklist = new ArrayList<Books>();

		try {
			stat = con.prepareStatement("select * from books");
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				Books b = new Books();
				b.setBookid(rs.getInt(1));
				b.setBookname(rs.getString(2));
				b.setAuthor(rs.getString(3));
				b.setQuantity(rs.getInt(4));

				booklist.add(b);
			}
			for (Books bk : booklist) {
				System.out.println("Book ID: " + bk.getBookid());
				System.out.println("Book Name: " + bk.getBookname());
				System.out.println("Author Name: " + bk.getAuthor());
				System.out.println("Available Quantity of boook in LIbrary: " + bk.getQuantity());
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return booklist;
	}

}
