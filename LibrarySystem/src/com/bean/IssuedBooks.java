package com.bean;

import java.sql.Date;

public class IssuedBooks {

	private int issuedbookid;
	private String issuedbookname;
	private String userName;
	private Date issueDate;
	private Date dueDate;
	public int getIssuedbookid() {
		return issuedbookid;
	}
	public void setIssuedbookid(int issuedbookid) {
		this.issuedbookid = issuedbookid;
	}
	public String getIssuedbookname() {
		return issuedbookname;
	}
	public void setIssuedbookname(String issuedbookname) {
		this.issuedbookname = issuedbookname;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
}
