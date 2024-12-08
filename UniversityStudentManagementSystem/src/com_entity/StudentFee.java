package com_entity;

public class StudentFee {
	private int sid;
	private String sname;
	private int totalfee;
	private int feepaid;
	private int feedue;

	public StudentFee() {
	}

	public StudentFee(int sid, String sname, int totalfee, int feepaid, int feedue) {
		this.sid = sid;
		this.sname = sname;
		this.totalfee = totalfee;
		this.feepaid = feepaid;
		this.feedue = feedue;
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

	public int getTotalfee() {
		return totalfee;
	}

	public void setTotalfee(int totalfee) {
		this.totalfee = totalfee;
	}

	public int getFeepaid() {
		return feepaid;
	}

	public void setFeepaid(int feepaid) {
		this.feepaid = feepaid;
	}

	public int getFeedue() {
		return feedue;
	}

	public void setFeedue(int feedue) {
		this.feedue = feedue;
	}

	public String toString() {
		return "Student Id: " + sid + ", Name: " + sname + ", TotalFee: " + totalfee + ", FeePaid: " + feepaid
				+ ", FeeDue: " + feedue + "\n";
	}

}
