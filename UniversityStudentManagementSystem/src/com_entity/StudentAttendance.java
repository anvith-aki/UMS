package com_entity;

public class StudentAttendance {
	private int sid;
	private String sname;
	private int dayspresent;
	private int totaldays;
	private float percentage;

	public StudentAttendance() {
	}

	public StudentAttendance(int sid, String sname, int dayspresent, int totaldays, float percentage) {
		this.sid = sid;
		this.sname = sname;
		this.dayspresent = dayspresent;
		this.totaldays = totaldays;
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

	public int getDayspresent() {
		return dayspresent;
	}

	public void setDayspresent(int dayspresent) {
		this.dayspresent = dayspresent;
	}

	public int getTotaldays() {
		return totaldays;
	}

	public void setTotaldays(int totaldays) {
		this.totaldays = totaldays;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	
	public String toString() {
		return "Student Id: " + sid + ", Name: " + sname + ", DaysPresent: " + dayspresent + ", TotalDays: "
				+ totaldays + ", Percentage: " + percentage + " %\n";
	}
}
