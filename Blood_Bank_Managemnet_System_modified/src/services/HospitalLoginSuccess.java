package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.Main;


import dao.Donordao;
import dao.Hospitaldao;
import model.BloodDonationCamp;
//import model.Donations;
import model.Donor;
import model.Hospital;

public class HospitalLoginSuccess {
	
	Hospitaldao hospitaldao = new Hospitaldao();
	Hospital hospital = new Hospital();
	Donordao donordao = new Donordao();
	Donor donor = new Donor();
	BloodDonationCamp camp=new 	BloodDonationCamp();

	String yes;
	//HOSPITAL MANAGEMENT FEATURES
    public void success() throws SQLException, Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	do {
		System.out.println("Logged in sucessfully");
		System.out.println("");
		//visible options
		System.out.println("1. Update Hospital Details" + "\t" + "\t"+"\t"+ "2.Get All Blood Donors Information");
		System.out.println("");
		System.out.println("3. Delete Donor profile"+ "\t" + "\t" + "\t"+"\t"+"4.Update Donor Profile" );
		System.out.println("");
		System.out.println("5. Delete Hospital Profile"+ "\t" + "\t"+"\t"+ "6.Search for Blood Donors");
		System.out.println("");
		System.out.println("7.Arrange blood donation camp "+ "\t" + "\t"+"\t"+ "8.View Hospital list");
		System.out.println("");
	//	System.out.println("\t" + "\t" + "\t"+ "\t"+"9.Home"+ "\t" + "\t" + "\t"+ "\t");
		System.out.println("9.View donor list "+ "\t" + "\t"+"\t"+ "8.View Hospital list");
		System.out.println("");
		System.out.println("\t" +"\t" +"PLEASE ENTER YOUR CHOICE NOW:");
		int choice = 0;

			try {
		choice = Integer.parseInt(br.readLine());
			}
			catch(Exception e){
				System.out.println("Please enter correct input:");
				choice = Integer.parseInt(br.readLine());
			}
			
			//cases as per choice 
		switch(choice) {
		case 1:
		hospitaldao.UpdateHospital(hospital);

		break;
		case 2:
			donordao.DonorsList();
			break;
		case 3:
			System.out.println("Enter donor_id which you wnat to delete :");
			int donorid = Integer.parseInt(br.readLine());
			donordao.deleteDonor(donorid);
			break;
		case 4:
			donordao.updateDonor(donor);
				break;
		case 5:
		System.out.println("Enter the id related to your hospital to delete your Hospital Profile :");
			int id1= Integer.parseInt(br.readLine());
			hospitaldao.deletehospital(id1);
			break;
		case 6:
			System.out.println("Please enter the ID related to the Blood group you want to search for:");
			System.out.println("O+ - 17");
			System.out.println("A+ - 18");
			System.out.println("A- - 19");
			System.out.println("B- - 20");
			System.out.println("AB+ - 22");
			int blood_id = Integer.parseInt(br.readLine());
			donordao.searchdonorbybloodgroupid(blood_id);
		   break;
		case 7:
			hospitaldao.donationcamp(camp);
			break;
		case 8:
			hospitaldao.Hospitalslist();
			break;
		case 9:
			System.out.println("Home");
			Main m = new Main();
			String[] args = null;
			m.main(args);
		default:  System.out.println("You entered wrong option");
		}
		System.out.println("Do you want to continue with other options? yes/no : ");
		yes = br.readLine();
	    }while(yes.equalsIgnoreCase("yes"));

}	
		
}

