package com_entity;

public class FacultyMain {
	private int fid;
	private String fname, fpwd;

	public FacultyMain() {
	}

	public FacultyMain(int fid, String fname, String fpwd) {
		this.fid = fid;
		this.fname = fname;
		this.fpwd = fpwd;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFpwd() {
		return fpwd;
	}

	public void setFpwd(String fpwd) {
		this.fpwd = fpwd;
	}

	public String toString() {
		return "Faculty Id: " + fid + ", Name: " + fname + ", Password: " + fpwd + "\n";
	}

}