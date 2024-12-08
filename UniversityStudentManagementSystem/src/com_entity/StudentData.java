package com_entity;

public class StudentData {
	private int sid;
	private String sname;
	private String dob;
	private String email;
	private String loc;

	public StudentData() {
	}

	public StudentData(int sid, String sname, String dob, String email, String loc) {
		this.sid = sid;
		this.sname = sname;
		this.dob = dob;
		this.email = email;
		this.loc = loc;
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String toString() {
		return "Student Id: " + sid + ", Name: " + sname + ", D.O.B: " + dob + ", E-mail: " + email + ", Location: " + loc
				+ "\n";
	}
}
