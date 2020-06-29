package com.client;

import java.util.Scanner;

import com.bean.Books;
import com.bean.User;
import com.dao.BooksDAO;
import com.service.LibraryService;

public class Librarian {

	int choice;
	private Scanner sc=new Scanner(System.in);
	LibraryService ls=new LibraryService();
	
	public void choose() {
		System.out.println("Welcome.....Mr. Librarian");
		System.out.println();
		System.out.println("Select 1: To Add Book.");
		System.out.println("Select 2: To Edit Book Details.");
		System.out.println("Select 3: To Remove Book.");
		System.out.println("Select 4: To Issue Book.");
		System.out.println("Select 5: To See List of Books that are issued from Library.");
		System.out.println("Select 6: To See List of All Books.");
		System.out.println();
		System.out.println("Please Choose from What You Want to do: ");
		choice=sc.nextInt();
		
		if(choice == 1) {
			Books book=new Books();
			System.out.println("Please Enter Book ID: ");
			book.setBookid(sc.nextInt());
			System.out.println("Please Enter Book Name: ");
			book.setBookname(sc.next());
			System.out.println("Please Enter Author Name: ");
			book.setAuthor(sc.next());
			System.out.println("Please Enter Number of Copies: ");
			book.setQuantity(sc.nextInt());
			ls.newBook(book);
		}
		
		else if(choice == 2) {
			Books b=new Books();
			System.out.println("Please Enter Book ID to update Data: ");
			b.setBookid(sc.nextInt());
			System.out.println("Please Enter New Book Name: ");
			b.setBookname(sc.next());
			System.out.println("Please Enter New Book's Author Name: ");
			b.setAuthor(sc.next());
			System.out.println("Please Enter Updated Quantity: ");
			b.setQuantity(sc.nextInt());
			ls.update(b);
		}
		
		else if(choice == 3) {
			//Books b=new Books();
			System.out.println("Please Enter Book ID to Delete its All Record: ");
			int bookid=sc.nextInt();
			ls.delete(bookid);
		}
		
		else if(choice == 4) {
			User us=new User();
			System.out.println("Please Enter Book ID to Issue: ");
			int bookid=sc.nextInt();
			System.out.println("Please Enter User ID to allocate book: ");
			us.setUserid(sc.next());
			System.out.println("Please Enter User Name: ");
			us.setUsername(sc.next());
			ls.issue(bookid, us);
		}
		
		else if(choice == 5) {
			BooksDAO bd=new BooksDAO();
			System.out.println("Issued Book List with Due Dates: ");
			bd.showDetails();
		}
		
		else if(choice == 6) {
			BooksDAO bd=new BooksDAO();
			System.out.println("List Of Available Books In Library: ");
			bd.selectall();
		}
		else {
			System.out.println("Please Enter a Valid Choice.");
		}
		
		
	}
}
