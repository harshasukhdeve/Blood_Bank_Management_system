package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utility.ConnectionManager;

public class Validate {

	// VALIDATION FOR INPUT DATA 
	String regex1=	"^[a-zA-Z]*$";
	String regex2="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	String regex3="((?=.*[a-z])(?=.*d)(?=.*[@#$%&])(?=.*[A-Z]).{4,16})";
	String regex4="^[0-9]*$";
	
	String regex5="^[A-Za-z0-9_-]*$";
	
	//admin signup validation
	public boolean signupvalidation(String name, String email, String password,String mobilenumber,String uniqueid) {
		if(name.matches(regex1)&& email.matches(regex2)&&password.matches(regex3)&& mobilenumber.matches(regex4) &&uniqueid.matches(regex4))
		return true;
		else
		return false;
	}
	
	//validate hospital entry
	public boolean Hospitalvalidation(String email,String password) {
		if(email.matches(regex2)&&password.matches(regex3))
			return true;
		else
		return false;
	
	
}
}
