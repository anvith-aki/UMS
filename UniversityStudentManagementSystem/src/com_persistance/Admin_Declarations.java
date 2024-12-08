package com_persistance;

import java.sql.SQLException;
import java.util.*;
import com_entity.*;

public interface Admin_Declarations
{

	public List<StudentMain> createStudent(String sname) throws ClassNotFoundException, SQLException, InputMismatchException;

	public void removeStudent(int sid) throws ClassNotFoundException, SQLException, InputMismatchException;

	public List<FacultyMain> createFaculty(String fname) throws ClassNotFoundException, SQLException, InputMismatchException;

	public void removeFaculty(int fid) throws ClassNotFoundException, SQLException, InputMismatchException;
	
	public void resetStudentPassword(int sid)throws ClassNotFoundException, SQLException, InputMismatchException;

	public void resetFacultyPassword(int fid)throws ClassNotFoundException, SQLException, InputMismatchException;
}