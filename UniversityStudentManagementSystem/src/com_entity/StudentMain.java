package com_entity;

public class StudentMain {
	private int sid;
	private String sname, spwd;

	public StudentMain() {
	}

	public StudentMain(int sid, String sname, String spwd) {
		this.sid = sid;
		this.sname = sname;
		this.spwd = spwd;
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

	public String getSpwd() {
		return spwd;
	}

	public void setSpwd(String spwd) {
		this.spwd = spwd;
	}

	public String toString() {
		return "Student Id: " + sid + ", Name: " + sname + ", Password: " + spwd + "\n";
	}

}
