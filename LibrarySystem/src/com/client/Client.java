package com.client;

import java.util.Scanner;

import com.dao.Validate;

public class Client {

	public String userid;
	public String password;
	Scanner sc=new Scanner(System.in);
	public void validateuser() {
		Validate v=new Validate();
		System.out.println("Welcome to LIBRARY...!");
		System.out.println("Please Enter User ID: ");
		userid=sc.next();
		System.out.println("Please Enter Password: ");
		password=sc.next();
		
		v.checkInput(userid, password);
	}
	
	public static void main(String[] args) {
		Client c=new Client();
		c.validateuser();
	}
}
