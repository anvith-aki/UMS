package com_entity;

public class StudentLib {
	private int sid;
	private String sname;
	private int bookstaken, booksreturned, bookspending;

	public StudentLib() {
	}

	public StudentLib(int sid, String sname, int bookstaken, int booksreturned, int bookspending) {
		this.sid = sid;
		this.sname = sname;
		this.bookstaken = bookstaken;
		this.booksreturned = booksreturned;
		this.bookspending = bookspending;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public int getBookstaken() {
		return bookstaken;
	}

	public void setBookstaken(int bookstaken) {
		this.bookstaken = bookstaken;
	}

	public int getBooksreturned() {
		return booksreturned;
	}

	public void setBooksreturned(int booksreturned) {
		this.booksreturned = booksreturned;
	}

	public int getBookspending() {
		return bookspending;
	}

	public void setBookspending(int bookspending) {
		this.bookspending = bookspending;
	}

	public String toString() {
		return "Student Id: " + sid + ", Name: " + sname + ", Books-Taken: " + bookstaken + ", Books-Returned: "
				+ booksreturned + ", Books-Pending: " + bookspending + "\n";
	}
}
