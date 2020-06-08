//package model;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//import dao.Donordao;
//import utility.ConnectionManager;
//
//public class Dona {
//	
//	
//	public void addDonationDetails(Donations donations) throws SQLException, Exception {
//		Donordao donordao = new Donordao();
//
//		System.out.println("Enter your donor ID:");
//		int donor_id1 = Integer.parseInt(br.readLine());
//		if (donordao.searchFordonorId(donor_id1)) {
//		System.out.println("Enter your Donation id:");
//		int donations_id = Integer.parseInt(br.readLine());
//		System.out.println("Enter date:");
//		String donation_date=br.readLine();
//		System.out.println("Enter your address:");
//		String addresss = br.readLine();
//		System.out.println("Enter your blood group Id");
//		System.out.println("Please enter the ID related to your Blood group:");
//		System.out.println("O+ - 17");
//		System.out.println("A+ - 18");
//		System.out.println("A- - 19");
//		System.out.println("B- - 20");
//		System.out.println("AB+ - 22");
//		System.out.println("Please enter your Blood ID now");		
//		int b_id = Integer.parseInt(br.readLine());
//		
//		donations.setDonor_id(donor_id1);
//	    donations.setDonations_id(donations_id);
//	    donations.setDonation_date(donation_date);
//	    donations.setAddresss(addresss);
//	    donations.setB_id(b_id);
//	    
//	    
//		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(Donations);
//		ps.setInt(1, donations.getDonor_id());
//		ps.setInt(2,donations.getDonations_id());
//		ps.setString(3,donations. getDonation_date());
//		ps.setString(4, donations.getAddresss());
//		ps.setInt(5, donations.getB_id());
//		ps.executeUpdate();		
//		System.out.println("You have succesfully registered ");
//		}
//		else
//		{
//			System.out.println("ID Already exits. Please use a different ID");
//		}
//		
//	}
// 	
// 	
//
//}
