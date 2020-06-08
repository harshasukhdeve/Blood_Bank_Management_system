package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.AdminRegistration;
import model.Adminlogin;
import services.Validate;
import utility.ConnectionManager;

public class Admindao {
 
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String passwordchange= "Update ADMINSIGNUP set password = ? where  emailid = ?"; 
		Validate validate=new Validate();
	Adminlogin adminlogin=new Adminlogin();
//	 String changepass="select * from adminsignup";
	//signup functionality
	public int signup(AdminRegistration user)throws Exception
	{
		//sql query
		String insert_admin= "INSERT INTO ADMINSIGNUP(username,emailid,password,mobilenumber,uniqueid)VALUES(?,?,?,?,?)";
		int result=0;
		try
		{
			Connection connection = null;
			try {
				connection = ConnectionManager.getConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			PreparedStatement preparedstatement= connection.prepareStatement(insert_admin);
			preparedstatement.setString(1,user.getUsername());
			preparedstatement.setString(2,user.getEmail());
	        preparedstatement.setString(3,user.getPassword());
	        preparedstatement.setString(4,user. getMobilenumber());
	        preparedstatement.setString(5,user.getUniqueid());
	        
            result = preparedstatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return result;
	}
	
	//login functionality
	public boolean loginUser(AdminRegistration user) throws Exception{
		boolean status = false;
		//connection
		try{
			Connection connection = null;
			try {
				connection = ConnectionManager.getConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		PreparedStatement preparedStatement = connection.prepareStatement("select * from adminsignup where emailid = ? and password = ? ");
		preparedStatement.setString(1, user.getEmail());
	    preparedStatement.setString(2, user.getPassword());
        ResultSet result = preparedStatement.executeQuery();
			status = result.next();

		} catch (SQLException e) {
			// process sql exception
			System.out.println(e);
		}
		return status;
		}
	

}
