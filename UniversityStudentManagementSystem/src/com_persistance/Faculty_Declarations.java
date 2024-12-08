package com_persistance;

import java.sql.SQLException;
import java.util.InputMismatchException;

public interface Faculty_Declarations
{
	public void updateData(int sid) throws ClassNotFoundException, SQLException, InputMismatchException ;
	
	public void updateAttendance(int sid) throws ClassNotFoundException, SQLException, InputMismatchException ;
	
	public void updateFee(int sid) throws ClassNotFoundException, SQLException, InputMismatchException;

	public void updateLib(int sid) throws ClassNotFoundException, SQLException, InputMismatchException ;
	
	public void updateMarks(int sid) throws ClassNotFoundException, SQLException, InputMismatchException ;
	
}