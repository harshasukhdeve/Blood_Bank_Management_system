package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.BloodDonationCamp;
import model.Hospital;
import model.HospitalLogin;
import utility.ConnectionManager;

public class Hospitaldao {
	String  newhospital= "INSERT INTO HOSPITAL"
			+ " (HOSPITALID,NAME, PHONE_NO,ADDRESS, USERNAME, EMAIL,PASSWORD) VALUES " + " (seq_hos.nextval, ?, ?, ?,?,?,?)";
	
	String addhospital = "Insert into HOSPITAL(HOSPITALID,NAME,PHONE_NO,ADDRESS,USERNAME,EMAIL,PASSWORD)VALUES(?,?,?,?,?,?,?)";
	String hospitallist = "Select * from HOSPITAL";
	String updatehospital = "update HOSPITAL set NAME=?, PHONE_NO=?, ADDRESS=?,USERNAME=?, EMAIL=?,PASSWORD=? where HOSPITALID=?";
	String Deletehospital = "Delete from Hospital where HOSPITALID=?";
	String adminhospital = "SELECT HOSPITALID, NAME, PHONE_NO, ADDRESS, EMAIL FROM HOSPITAL";
	String donationcamp ="Insert into camp(NAME,MOBILENO,C_DATE,ADDRESS)values(?,?,?,?)";
    String veiwcamp="Select * from camp";
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//ADD HOSPTIAL DETAILS 
	public void addHospital(Hospital hospital) throws SQLException, Exception {
		Hospitaldao hospitaldao = new Hospitaldao();
		try
		{
			Connection connection = null;
			try {
				connection = ConnectionManager.getConnection();
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      int hospital_id = hospital.getHospital_id();
		String hospital_name = hospital.getHospital_name();
		long Phone_number = hospital.getPhone_no();
		String address = hospital.getAddress();
		String username = hospital.getUsername();
		String email = hospital.getEmail();
		String password = hospital.getPassword();
		
		ConnectionManager con = new ConnectionManager();
		
		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(newhospital);
//		ps.setInt(1, hospital_id);
		ps.setString(1, hospital_name);
		ps.setLong(2, Phone_number);
		ps.setString(3, address);
		ps.setString(4, username);
		ps.setString(5, email);
		ps.setString(6, password);
		ps.executeUpdate();
		System.out.println("Your Hospital has been succesfully registered");
		
	}catch(Exception e) {
		System.out.println("Invalid input");

	}
	}
	
	//CHECK VALIDATION FOR HOSPITAL
public boolean hoslogin(HospitalLogin hospitallogin) throws SQLException, Exception {
		
		String email = hospitallogin.getEmailid();
		String password = hospitallogin.getPassword();
		
		ConnectionManager con = new ConnectionManager();
		Statement st = con.getConnection().createStatement();
		
		ResultSet rs = st.executeQuery("Select * from HOSPITAL");
		boolean result = false;
		while(rs.next())
		{
			if(email.equals(rs.getString("EMAIL")) && (password.equals(rs.getString("PASSWORD"))))
			{
				result = true;
			}
			
		}
		return result;
	}
	
//LIST OF HOSPITALS THAT APPEARS ON ADMIN PART
	public void Hospitalslist() throws SQLException, Exception {
		Statement st = ConnectionManager.getConnection().createStatement();
		ResultSet rs = st.executeQuery(adminhospital);
		System.out.println("ID"+"\t"+"HOSPITAL NAME"+"\t"+"\t"+"PHONE"+"\t"+"\t"+"LOCATION"+"\t"+"EMAIL");
		while (rs.next()) {
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+"\t"+"\t"+rs.getLong(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
		}
	}
	
//UPDATING HOSPITALS INFO
	public void UpdateHospital(Hospital hospital) throws SQLException, Exception {
		
		try
		{
			Connection connection = null;
			try {
				connection = ConnectionManager.getConnection();
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		Hospitaldao hospitaldao = new Hospitaldao();
		System.out.println("Update Hospital Details:");
		System.out.println("Enter ID of your Hospital :");
		int hospital_id = Integer.parseInt(br.readLine());
		if(hospitaldao.checkid(hospital_id)) {
		System.out.println("Enter hospital Phone-number :");
		long phone_num = Long.parseLong(br.readLine()); 
		System.out.println("Enter Address of the Hospital :");
		String address = br.readLine();
		System.out.println("Enter your username :");
		String username = br.readLine();
		System.out.println("Enter your email address :");
		String email = br.readLine();
		System.out.println("Enter your password :");
		String password = br.readLine();
		
		hospital.setHospital_id(hospital_id);
		hospital.setPhone_no(phone_num);
		hospital.setAddress(address);
		hospital.setUsername(username);
		hospital.setEmail(email);
		hospital.setPassword(password);
		
		PreparedStatement ps = connection.prepareStatement(updatehospital);
		
		ps.setLong(1, hospital.getPhone_no());
		ps.setString(2, hospital.getAddress());
		ps.setString(3, hospital.getUsername());
		ps.setString(4, hospital.getEmail());
		ps.setString(5, hospital.getPassword());
		ps.setInt(6, hospital.getHospital_id());
		
		boolean rowupdated = ps.executeUpdate() >0;
		if (rowupdated) {
			System.out.println("Hospital Sign in Details updated successfully");
			
		}
		else {
			System.out.println("Nothing was updated");
		}
		}
		else
		{
			System.out.println("Please enter correct among below ");
			hospitaldao.retrivedelete();
			
		}

		}catch(Exception e) {
			System.out.println("Invalid input");

		}
	}

//METHOD TO CHECK ID BEFORE UPDATING THE DETAILS	
	public boolean checkid(int hospital_id) throws SQLException, Exception {
		boolean result = false;
		Statement st = ConnectionManager.getConnection().createStatement();
		ResultSet rs = st.executeQuery(hospitallist);
		while(rs.next())
		{
			if(rs.getInt(1)==hospital_id) {
				result = true;
			}
			break;
		}
	return result;
}
//METHOD TO DELETE HOSPITAL FROM LIST
	public void deletehospital(int id) throws SQLException, Exception {
	
		Hospitaldao hospitaldao=new Hospitaldao();
		boolean rowDeleted;
		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(Deletehospital);
		ps.setInt(1, id);
		rowDeleted = ps.executeUpdate() > 0;
		if (rowDeleted) {
			System.out.println("Your Hospital Profile was succesfully deleted");
		}
		else {
			System.out.println("The entered Id does not exist. Please try again ");
			hospitaldao.retrivedelete();
		}
	
	}
	
	public void donationcamp(BloodDonationCamp camp)throws SQLException, Exception  {
		
		try
		{
			Connection connection = null;
			try {
				connection = ConnectionManager.getConnection();
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Enter organizer Name :");
			String name = br.readLine();
			System.out.println("Enter your Mobile_no :");
			int mobile_no= Integer.parseInt(br.readLine());
			System.out.println("Enter date of camp :");
			String date=br.readLine();
			System.out.println("Enter location to arrange camp :");
			String address = br.readLine();
			
			camp.setName(name);
			camp.setMobile_no(mobile_no);
			camp.setDate(date);
			camp.setAddress(address);
			
			PreparedStatement ps = connection.prepareStatement(donationcamp);
			
			ps.setString(1,camp.getName());
			ps.setInt(2, camp.getMobile_no());
			ps.setString(3, camp.getDate());
			ps.setString(4,camp.getAddress());
			ps.executeUpdate();		
			System.out.println("You have succesfully registered ");
		
		}catch(Exception e) {
			System.out.println("Invalid input");

		}
	}
	
	//add camp detials
	public void viewCampDetails() throws SQLException, Exception {
		Statement st = ConnectionManager.getConnection().createStatement();
		ResultSet rs = st.executeQuery(veiwcamp);
		System.out.println("NAME"+"\t"+"MOBILENO"+"\t"+"\t"+"\t"+"\t"+"DATE"+"\t"+"\t"+"LOCATION"+"");
		while (rs.next()) {
			System.out.println(rs.getString(1)+"\t"+rs.getInt(2)+"\t"+"\t"+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t");
		}
	}
	
	
	public void incermet(Hospital hospital) throws SQLException, Exception {
		Hospitaldao hospitaldao = new Hospitaldao();
		System.out.println("Update Hospital Details:");
		System.out.println("Enter Address of the Hospital :");
		String hospital_name = br.readLine();

		System.out.println("Enter hospital Phone-number :");
		long phone_num = Long.parseLong(br.readLine()); 
		System.out.println("Enter Address of the Hospital :");
		String address = br.readLine();
		System.out.println("Enter your username :");
		String username = br.readLine();
		System.out.println("Enter your email address :");
		String email = br.readLine();
		System.out.println("Enter your password :");
		String password = br.readLine();
		
		hospital.setHospital_name(hospital_name);
		hospital.setPhone_no(phone_num);
		hospital.setAddress(address);
		hospital.setUsername(username);
		hospital.setEmail(email);
		hospital.setPassword(password);
		
		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(newhospital);
		
		ps.setString(1, hospital.getHospital_name());
		ps.setLong(2, hospital.getPhone_no());
		ps.setString(3, hospital.getAddress());
		ps.setString(4, hospital.getUsername());
		ps.setString(5, hospital.getEmail());
		ps.setString(6, hospital.getPassword());
		
		
		boolean rowupdated = ps.executeUpdate() >0;
		if (rowupdated) {
			System.out.println("Hospital Sign in Details updated successfully");
			
		}
		else {
			System.out.println("Nothing was updated");
		}
		
	}
	
	public void retrivedelete() throws SQLException, Exception {
		Hospitaldao hospitaldao = new Hospitaldao();
		Statement st = ConnectionManager.getConnection().createStatement();
		ResultSet rs = st.executeQuery(hospitallist);
		System.out.println("ID"+"\t"+"\t"+"NAME");
		while(rs.next())
		{		
			System.out.println(rs.getInt(1)+"\t"+"\t"+rs.getString(2));
			
		}
		System.out.println("Enter donor_id which you wnat to delete :");
		int donorid = Integer.parseInt(br.readLine());
		hospitaldao.deletehospital(donorid);
	
		}
	
	public void retriveupdate() throws SQLException, Exception {
		Hospitaldao hospitaldao = new Hospitaldao();
		Hospital hospital=new Hospital();
		Statement st = ConnectionManager.getConnection().createStatement();
		ResultSet rs = st.executeQuery(hospitallist);
		System.out.println("ID"+"\t"+"\t"+"NAME");
		while(rs.next())
		{		
			System.out.println(rs.getInt(1)+"\t"+"\t"+rs.getString(2));
			
		}
		hospitaldao.UpdateHospital(hospital);
	
		}
	

   
   
}