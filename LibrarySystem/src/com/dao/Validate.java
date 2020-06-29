package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.client.Librarian;
import com.connection.MyConnection;

public class Validate {

	private Connection con;
	private PreparedStatement stat;
	Librarian lib=new Librarian();
	public Validate() {
		con=MyConnection.getConnection();
	}
	public void checkInput(String userid, String password) {
		try {
			stat=con.prepareStatement("select * from login where loginid=? and password=?");
			stat.setString(1, userid);
			stat.setString(2, password);
			ResultSet rs=stat.executeQuery();
			if(rs.next()) {
				lib.choose();
			}else {
				System.out.println("PLease Enter Valid ID or Password.");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
