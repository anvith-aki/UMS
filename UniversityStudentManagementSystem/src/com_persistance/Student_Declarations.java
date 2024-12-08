package com_persistance;

import java.sql.*;
import java.util.*;
import com_entity.*;

public interface Student_Declarations
{
	public  List<StudentData> viewData(int sid) throws ClassNotFoundException, SQLException, InputMismatchException;

	public  List<StudentAttendance> viewAttendance(int sid) throws ClassNotFoundException, SQLException, InputMismatchException;
	
	public  List<StudentFee> viewFee(int sid) throws ClassNotFoundException, SQLException, InputMismatchException;
	
	public  List<StudentMarks> viewMarks(int sid) throws ClassNotFoundException, SQLException, InputMismatchException;
	
	public  List<StudentLib> viewLib(int sid) throws ClassNotFoundException, SQLException, InputMismatchException;
	
	public void updateStudentPassword(int sid, String newpwd) throws ClassNotFoundException, SQLException, InputMismatchException;
}
