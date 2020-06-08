package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
//import java.sql.Date; // 
//import java.time.LocalDate; 
import java.sql.Date;//not java.sql.Date
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Donations;

import model.Donations;
//import model.Donations;
import model.Donor;
//import model.Referals;
import utility.ConnectionManager;


public class Donordao {
	
	Donor donor=new Donor();
	String  newdonor= "INSERT INTO BLOOD_DONOR"
			+ " (donor_id,name,weight,address,phone_no, b_id) VALUES " + " (seq_donor.nextval, ?, ?, ?,?,?)";
	 String Donations = "INSERT INTO DONATIONS (Donor_ID,Donation_ID,DO_DATE,ADDRESS,BLOOD_GRP)VALUES(?,?,?,?,?)";
  //   String INSERT_INTO_DONOR = "Insert into BLOOD_DONOR(donor_id,name,weight,address, phone_no, b_id) VALUES (?,?,?,?,?,?)";
	 String getdonor = "Select * from BLOOD_DONOR";
	 String UPDATE_DONOR_INFO = "update BLOOD_DONOR set   weight=?, address=?, phone_no=?  where donor_id=?";
//	 String deletedonor =  "Delete from BLOOD_DONOR where donor_id=?";
     String GET_DONORS_BY_BLOODID = "Select * from BLOOD_DONOR";
   //final String Donations = "INSERT INTO DONATIONS (Donation_ID,Donor_ID,DO_DATE)VALUES(?,?,TO_DATE('YYYY/MM/DD'))";
 	final String View_Donations = "SELECT BLOOD_DONOR.name, blood_donor.weight, blood_donor.phone_no,donations.time_date,blood.b_group FROM BLOOD_DONOR INNER JOIN DONATIONS on blood_donor.donor_id = donations.donorid INNER JOIN BLOOD ON blood_donor.b_id = blood.blood_id "; 
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//METHOD TO ADD DONORS	
 	public void addDonor(Donor donor) throws SQLException, Exception {

		Donordao donordao = new Donordao();
		try
		{
			Connection connection = null;
			try {
				connection = ConnectionManager.getConnection();
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//		System.out.println("Enter your ID:");
//		int donor_id1 = Integer.parseInt(br.readLine());
		System.out.println("Enter your name:");
		String name = br.readLine();
		System.out.println("Enter your weight:");
		int weight = Integer.parseInt(br.readLine());
		System.out.println("Enter your address:");
		String addresss = br.readLine();
		System.out.println("Enter your phone number:");
		long phone_no = Long.parseLong(br.readLine());
		System.out.println("Enter your blood group Id");
		System.out.println("Please enter the ID related to your Blood group:");
		System.out.println("O+ - 17");
		System.out.println("A+ - 18");
		System.out.println("A- - 19");
		System.out.println("B- - 20");
		System.out.println("AB+ - 22");
		System.out.println("Please enter your Blood ID from above :");		
		int b_id = Integer.parseInt(br.readLine());
		
//		donor.setDonor_id(donor_id1);
		donor.setName(name);
		donor.setWeight(weight);
		donor.setAddresss(addresss);
		donor.setPhone_no(phone_no);
		donor.setB_id(b_id);
		
		PreparedStatement ps = connection.prepareStatement(newdonor);
//		ps.setInt(1, donor.getDonor_id());
		ps.setString(1, donor.getName());
		ps.setInt(2, donor.getWeight());
		ps.setString(3, donor.getAddresss());
		ps.setLong(4, donor.getPhone_no());
		ps.setInt(5, donor.getB_id());
		ps.executeUpdate();		
		System.out.println("You have succesfully registered as Donor");
		
 	}
	catch (SQLException e) {
	System.out.println("Invalid data");
	}}
//		else
//		{
//			System.out.println("ID Already exits. Please use a different ID");
//		}
		
	
 	
 	

	//METHOD TO GET INFORMATION ON DONATIONS MADE 
		public void Donationsinfo() throws SQLException, Exception {
			
			Statement st = ConnectionManager.getConnection().createStatement();
			
			ResultSet rs = st.executeQuery(View_Donations);
			System.out.println("Donor Name"+"\t"+"\t"+"Donor Weight"+"\t"+"Phone"+"\t"+"\t"+"Date"+"\t"+"\t"+"Blood Group");
			while (rs.next())
		{
				System.out.println(rs.getString(1)+"\t"+"\t"+rs.getInt(2)+"\t"+"\t"+rs.getLong(3)+"\t"+rs.getDate(4)+"\t"+rs.getString(5));
			}
			
		}

 //METHOD TO GET WHOLE DONORS LIST	
	public void DonorsList() throws SQLException, Exception {
		
		Statement st = ConnectionManager.getConnection().createStatement();

		ResultSet rs = st.executeQuery(getdonor);
		System.out.println("ID"+"\t"+"NAME"+"\t"+"Wt."+"\t"+"ADDRESS"+"\t"+"\t"+"PHONE_NO"+"\t"+"B_ID");
		
		while(rs.next())
		{		
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6));
		}
	
		}
	
    
//METHOD TO UPDATE DONORS INFORMATION	
	public void updateDonor(Donor donor) throws SQLException, Exception {
		
		Donordao donordao = new Donordao();
		try
		{
			Connection connection = null;
			try {
				connection = ConnectionManager.getConnection();
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println("Enter your ID:");
		int donor_id1 = Integer.parseInt(br.readLine());
		if (donordao.searchFordonorId(donor_id1)) {
		System.out.println("Enter your weight:");
		int weight = Integer.parseInt(br.readLine());
		System.out.println("Enter your address:");
		String addresss = br.readLine();
		System.out.println("Enter your phone number:");
		long phone_no = Long.parseLong(br.readLine());
		
			donor.setDonor_id(donor_id1);
			donor.setWeight(weight);
			donor.setAddresss(addresss);
			donor.setPhone_no(phone_no);
			
		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(UPDATE_DONOR_INFO);
	      
		ps.setInt(1, donor.getWeight());
		ps.setString(2, donor.getAddresss());
		ps.setLong(3, donor.getPhone_no());
		ps.setInt(4, donor.getDonor_id());

		
		boolean rowUpdated = ps.executeUpdate() >0;
		if(rowUpdated)
		{
			System.out.println(" Donor Details Updated");
		}
		else
		{
			System.out.println("No Donor with give ID, Please check the ID");
			
		}
		}
		else {
			System.out.println("Incorrect ID ,Please enter the id among below ");
			donordao.retriveupdate();
			}
		}
		catch (SQLException e) {
		System.out.println("Invalid data");
		}
	}

	//METHOD TO SEARCHID 
	public boolean searchFordonorId(int donor_id1) throws  SQLException,Exception {

		boolean result = false;
		
		Statement st = ConnectionManager.getConnection().createStatement();

		ResultSet rs = st.executeQuery(getdonor);
		while(rs.next()) {
			if(rs.getInt(1) == donor_id1) {
				result = true;
		}
		
		}
		return result;
	}

	//METHOD TO DELETE DONOR
	public void deleteDonor(int donorid) throws Exception{
		Donordao donordao = new Donordao();
		boolean rowDeleted;
		Connection connection = ConnectionManager.getConnection();
			PreparedStatement statement = connection.prepareStatement("delete from BLOOD_DONOR where donor_id=?");
			statement.setInt(1, donorid);
			rowDeleted = statement.executeUpdate() > 0;
		
		if(rowDeleted) {
			System.out.println("Donor is deleted");
	}
		else {
			System.out.println("No Donor with the given id");
			donordao.retrivedelete();
		}
		
	}

	//METHOD TO CHECK DONOR BY BLOOD GROUP
	public void searchdonorbybloodgroupid(int blood_id) throws SQLException, Exception {
		Statement st = ConnectionManager.getConnection().createStatement();
		ResultSet rs = st.executeQuery(GET_DONORS_BY_BLOODID);
		int count = 0;
		while(rs.next()) {
			if (rs.getInt(6) ==blood_id) {
				System.out.println(rs.getInt("donor_id")+"\t"+rs.getString("name")+"\t"+rs.getString("weight")+"\t"+rs.getString("address")+"\t"+rs.getString("phone_no")+"\t"+rs.getString("b_id"));
					count++;
			}
		}
		
		if(count ==0) {
			System.out.println("Id not found");
		}
		
	}
	

 	
	public void addDonationDetails(Donations donations) throws SQLException, Exception {
		Donordao donordao = new Donordao();
		try
		{
			Connection connection = null;
			try {
				connection = ConnectionManager.getConnection();
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		System.out.println("Enter your donor ID:");
		int donor_id1 = Integer.parseInt(br.readLine());
		if (donordao.searchFordonorId(donor_id1)) {
		System.out.println("Enter your Donation id:");
		int donations_id = Integer.parseInt(br.readLine());
		System.out.println("Enter date:");
		String donation_date=br.readLine();
		System.out.println("Enter your address:");
		String addresss = br.readLine();
		System.out.println("Enter your blood group Id");
		System.out.println("Please enter the ID related to your Blood group:");
		System.out.println("O+ - 17");
		System.out.println("A+ - 18");
		System.out.println("A- - 19");
		System.out.println("B- - 20");
		System.out.println("AB+ - 22");
		System.out.println("Please enter your Blood ID now");		
		int b_id = Integer.parseInt(br.readLine());
		
		donations.setDonor_id(donor_id1);
	    donations.setDonations_id(donations_id);
	    donations.setDonation_date(donation_date);
	    donations.setAddresss(addresss);
	    donations.setB_id(b_id);
	    
	    
		PreparedStatement ps = connection.prepareStatement(Donations);
		ps.setInt(1, donations.getDonor_id());
		ps.setInt(2,donations.getDonations_id());
		ps.setString(3,donations. getDonation_date());
		ps.setString(4, donations.getAddresss());
		ps.setInt(5, donations.getB_id());
		ps.executeUpdate();		
		System.out.println("You have succesfully registered ");
		}
		else
		{
			System.out.println("WRONG ID DONOR NOT REGISTERED");
		}
		}
		catch (SQLException e) {
		System.out.println(e);
		}
	}
	  public void retrivedelete() throws SQLException, Exception {
	    	Donordao donordao = new Donordao();
			Statement st = ConnectionManager.getConnection().createStatement();

			ResultSet rs = st.executeQuery(getdonor);
			System.out.println("ID"+"\t"+"NAME");
			
			while(rs.next())
			{		
				System.out.println(rs.getInt(1)+rs.getString("name"));
				
			}
			System.out.println("Enter donor_id which you wnat to delete :");
			int donorid = Integer.parseInt(br.readLine());
			donordao.deleteDonor(donorid);
		
			}

	  public void retriveupdate() throws SQLException, Exception {
			Donordao donordao=new Donordao();
			Donor donor=new Donor();
			
			Statement st = ConnectionManager.getConnection().createStatement();
			ResultSet rs = st.executeQuery(getdonor);
			System.out.println("ID"+"\t"+"\t"+"NAME");
			while(rs.next())
			{		
				System.out.println(rs.getInt(1)+"\t"+"\t"+rs.getString(2));
				
			}
			
		    donordao.updateDonor(donor);
		
			}
	  public void retrive() throws SQLException, Exception {
	    	Donordao donordao = new Donordao();
			Statement st = ConnectionManager.getConnection().createStatement();

			ResultSet rs = st.executeQuery(getdonor);
			System.out.println("ID"+"\t"+"NAME");
			
			while(rs.next())
			{		
				System.out.println(rs.getInt(1)+rs.getString("name"));
				
			}
			}

}