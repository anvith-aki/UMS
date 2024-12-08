package com_persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import com_entity.FacultyMain;
import com_entity.StudentMain;

public class Admin_Implementations implements Admin_Declarations
{
	
// Create Student
// =================================================================================================
	@Override
	public List<StudentMain> createStudent(String sname) throws ClassNotFoundException, SQLException, InputMismatchException {
		ArrayList<StudentMain> list=new ArrayList<StudentMain>();
		StudentMain t=null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ums","root","root");
			
			int i1,i2,i3,i4,i5;
	    	PreparedStatement ps1=con.prepareStatement(" insert into studentdata(sname) values(?) ");
			ps1.setString(1,sname);
			i1=ps1.executeUpdate();
			sleep(250);
		    PreparedStatement ps2=con.prepareStatement(" insert into studentattendance(sname) values(?) ");
		    ps2.setString(1,sname);
			i2=ps2.executeUpdate();
			sleep(250);
		    PreparedStatement ps3=con.prepareStatement(" insert into studentfee(sname) values(?) ");
		    ps3.setString(1,sname);
			i3=ps3.executeUpdate();
			sleep(250);
		    PreparedStatement ps4=con.prepareStatement(" insert into studentlib(sname) values(?) ");
		    ps4.setString(1,sname);
			i4=ps4.executeUpdate();
			sleep(250);
		    PreparedStatement ps5=con.prepareStatement(" insert into studentmarks(sname) values(?) ");
		    ps5.setString(1,sname);
			i5=ps5.executeUpdate();	
			sleep(250);
			
			if( (i1+i2+i3+i4+i5)==5)
			System.out.println("\nCreating ...\n");
			sleep(1000);
			
			PreparedStatement ps=con.prepareStatement("insert into studentMain(sname) values(?)");
			ps.setString(1,sname);
			int i=ps.executeUpdate();
		
		if(i==0){		
			System.out.println("Creation Failed\n");
		}else
		{	
			System.out.println("Created Successfully\n");
			System.out.println("Generating Credentials please wait...\n");
			sleep(750);
			String query="select * from studentmain order by sid desc limit 1";
			Statement st=con.createStatement();
		    ResultSet rs=st.executeQuery(query);
		    while(rs.next())
		    {
		    	t=new StudentMain();
		    	t.setSid(rs.getInt(1));
		    	t.setSname(rs.getString(2));
		    	t.setSpwd(rs.getString(3));
		    	list.add(t);
		    }		
		}
		con.close();
		return list;
	}

// Remove Student
//	===========================================================================================	
	@Override
	public void removeStudent(int sid) throws ClassNotFoundException, SQLException, InputMismatchException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ums","root","root");
		
		PreparedStatement ps=con.prepareStatement("delete from studentmain where sid=?");
		ps.setInt(1,sid);
		sleep(750);
		int i=ps.executeUpdate();
		if(i==0){
			System.out.println("Deletion Failed\n");
		}else{
			int i1,i2,i3,i4,i5;
		    PreparedStatement ps1=con.prepareStatement(" delete from studentdata where sid=? ");
			ps1.setInt(1,sid);
			i1=ps1.executeUpdate();
			sleep(250);
		    PreparedStatement ps2=con.prepareStatement(" delete from studentattendance where sid=? ");
			ps2.setInt(1,sid);
			i2=ps2.executeUpdate();
			sleep(250);
		    PreparedStatement ps3=con.prepareStatement(" delete from studentfee where sid=? ");
			ps3.setInt(1,sid);
			i3=ps3.executeUpdate();
			sleep(250);
		    PreparedStatement ps4=con.prepareStatement(" delete from studentlib where sid=? ");
			ps4.setInt(1,sid);
			i4=ps4.executeUpdate();
			sleep(250);
		    PreparedStatement ps5=con.prepareStatement(" delete from studentmarks where sid=? ");
			ps5.setInt(1,sid);
			i5=ps5.executeUpdate();
			sleep(250);
				
			if( (i1+i2+i3+i4+i5)==5 ){
				System.out.println("\nDeleted Student With Student Id: "+sid+" Successfully\n");				
			}
		}
		con.close();
	}
	
// Create Faculty
//	===========================================================================================
	@Override
	public List<FacultyMain> createFaculty(String fname) throws ClassNotFoundException, SQLException, InputMismatchException {
		ArrayList<FacultyMain> list=new ArrayList<FacultyMain>();
		FacultyMain t=null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ums","root","root");
		PreparedStatement ps=con.prepareStatement("insert into facultymain(fname) values(?)");
		ps.setString(1,fname);
		int i=ps.executeUpdate();
		
		if(i==0){		
			System.out.println("Faculty Creation Failed\n");
		}else
		{	
			System.out.println("\nCreating ...\n");
			sleep(1000);
			System.out.println("Faculty Created Successfully\n");
			System.out.println("Generating Credentials please wait...\n");
			sleep(800);
			
			String query="select * from facultymain order by fid desc limit 1";
			Statement st=con.createStatement();
		    ResultSet rs=st.executeQuery(query);
		    while(rs.next())
		    {
		    	t=new FacultyMain();
		    	t.setFid(rs.getInt(1));
		    	t.setFname(rs.getString(2));
		    	t.setFpwd(rs.getString(3));
		    	list.add(t);
		    }
		}
		con.close();
		return list;
	}
	
// Remove Faculty
//	===========================================================================================
	@Override
	public void removeFaculty(int fid) throws ClassNotFoundException, SQLException, InputMismatchException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ums","root","root");
		PreparedStatement ps=con.prepareStatement("delete from facultymain where fid=?");
		ps.setInt(1,fid);
		int i=ps.executeUpdate();
		if(i==0){
			System.out.println("Facaulty Deletion Failed\n");
		}else{
			System.out.println("\nDeleted Faculty With Faculty Id: "+fid+" Successfully\n");
		}
		con.close();
	}
	
// Reset Student Password
//	===========================================================================================
	@Override
	public void resetStudentPassword(int sid)throws ClassNotFoundException, SQLException, InputMismatchException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ums","root","root");
		PreparedStatement ps=con.prepareStatement("update studentmain set spwd=? where sid=?");
		String newpwd="spass@123";
		ps.setString(1,newpwd);
		ps.setInt(2,sid);
		int i=ps.executeUpdate();
		if(i==0){
			System.out.println("Failed to reset password\n");
		}else{
			System.out.println("Password reset successful\n");
		}
		con.close();		
	}
	
// Reset Faculty Password
//	===========================================================================================
	@Override
	public void resetFacultyPassword(int fid)throws ClassNotFoundException, SQLException, InputMismatchException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ums", "root", "root");
		PreparedStatement ps = con.prepareStatement("update facultymain set fpwd=? where fid=?");
		String newpwd="fpass@123";
		ps.setString(1, newpwd);
		ps.setInt(2, fid);
		int i = ps.executeUpdate();
		if (i == 0) {
			System.out.println("Failed to reset password\n");
		} else {
			System.out.println("Password reset successful\n");
		}
		con.close();
	}
	
	public void sleep(int num){
		try {
			Thread.sleep(num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}