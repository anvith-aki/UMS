package com_service;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import com_entity.*;
import com_persistance.*;

public class Admin_Service_Implementations 
{
	public List<StudentMain> createStudent(String sname) throws ClassNotFoundException, SQLException, InputMismatchException{
		return new Admin_Implementations().createStudent(sname);	
	}
	
	public void removeStudent(int sid) throws ClassNotFoundException, SQLException, InputMismatchException{
		new Admin_Implementations().removeStudent(sid);
	}
	
	public List<FacultyMain> createFaculty(String fname) throws ClassNotFoundException, SQLException, InputMismatchException{
		return new Admin_Implementations().createFaculty(fname);
		
	}
	
	public void removeFaculty(int fid) throws ClassNotFoundException, SQLException, InputMismatchException{
		new Admin_Implementations().removeFaculty(fid);
	}
	
	public void resetStudentPassword(int sid) throws ClassNotFoundException, SQLException, InputMismatchException
	{
		new Admin_Implementations().resetStudentPassword(sid);
	}
	
	public void resetFacultyPassword(int fid) throws ClassNotFoundException, SQLException, InputMismatchException
	{
		new Admin_Implementations().resetFacultyPassword(fid);
	}
}
