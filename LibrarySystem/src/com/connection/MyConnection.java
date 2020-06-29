package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

	private static Connection con;
	private MyConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/trg", "root", "Dhruwinkle");
			System.out.println("Connected");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Creating static method so we can access this method without creating object of class
	public static Connection getConnection() { 
		@SuppressWarnings("unused")
		MyConnection c1=new MyConnection();
		return con;
	}
}
