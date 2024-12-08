package com_service;

import java.sql.SQLException;
import java.util.InputMismatchException;

import com_persistance.*;

public class Faculty_Service_Implementations
{
	public void updateData(int sid) throws ClassNotFoundException, SQLException, InputMismatchException {
		new Faculty_Implementations().updateData(sid);
	}
	
	public void updateAttendance(int sid) throws ClassNotFoundException, SQLException, InputMismatchException {
	    new Faculty_Implementations().updateAttendance(sid);
	}
	
	public void updateLib(int sid) throws ClassNotFoundException, SQLException, InputMismatchException {
		new Faculty_Implementations().updateLib(sid);
	}
	
	public void updateFee(int sid) throws ClassNotFoundException, SQLException, InputMismatchException{
		new Faculty_Implementations().updateFee(sid);
	}
	
	public void updateMarks(int sid) throws ClassNotFoundException, SQLException, InputMismatchException {
		new Faculty_Implementations().updateMarks(sid);
	}

	public void updateFacultyPassword(int fid, String newpwd) throws ClassNotFoundException, SQLException, InputMismatchException  {
		new Faculty_Implementations().updateStudentPassword(fid, newpwd);
	}

	public void export(String string) {
		new Faculty_Implementations().export(string);
	}
	
}