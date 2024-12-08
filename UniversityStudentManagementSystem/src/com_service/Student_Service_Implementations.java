package com_service;

import java.sql.*;
import java.util.*;
import com_entity.*;
import com_persistance.*;

public class Student_Service_Implementations 
{
	public  List<StudentData> viewData(int sid) throws ClassNotFoundException, SQLException, InputMismatchException
	{
		return new Student_Implementations().viewData(sid);
	}
	public  List<StudentAttendance> viewAttendance(int sid) throws ClassNotFoundException, SQLException, InputMismatchException
	{
		return new Student_Implementations().viewAttendance(sid);
	}
	public  List<StudentFee> viewFee(int sid) throws ClassNotFoundException, SQLException, InputMismatchException
	{
		return new Student_Implementations().viewFee(sid);
	}
	public  List<StudentMarks> viewMarks(int sid) throws ClassNotFoundException, SQLException, InputMismatchException
	{
		return new Student_Implementations().viewMarks(sid);
	}
	public  List<StudentLib> viewLib(int sid) throws ClassNotFoundException, SQLException, InputMismatchException
	{
		return new Student_Implementations().viewLib(sid);
	}
	public void updateStudentPassword(int sid, String newpwd) throws ClassNotFoundException, SQLException, InputMismatchException
	{
		new Student_Implementations().updateStudentPassword(sid, newpwd);
	}
	
}
