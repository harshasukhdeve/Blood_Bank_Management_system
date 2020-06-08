package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Referals;
import utility.ConnectionManager;

public class ReferalDAO {
	
	final String add_referals = "Insert into REFERRALS(id,name,address,phone_number) VALUES (?,?,?,?)";		
    final String view_referals = "Select blood_donor.name ,referrals.name, referrals.address, referrals.phone_number FROM BLOOD_DONOR INNER JOIN REFERRALS on BLOOD_DONOR.DONOR_ID = referrals.id";
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	Referals referals = new Referals();
    //ADD REFERAL DETAILS 
	public void referrals() throws SQLException, Exception {
		Donordao donordao=new Donordao();
		System.out.println("Enter your Donor ID :");
		int id = Integer.parseInt(br.readLine());
		if (donordao.searchFordonorId(id)) {
		System.out.println("Enter name of the person whom you want to refer :");
		String referral_name = br.readLine();
		System.out.println("Enter location of the referral :");
		String location = br.readLine();
		System.out.println("Enter Phone number of the referral :");
		long phn_number = Long.parseLong(br.readLine());
		
		referals.setID(id);
		referals.setName(referral_name);
		referals.setAddress(location);
		referals.setPhone_number(phn_number);
		
		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(add_referals);
		
		ps.setInt(1, referals.getID());
		ps.setString(2, referals.getName());
		ps.setString(3, referals.getAddress());
		ps.setLong(4, referals.getPhone_number());
		
		ps.executeUpdate();
		System.out.println("You have succesfully registered your referral");
		}
		else {
			System.out.println("Please enter correct Id");
			donordao.retrive();
			ReferalDAO referal=new ReferalDAO();
			referal.referrals();
		}
		
	}
	
	//SEE REFFERAL LIST 
	public void seereferals() throws SQLException, Exception {
		Statement st = ConnectionManager.getConnection().createStatement();
		
		ResultSet rs = st.executeQuery(view_referals);
		System.out.println("USER NAME"+"\t"+"Referral Name"+"\t"+"\t"+"Location"+"\t"+"\t"+"Phone number");
		while(rs.next()) {
			System.out.println(rs.getString(1)+"\t"+"\t"+rs.getString(2)+"\t"+"\t"+"\t"+rs.getString(3)+"\t"+"\t"+ rs.getLong(4));
		}
	}
	
		
	}