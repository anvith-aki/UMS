package com_persistance;

import java.sql.*;
import java.util.*;
import com_entity.*;

//1.viewData
//2.viewAttendance
//3.viewFee
//4.viewMarks
//5.viewLib
//6.updateStudentPassword

public class Student_Implementations implements Student_Declarations {
	
//	For displaying the student data by student
//	================================================================================================
	public List<StudentData> viewData(int sid) throws ClassNotFoundException, SQLException, InputMismatchException {
		ArrayList<StudentData> slist = new ArrayList<StudentData>();
		StudentData s = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ums", "root", "root");
		PreparedStatement ps = con.prepareStatement("select * from studentdata where sid=?");
		ps.setInt(1, sid);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			s = new StudentData();
			s.setSid(rs.getInt(1));
			s.setSname(rs.getString(2));
			s.setDob(rs.getString(3));
			s.setEmail(rs.getString(4));
			s.setLoc(rs.getString(5));
			slist.add(s);
		}
		con.close();
		return slist;
	}

// For displaying the student attendance by student
// ================================================================================================
	public List<StudentAttendance> viewAttendance(int sid)
			throws ClassNotFoundException, SQLException, InputMismatchException {
		ArrayList<StudentAttendance> slist = new ArrayList<StudentAttendance>();
		StudentAttendance s = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ums", "root", "root");
		PreparedStatement ps = con.prepareStatement("select * from studentattendance where sid=?");
		ps.setInt(1, sid);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			s = new StudentAttendance();
			s.setSid(rs.getInt(1));
			s.setSname(rs.getString(2));
			s.setDayspresent(rs.getInt(3));
			s.setTotaldays(rs.getInt(4));
			s.setPercentage(rs.getFloat(5));
			slist.add(s);
		}
		con.close();
		return slist;
	}

// For displaying the student fee details by student
// ================================================================================================
	public List<StudentFee> viewFee(int sid) throws ClassNotFoundException, SQLException, InputMismatchException {
		ArrayList<StudentFee> slist = new ArrayList<StudentFee>();
		StudentFee s = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ums", "root", "root");
		PreparedStatement ps = con.prepareStatement("select * from studentfee where sid=?");
		ps.setInt(1, sid);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			s = new StudentFee();
			s.setSid(rs.getInt(1));
			s.setSname(rs.getString(2));
			s.setTotalfee(rs.getInt(3));
			s.setFeepaid(rs.getInt(4));
			s.setFeedue(rs.getInt(5));
			slist.add(s);
		}
		con.close();
		return slist;
	}
	
// For displaying the student marks by student
//	================================================================================================
	public List<StudentMarks> viewMarks(int sid) throws ClassNotFoundException, SQLException, InputMismatchException {
		ArrayList<StudentMarks> slist = new ArrayList<StudentMarks>();
		StudentMarks s = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ums", "root", "root");
		PreparedStatement ps = con.prepareStatement("select * from studentmarks where sid=?");
		ps.setInt(1, sid);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			s = new StudentMarks();
			s.setSid(rs.getInt(1));
			s.setSname(rs.getString(2));
			s.setM1(rs.getInt(3));
			s.setM2(rs.getInt(4));
			s.setM3(rs.getInt(5));
			s.setTotal(rs.getInt(6));
			s.setPercentage(rs.getInt(7));
			slist.add(s);
		}
		con.close();
		return slist;
	}
	
// For displaying the student library details by student
//	================================================================================================
	public List<StudentLib> viewLib(int sid) throws ClassNotFoundException, SQLException, InputMismatchException {
		ArrayList<StudentLib> slist = new ArrayList<StudentLib>();
		StudentLib s = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ums", "root", "root");
		PreparedStatement ps = con.prepareStatement("select * from studentlib where sid=?");
		ps.setInt(1, sid);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			s = new StudentLib();
			s.setSid(rs.getInt(1));
			s.setSname(rs.getString(2));
			s.setBookstaken(rs.getInt(3));
			s.setBooksreturned(rs.getInt(4));
			s.setBookspending(rs.getInt(5));
			slist.add(s);
		}
		con.close();
		return slist;
	}

// For updating of his/her own password
//	================================================================================================
	@Override
	public void updateStudentPassword(int sid, String newpwd)
			throws ClassNotFoundException, SQLException, InputMismatchException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ums", "root", "root");
		PreparedStatement ps = con.prepareStatement("update studentmain set spwd=? where sid=?");
		ps.setString(1, newpwd);
		ps.setInt(2, sid);
		int i = ps.executeUpdate();
		if (i == 0) {
			System.out.println("Failed to update password\n");
		} else {
			System.out.println("Password Updated Successfully\n");
		}
		con.close();
	}
}