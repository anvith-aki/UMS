package com_entity;

public class StudentMarks {
	private int sid;
	private String sname;
	private int m1, m2, m3, total, percentage;

	public StudentMarks() {
	}

	public StudentMarks(int sid, String sname, int m1, int m2, int m3, int total, int percentage) {

		this.sid = sid;
		this.sname = sname;
		this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
		this.total = total;
		this.percentage = percentage;
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

	public int getM1() {
		return m1;
	}

	public void setM1(int m1) {
		this.m1 = m1;
	}

	public int getM2() {
		return m2;
	}

	public void setM2(int m2) {
		this.m2 = m2;
	}

	public int getM3() {
		return m3;
	}

	public void setM3(int m3) {
		this.m3 = m3;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public String toString() {
		return "Student Id: " + sid + ", Name: " + sname + ", M1: " + m1 + ", M2: " + m2 + ", M3: " + m3 + ", Total: "
				+ total + ", Percentage: " + percentage + " %\n";
	}

}
