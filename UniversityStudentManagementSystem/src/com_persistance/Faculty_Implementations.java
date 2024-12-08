package com_persistance;

import java.util.*;
import java.util.regex.Pattern;
import java.io.*;
import java.sql.*;

public class Faculty_Implementations implements Faculty_Declarations {

	Scanner sc = new Scanner(System.in);

// update data
// ===================================================================================================

	public void updateData(int sid) throws ClassNotFoundException, SQLException, InputMismatchException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ums", "root", "root");
		String column;
		//
		// to ensure column name is given correctly
		do
		{
			System.out.println("To Update (sname/dob/email/loc)");
			column = sc.next();
			column = column.toLowerCase();
			
		}while (!( column.equals("sname") || column.equals("dob") || column.equals("email") || column.equals("loc") ));

		String value = null;
		if(column.equals("dob"))
		{	
			//
			// to ensure D.O.B date being set/updated into database is valid
			do{	
				System.out.println("Enter Day(dd)");
				int day=sc.nextInt();
				System.out.println("Enter Month(mm)");
				int month=sc.nextInt();
				System.out.println("Enter Year(yyyy)");
				int year=sc.nextInt();
				value=year+"-"+month+"-"+day;
				
				if(!isValidDate(value)){
					System.out.println("Enter a valid date\n");					
				}
			}while( !(isValidDate(value)) );
		}
		else if(column.equals("email")){
			
			do{	
				//
				// to ensure Email being set/updated into database is valid
				System.out.println("Enter email :");
				String mail=sc.next();
				value=mail;
				if(!isValidMail(value)){
					System.out.println("Email must be in format abc@xyz.com");
					System.out.println("Enter a valid mail\n");					
				}
			}while( !(isValidMail(value)) );
		}
		else{
			System.out.println("Enter " + column + " value to update");
			value = sc.next();			
		}
		
		PreparedStatement p = con.prepareStatement("update studentdata set " +column+ "=? where sid=?");
		p.setString(1, value);
		p.setInt(2, sid);
		int i = p.executeUpdate();

		if (i != 0)
		{
			if(column.equals("sname"))
			{
				//	to ensure name is being updated in all student tables
				PreparedStatement p1 = con.prepareStatement("update studentattendance set " +column+ "=? where sid=?");
				p1.setString(1, value);
				p1.setInt(2, sid);
				p1.executeUpdate();
				sleep(250);
				PreparedStatement p2 = con.prepareStatement("update studentfee set " +column+ "=? where sid=?");
				p2.setString(1, value);
				p2.setInt(2, sid);
				p2.executeUpdate();
				sleep(250);
				PreparedStatement p3 = con.prepareStatement("update studentmarks set " +column+ "=? where sid=?");
				p3.setString(1, value);
				p3.setInt(2, sid);
				p3.executeUpdate();
				sleep(250);
				PreparedStatement p4 = con.prepareStatement("update studentlib set " +column+ "=? where sid=?");
				p4.setString(1, value);
				p4.setInt(2, sid);
				p4.executeUpdate();
				sleep(250);
				PreparedStatement p5 = con.prepareStatement("update studentmain set " +column+ "=? where sid=?");
				p5.setString(1, value);
				p5.setInt(2, sid);
				p5.executeUpdate();
				sleep(250);
			}
			System.out.println("Student "+column+" Updated\n");
			PreparedStatement p1 = con.prepareStatement("select * from studentdata where sid=?");
			p1.setInt(1, sid);

			ResultSet r = p1.executeQuery();
			while (r.next()) {
				System.out.println("Displaying details\n");
				System.out.println("Student Id: "+r.getInt(1) + ", " +"Name: "+ r.getString(2) + ", " +"DOB: "+r.getString(3) + ", " 
				+"Email: "+ r.getString(4)+ ", " +"Location: "+ r.getString(5));
				System.out.println("");
			}
		} else {
			System.out.println("Updation Failed !!!\n");
		}
		con.close();
	}

// update Attendance 
// ===================================================================================================

	public void updateAttendance(int sid) throws ClassNotFoundException, SQLException, InputMismatchException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ums", "root", "root");
		//
		//	ensuring no misleading values are updating
		int value,totaldays=0;
		do{
			System.out.println("Set Days Present ");
			value = sc.nextInt();
			
			String sql = "select totaldays from studentattendance where sid = ?";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setInt(1, sid);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next())
	        	totaldays=resultSet.getInt("totaldays");
	        
	        if(value<0)
	        {
	        	System.out.println("Cannot update misleading values\n");
	        }
	        if(value>totaldays)
	        {
	        	System.out.println("Days Present cannot be >"+totaldays+"\n");
	        }
		}while(!(value>=0) || !(value<=totaldays));
		
		PreparedStatement p1 = con.prepareStatement("update studentattendance set dayspresent=?, percentage=(dayspresent/totaldays)*100 where sid=? and ?>=0 and ?<=totaldays");
		p1.setInt(1, value);
		p1.setInt(2, sid);
		p1.setInt(3, value);
		p1.setInt(4, value);
		int i = p1.executeUpdate();
		if (i != 0)
		{
			System.out.println("Days Present Updated\n");
			PreparedStatement p3 = con.prepareStatement("select * from studentattendance where sid=?");
			p3.setInt(1, sid);

			ResultSet r = p3.executeQuery();
			while (r.next()) {
				System.out.println("Displaying details\n");
				System.out.println("Student Id: "+r.getInt(1) + ", " +"Name: "+ r.getString(2) + ", " +"DaysPresent: "+
				r.getInt(3) + ", " +"TotalDays: "+ r.getInt(4) + ", " +"Percentage: "+ r.getInt(5)+"%");
				System.out.println("");
			}
		} else {
			System.out.println("Updation Failed !!!\n");
		}
		con.close();
	}

// update fee
// ===================================================================================================

	public void updateFee(int sid) throws ClassNotFoundException, SQLException, InputMismatchException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ums", "root", "root");
		int amount,due=0;
		//
		//to ensure ( to ensure amount input by user is not -ve & not > pending amt )
		do{
			String sql = "select feedue from studentfee where sid = ?";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setInt(1, sid);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next())
	        due=resultSet.getInt("feedue");
	        
	        System.out.println("Your Due is : "+due+"\n");
	        
	        if(due==0){
		        System.out.println("You have no fee due\n");
		        return;
	        }
	        
			System.out.println("Enter the amount paying");
			amount = sc.nextInt();
	        
	        if(amount<0)
	        System.out.println("Warning can't update misleading values\n");
	
	        if(amount==0)
	        System.out.println("You are paying nothing\n");
	        
	        if(amount>due)
	        System.out.println("Your are paying more than the due amount\n");
	        
		}while(!(amount<=due) || !(amount>=0));
		PreparedStatement ps = con.prepareStatement("update studentfee set feepaid=feepaid+"+amount+", feedue=feedue-"+amount+" where sid=? and ?>=0 and ?<=feedue");
		ps.setInt(1, sid);
		ps.setInt(2, amount);
		ps.setInt(3, amount);
		int i = ps.executeUpdate();

		if (i == 0) {
			System.out.println("Updation Failed !!!\n");
		} else {
			if(amount>0){
				System.out.println("Fee Updated\n");				
			}
			PreparedStatement ps3 = con.prepareStatement("select * from studentfee where sid=?");
			ps3.setInt(1, sid);
			ResultSet rs = ps3.executeQuery();
			while (rs.next()) {
				System.out.println("Displaying details\n");
				System.out.println("Student Id: "+rs.getInt(1) + ", " +"Name: "+ rs.getString(2) + ", " +
				"TotalFee: "+rs.getInt(3) + ", " +"FeePaid: "+ rs.getInt(4) + ", "+"FeeDue: "+ rs.getInt(5));
				System.out.println("");
			}
		}
		con.close();
	}

// update lib
// ===================================================================================================

	public void updateLib(int sid) throws SQLException, ClassNotFoundException, InputMismatchException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ums", "root", "root");
		String col_name;
		//
		//to ensure ( to ensure not giving column name wrongly )
		do {
			System.out.println("To Update (bookstaken/booksreturned)");
			col_name = sc.next();
			col_name = col_name.toLowerCase();
		} while (!(col_name.equals("bookstaken") || col_name.equals("booksreturned")));
		//
		// to ensure not updating misleading values
		int no,bookstaken=0,booksreturned=0;
		do{
			System.out.println("Add no. of " + col_name + " ");
			no = sc.nextInt();		
	        
			String sql = "select bookstaken,booksreturned from studentlib where sid = ?";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setInt(1, sid);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()){
		        bookstaken=resultSet.getInt("bookstaken");
		        booksreturned=resultSet.getInt("booksreturned");
	        }
	        if(col_name.equals("booksreturned")){
	        	booksreturned=booksreturned+no;
	        }
			if(booksreturned>bookstaken){
				System.out.println("you cant return more books than taken\n");
				return;
			}
		    if(no<0)
		    System.out.println("Warning can't add misleading values\n");
		      
		    if(no==0)
		    System.out.println("You are adding nothing\n");
	        			
		}while(!(no>0) || !(booksreturned<=bookstaken));
		
		PreparedStatement ps = con.prepareStatement("update studentlib set "+col_name+"="+col_name+"+"+no+
				", bookspending=bookstaken-booksreturned  where sid=?");
		ps.setInt(1, sid);
		int i = ps.executeUpdate();

		if (i == 0) {
			System.out.println("Updation Failed !!!\n");
		} else {
			
			if(no>0)
			System.out.println("Updated "+col_name+"\n");
			
			PreparedStatement ps3 = con.prepareStatement("select * from studentlib where sid=?");
			ps3.setInt(1, sid);
			ResultSet rs = ps3.executeQuery();
			while (rs.next()) {
				System.out.println("Displaying details\n");
				System.out.println("Student Id: "+rs.getInt(1) + ", " +"Name: "+ rs.getString(2) + ", " +
				"Books-Taken: "+rs.getInt(3) + ", " +"Books-Returned: "+ rs.getInt(4) + ", "+"Books-Pending: "+rs.getInt(5));
				System.out.println("");
			}
		}
		con.close();
	}	

// update marks
// ===================================================================================================
	@Override
	public void updateMarks(int sid) throws ClassNotFoundException, SQLException, InputMismatchException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ums", "root", "root");
		int m1, m2, m3;
		//
		// to ensure faculty can't set marks>100 & marks<0 in any subject
		do {
			System.out.println("Set Marks m1, m2, m3");
			System.out.print("M1: ");m1 = sc.nextInt();
			System.out.print("M2: ");m2 = sc.nextInt();
			System.out.print("M3: ");m3 = sc.nextInt();
			
			if(m1<0 || m2<0 || m3<0){
				System.out.println("Marks cannot be negative\n");
			}
			if(m1>100 || m2>100 || m3>100){
				System.out.println("You have entered "+m1+", "+m2+", "+m3);
				System.out.println("Marks cannot be greater than 100\n");
			}
		} while (!( (m1 <= 100 && m1>=0) && (m2 <= 100 && m2>=0) && (m3 <= 100 && m3>=0) ) );

		PreparedStatement ps = con.prepareStatement(
				"update studentmarks set m1=?, m2=?, m3=?, total=(m1+m2+m3), percentage=total/3 where sid=?");
		ps.setInt(1, m1);
		ps.setInt(2, m2);
		ps.setInt(3, m3);
		ps.setInt(4, sid);
		int i = ps.executeUpdate();

		if (i == 0) {
			System.out.println("Updation Failed !!! \n");
		} else {
			PreparedStatement ps3 = con.prepareStatement("select * from studentMarks where sid=?");
			ps3.setInt(1, sid);
			ResultSet rs = ps3.executeQuery();
			while (rs.next()) {
				System.out.println("Displaying details\n");
				System.out.println("Student Id: "+rs.getInt(1) + ", " +"Name: "+ rs.getString(2) + ", " +
				"M1: "+rs.getInt(3) + ", " +"M2: "+ rs.getInt(4) + ", "+"M3: "+ rs.getInt(5) + ", " +
						"Total: "+rs.getInt(6) + ", " +"Percentage: "+ rs.getInt(7)+"%");
				System.out.println("");
			}
		}
		con.close();
	}

// update password
// ===================================================================================================

	public void updateStudentPassword(int fid, String newpwd) throws SQLException, ClassNotFoundException, InputMismatchException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ums", "root", "root");
		PreparedStatement ps = con.prepareStatement("update facultymain set fpwd=? where fid=?");
		ps.setString(1, newpwd);
		ps.setInt(2, fid);
		int i = ps.executeUpdate();
		if (i == 0) {
			System.out.println("Failed to update password\n");
		} else {
			System.out.println("Password Updated Successfully\n");
		}
		con.close();
	}

// generate files
// ===================================================================================================
	
	private BufferedWriter fileWriter;

	public void export(String table) {
	    String jdbcURL = "jdbc:mysql://localhost:3306/ums";
	    String username = "root";
	    String password = "root";
	     
	    String csvFileName = table.concat(".csv");
	     
	   try (Connection connection = DriverManager.getConnection(jdbcURL,username,password)) {
	        String sql = "SELECT * FROM ".concat(table);
	         
	        Statement statement = connection.createStatement();
	         
	        ResultSet result = statement.executeQuery(sql);
	         
	        fileWriter = new BufferedWriter(new FileWriter(csvFileName));
	         
	        int columnCount = writeHeaderLine(result);
	         
	        while (result.next()) {
	            String line = "";
	             
	            for (int i = 1; i <= columnCount; i++) {
	                Object valueObject = result.getObject(i);
	                String valueString = "";
	                 
	                if (valueObject != null) valueString = valueObject.toString();
	                 
	                if (valueObject instanceof String) {
	                    valueString = "\"" + escapeDoubleQuotes(valueString) + "\"";
	                }
	                 
	                line = line.concat(valueString);
	                 
	                if (i != columnCount) {
	                    line = line.concat(",");
	                }
	            }
	             
	            fileWriter.newLine();
	            fileWriter.write(line);  
	        }
	         
	        statement.close();
	        fileWriter.close();
	        connection.close();
	         
	    } catch (SQLException e) {
	        System.out.println("Datababse error:");
	        e.printStackTrace();
	    } catch (IOException e) {
	        System.out.println("File IO error:");
	        e.printStackTrace();
	    }
	     
	}
	
	private int writeHeaderLine(ResultSet result) throws SQLException, IOException {
	    // write header line containing column names
	    ResultSetMetaData metaData = result.getMetaData();
	    int numberOfColumns = metaData.getColumnCount();
	    String headerLine = "";
	     
	    
	    for (int i = 1; i <= numberOfColumns; i++) {
	        String columnName = metaData.getColumnName(i);
	        headerLine = headerLine.concat(columnName).concat(",");
	    }
	     
	    fileWriter.write(headerLine.substring(0, headerLine.length() - 1));
	     
	    return numberOfColumns;
	}
	 
	private String escapeDoubleQuotes(String value) {
	    return value.replaceAll("\"", "\"\"");
	}
	
// date validation
// ======================================================================
	
	static int MAX_VALID_YR = 2023;
    static int MIN_VALID_YR = 1950;
  
    // Returns true if 
    // given year is valid.
    static boolean isLeap(int year)
    {
        // Return true if year is 
        // a multiple of 4 and not 
        // multiple of 100.
        // OR year is multiple of 400.
        return (((year % 4 == 0) && 
                 (year % 100 != 0)) || 
                 (year % 400 == 0));
    }
  
    // Returns true if given 
    // year is valid or not.
    public static boolean isValidDate(String date)
    {
    	String arr[]=date.split("-");
    	int y=Integer.parseInt(arr[0]);
    	int m=Integer.parseInt(arr[1]);
    	int d=Integer.parseInt(arr[2]);
    	
        // If year, month and day 
        // are not in given range
        if (y > MAX_VALID_YR || y < MIN_VALID_YR)
            return false;
        if (m < 1 || m > 12)
            return false;
        if (d < 1 || d > 31)
            return false;
  
        // Handle February month
        // with leap year
        if (m == 2) 
        {
            if (isLeap(y))
                return (d <= 29);
            else
                return (d <= 28);
        }
  
        // Months 4,6,9,11 should have <= 30 days
        if (m == 4 || m == 6 || m == 9 || m == 11)
            return (d <= 30);
  
        return true;
    }
  
// email validation
// ======================================================================
    public static boolean isValidMail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                              
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
	
	public void sleep(int num){
		try {
			Thread.sleep(num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}