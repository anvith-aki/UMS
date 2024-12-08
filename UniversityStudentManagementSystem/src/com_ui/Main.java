package com_ui;

import java.sql.*;
import java.util.*;
import com_entity.*;
import com_service.*;

public class Main
{
	public static Scanner sc = new Scanner(System.in);

//	Main UI
//	================================================================================================
	public static void main(String args[]) throws ClassNotFoundException, SQLException
	{
		
		System.out.println("Login Into University As\n \n1.Admin\n2.Faculty\n3.Student");
		System.out.println("Enter Choice : ");
		try
		{		
			int choice = sc.nextInt();
			switch (choice) {
	
			case 1:
				System.out.println("Enter Admin Username : ");
				String username = sc.next();
				System.out.println("Enter Password : ");
				String password = sc.next();
				adminpage(username, password);
				break;
	
			case 2:
				System.out.println("Enter Faculty ID : ");
				int fid = sc.nextInt();
				System.out.println("Enter Password : ");
				String fpwd= sc.next();
				facultypage(fid, fpwd);
				break;
	
			case 3:
				System.out.println("Enter Student ID : ");
				int sid = sc.nextInt();
				System.out.println("Enter Password : ");
				String spwd = sc.next();
				studentpage(sid, spwd);
				break;
	
			default:
				System.out.println("Invalid Choice !!!");
	
			}
		}
		catch(InputMismatchException e){
			System.out.println("Error: Enter Valid Input");
			System.exit(0);
		}
		catch(Exception e){
			System.out.println(e);
			System.exit(0);
		}
	}

//	Admin Page 
//	===========================================================================================
	
	public static void adminpage(String username, String password) throws ClassNotFoundException, SQLException
	{	
		if(username.equals("admin") && password.equals("admin"))
		{
			System.out.println("\nLogged Into Admin Portal\n");
			try{
				while(true) 
				{
					System.out.println("1.Create New Student");
					System.out.println("2.Remove Student");
					System.out.println("3.Create New Faculty");
					System.out.println("4.Remove Faculty");
					System.out.println("5.Reset Student-Password");
					System.out.println("6.Reset Faculty-Password");
					System.out.println("7.Exit");
					System.out.println("");
					System.out.println("Select Choice : ");
					int ch = sc.nextInt();
						
					switch(ch)
					{
						case 1:
							createStudent();
							break;
						case 2:
							removeStudent();
							break;
						case 3:
							createFaculty();
							break;
						case 4:
							removeFaculty();
							break;
						case 5:
							resetStudentPassword();
							break;
						case 6:
							resetFacultyPassword();
							break;
						case 7:
							System.out.println("Logged Out Successfully");
							System.out.println("Bye...");
							System.exit(0);
								
						default: System.out.println("Invalid Choice !!!");
					}
				}
			}
			catch(InputMismatchException e){
				System.out.println("Error: Enter Valid Input");
				System.exit(0);
			}
			catch(Exception e){
				System.out.println(e);
				System.exit(0);
			}
		}else{
			System.out.println("Invalid Credentials !!!");
			return;
		}
	}

	// Admin's Choices
	public static void resetFacultyPassword() throws ClassNotFoundException, SQLException, InputMismatchException{
		System.out.println("Enter Faculty Id: ");
		int fid=sc.nextInt();
		new Admin_Service_Implementations().resetFacultyPassword(fid);
	}
	
	public static void removeFaculty() throws ClassNotFoundException, SQLException, InputMismatchException {
		System.out.println("Enter Faculty Id: ");
		int fid=sc.nextInt();
		new Admin_Service_Implementations().removeFaculty(fid);
		
	}

	public static void createFaculty() throws ClassNotFoundException, SQLException, InputMismatchException {
		System.out.println("Enter Faculty Name: ");
		String fname=sc.next();
		System.out.println("");
		List<FacultyMain> facultylist =new Admin_Service_Implementations().createFaculty(fname);
		for (FacultyMain f : facultylist) {
			System.out.println(f);
		}
		System.out.println("");	
		
	}

	public static void resetStudentPassword() throws ClassNotFoundException, SQLException, InputMismatchException{
		System.out.println("Enter Student Id: ");
		int sid=sc.nextInt();
		new Admin_Service_Implementations().resetStudentPassword(sid);
	}
	
	public static void removeStudent() throws ClassNotFoundException, SQLException, InputMismatchException {
		System.out.println("Enter Student Id: ");
		int sid=sc.nextInt();
		new Admin_Service_Implementations().removeStudent(sid);
		
	}

	public static void createStudent() throws ClassNotFoundException, SQLException, InputMismatchException {
		System.out.println("Enter Student Name: ");
		String sname=sc.next();
		System.out.println("");
		List<StudentMain> studentlist =new Admin_Service_Implementations().createStudent(sname);
		for (StudentMain s : studentlist) {
			System.out.println(s);
		}
		System.out.println("");	
	}

//	Faculty Page 
//	===========================================================================================
	
	public static void facultypage(int fid, String fpwd) throws SQLException, ClassNotFoundException
	{
		// faculty validation
		
        int userId = fid;
        String password = fpwd;

        // Connect to the database
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ums","root","root");
        String sql = "select fpwd from facultymain where fid = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();

        // Check if a row was returned
        if (resultSet.next()) {
            // Get the password from the result set
            String storedPassword = resultSet.getString("fpwd");

            // Check if the entered password matches the stored password
            if (password.equals(storedPassword))
            {
				System.out.println("\nLogged Into Faculty Portal\n");
				try{
					while (true) 
					{
						System.out.println("1.Update Student Data");
						System.out.println("2.Update Student Attendance");
						System.out.println("3.Update Student Fee");
						System.out.println("4.Update Student Marks");
						System.out.println("5.Update Student Library Info");
						System.out.println("6.Get Student Attendance Report");
						System.out.println("7.Get Student Fee Report");
						System.out.println("8.Get Student Marks Report");
						System.out.println("9.Get Student Library Info Report");
						System.out.println("10.Update Password-Faculty");
						System.out.println("11.Logout");
						System.out.println("");
						System.out.println("Select Choice : ");
						int ch = sc.nextInt();
		
						switch (ch) 
						{
						case 1:updateData();
							break;
							
						case 2:updateAttendance();
							break;
							
						case 3:updateFee();
							break;
							
						case 4:updateMarks();
							break;
							
						case 5:updateLib();
							break;
							
						case 6:getAttendance();
							break;
							
						case 7:getFees();
							break;
							
						case 8:getMarks();
							break;
							
						case 9:getLib();
							break;
							
						case 10:updateFacultyPassword(fid);
							break;
							
						case 11:
							System.out.println("Logged Out Successfully");
							System.out.println("Bye...");
							System.exit(0);
							
						default: System.out.println("Invalid Choice !!!");
			
						}
					}
				}
				catch(InputMismatchException e){
					System.out.println("Error: Enter Valid Input");
					System.exit(0);
				}
				catch(Exception e){
					System.out.println(e);
					System.exit(0);
				}
            }
            else
            {
                System.out.println("Invalid credentials");
            }
        }
        else 
        {
            System.out.println("No credentials found");
        }
        connection.close();
	}

	public static void updateFacultyPassword(int fid) throws ClassNotFoundException, SQLException, InputMismatchException {
		System.out.println("Enter your new password : ");
		String newpwd=sc.next();
		new Faculty_Service_Implementations().updateFacultyPassword(fid,newpwd);
	}

	public static void getMarks() {
		new Faculty_Service_Implementations().export("studentmarks");
		System.out.println("Generating report please wait");
		sleep(1000);
		System.out.println("Marks report generated ...\n");
	}
	
	public static void getLib() {
		new Faculty_Service_Implementations().export("studentlib");
		System.out.println("Generating report please wait");
		sleep(1000);
		System.out.println("Library report generated ...\n");
	}

	public static void getFees() {
		new Faculty_Service_Implementations().export("studentfee");
		System.out.println("Generating report please wait");
		sleep(1000);
		System.out.println("Fee report generated ...\n");
	}

	public static void getAttendance() {
		new Faculty_Service_Implementations().export("studentattendance");
		System.out.println("Generating report please wait");
		sleep(1000);
		System.out.println("Attendance report generated ...\n");
	}

	public static void updateData() throws InputMismatchException, ClassNotFoundException, SQLException {
		
		System.out.println("Enter Student Id: ");
		int sid=sc.nextInt();
		new Faculty_Service_Implementations().updateData(sid);
	}

	public static void updateAttendance() throws InputMismatchException, ClassNotFoundException, SQLException {
		System.out.println("Enter Student Id: ");
		int sid=sc.nextInt();
		new Faculty_Service_Implementations().updateAttendance(sid);
	}

	public static void updateFee() throws ClassNotFoundException, SQLException, InputMismatchException {
		System.out.println("Enter Student Id: ");
		int sid=sc.nextInt();
		new Faculty_Service_Implementations().updateFee(sid);
	}

	public static void updateMarks() throws ClassNotFoundException, SQLException, InputMismatchException {
		System.out.println("Enter Student Id: ");
		int sid=sc.nextInt();
		new Faculty_Service_Implementations().updateMarks(sid);
	}

	public static void updateLib() throws ClassNotFoundException, SQLException, InputMismatchException {
		System.out.println("Enter Student Id: ");
		int sid=sc.nextInt();
		new Faculty_Service_Implementations().updateLib(sid);
	}

//	Student Page 
//	===========================================================================================
	
	public static void studentpage(int sid, String spwd) throws SQLException, ClassNotFoundException, InputMismatchException
	{
		// Student Validation
        int userId = sid;
        String password = spwd;

        // Connect to the database
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ums","root","root");
        String sql = "SELECT spwd FROM studentmain WHERE sid = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, userId);
        ResultSet resultSet = statement.executeQuery();

        // Check if a row was returned
        if (resultSet.next()) {
            // Get the password from the result set
            String storedPassword = resultSet.getString("spwd");

            // Check if the entered password matches the stored password
            if (password.equals(storedPassword))
            {
				System.out.println("\nLogged Into Student Portal\n");
				try{
					while (true) {
						System.out.println("1.View Student Data");
						System.out.println("2.View Student Attendance");
						System.out.println("3.View Student Fee");
						System.out.println("4.View Student Marks");
						System.out.println("5.View Student Library Info");
						System.out.println("6.Update Password-Student");
						System.out.println("7.Logout");
						System.out.println("");
						System.out.println("Select Choice : ");
						int ch = sc.nextInt();
			
						switch (ch) {
						case 1:
							viewData(sid);
							break;
						case 2:
							viewAttendance(sid);
							break;
						case 3:
							viewFee(sid);
							break;
						case 4:
							viewMarks(sid);
							break;
						case 5:
							viewLib(sid);
							break;
						case 6:updateStudentPassword(sid);
							break;
						case 7:
							System.out.println("Logged Out Successfully");
							System.out.println("Bye...");
							System.exit(0);
							
						default: System.out.println("Invalid Choice !!!");
			
						}
					}
				}
				catch(InputMismatchException e){
					System.out.println("Error: Enter Valid Input");
					System.exit(0);
				}
				catch(Exception e){
					System.out.println(e);
					System.exit(0);
				}
            }
            else
            {
                System.out.println("Invalid credentials");
            }
        }
        else
        {
            System.out.println("No credentials found");
        }
        connection.close();
	}

	public static void updateStudentPassword(int sid) throws ClassNotFoundException, SQLException, InputMismatchException {
		System.out.println("Enter your new password : ");
		String newpwd=sc.next();
		new Student_Service_Implementations().updateStudentPassword(sid,newpwd);
	}

	public static void viewData(int sid) throws ClassNotFoundException, SQLException, InputMismatchException {
		System.out.println("");
		List<StudentData> studentlist = new Student_Service_Implementations().viewData(sid);
		for (StudentData s : studentlist) {
			System.out.println(s);
		}
		System.out.println("");
	}

	public static void viewAttendance(int sid) throws ClassNotFoundException, SQLException, InputMismatchException {
		System.out.println("");
		List<StudentAttendance> studentlist = new Student_Service_Implementations().viewAttendance(sid);
		for (StudentAttendance s : studentlist) {
			System.out.println(s);
		}
		System.out.println("");
	}

	public static void viewFee(int sid) throws ClassNotFoundException, SQLException, InputMismatchException {
		System.out.println("view fee");
		System.out.println("");
		List<StudentFee> studentlist = new Student_Service_Implementations().viewFee(sid);
		for (StudentFee s : studentlist) {
			System.out.println(s);
		}
		System.out.println("");
	}

	public static void viewMarks(int sid) throws ClassNotFoundException, SQLException, InputMismatchException {
		System.out.println("");
		List<StudentMarks> studentlist = new Student_Service_Implementations().viewMarks(sid);
		for (StudentMarks s : studentlist) {
			System.out.println(s);
		}
		System.out.println("");
	}

	public static void viewLib(int sid) throws ClassNotFoundException, SQLException, InputMismatchException {
		System.out.println("");
		List<StudentLib> studentlist = new Student_Service_Implementations().viewLib(sid);
		for (StudentLib s : studentlist) {
			System.out.println(s);
		}
		System.out.println("");
	}
	
	public static void sleep(int num){
		try {
			Thread.sleep(num);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}