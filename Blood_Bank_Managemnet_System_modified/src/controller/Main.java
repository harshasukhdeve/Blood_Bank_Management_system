package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Scanner;

import dao.Admindao;

import dao.Donordao;
import dao.FeedbackDAO;
import dao.Hospitaldao;
import dao.ReferalDAO;
import model.AdminRegistration;
import model.Donations;
import model.Donor;
import model.Hospital;
import model.HospitalLogin;
import services.HospitalLoginSuccess;

import services.Validate;

public class Main {

	public static void main(String[] args) throws Exception {
		
		//object of classes
		String yes = null;
		Donations donations=new Donations();
		Donor donor = new Donor();
		Donordao donordao = new Donordao();
		ReferalDAO referaldao = new ReferalDAO();
		FeedbackDAO feedbackdao = new FeedbackDAO();
		Hospitaldao hospitaldao=new Hospitaldao();
		Hospital hospital=new Hospital();
		Validate adminValidation= new Validate();
		HospitalLogin hospitallogin = new HospitalLogin();
		Admindao admindao=new Admindao();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc= new Scanner(System.in);
		
		//Enter in the program
		do {
	
		 System.out.println("\t" +"\t"+"WELCOME TO BLOOD BANK:");
		    System.out.println("\t"+"-----------------**----------------------");
			System.out.println("\t" +"\t" +"1. Admin");
			System.out.println("\t" +"\t" +"2. Donor");
			System.out.println("\t" +"\t" +"3. Hospital");
			System.out.println("\t" +"\t" +"4. Exit");
			System.out.print("Please enter your choice:");
		
			int choice = 0;
			try {
			choice = Integer.parseInt(br.readLine());
			}
			catch (Exception e) {
				System.out.println("Please enter correct input");
			 choice = Integer.parseInt(br.readLine());
			}
			
		//Admin function entry 	
		switch(choice)
		{
		case 1:
			
			 System.out.println("*---------------------Existing user or signup?--------------------------*");
	         System.out.println("\t" +"\t" +"\t" +"\t" +"1.Signup");
	         System.out.println("\t" +"\t" +"\t" +"\t" +"2. Login");
	         System.out.println("*------------------------------------------------------------------------*");
	         int loginresponseAdmin= sc.nextInt();
	         
	         //New registration for admin
	         if(loginresponseAdmin==1) {
	        	AdminRegistration newAdmin=new AdminRegistration();
	            System.out.println("Enter Name :");
	        	String username= br.readLine();
	        	newAdmin.setUsername(username);
	        	System.out.println("Enter email :");
	        	String email= br.readLine();
	        	newAdmin.setEmail(email);
	        	System.out.println("Enter password which must have a capital letter,a special character :");
	        	String password= br.readLine();
	        	newAdmin.setPassword(password);
	        	System.out.println("Enter phonenumber :");
	        	String mobilenumber= br.readLine();
	        	newAdmin.setMobilenumber(mobilenumber);
	        	System.out.println("Enter uniqueid :");
	        	String uniqueid= br.readLine();
	        	newAdmin.setUniqueid(uniqueid);
	        	
	        	//validation of entered details 
	        	boolean check=adminValidation.signupvalidation(username, email, password,mobilenumber,uniqueid);
	        	  System.out.println("*------------------------------------------------------------------------*");
	        	if(check==true) {
	        		System.out.println("Admin Registration Completed");
	        		Admindao newAdmindao= new Admindao();
		        	newAdmindao.signup(newAdmin);
	        	}
        	else {
	        		System.out.println("Plese check Inputs ");
	            }
	        }
	       
	         //Admin login
	         else if(loginresponseAdmin==2)
			        {
	        		 AdminRegistration existingUser= new AdminRegistration();
			        	System.out.println("Enter email :");
			        	String emailUser= br.readLine();
			        	System.out.println("Enter password :");
			        	String passwordUser= br.readLine();
			        	existingUser.setEmail(emailUser);
			        	existingUser.setPassword(passwordUser);
			        	Admindao existingUserdao= new Admindao();
			        	boolean checkUser=existingUserdao.loginUser(existingUser);
			        	  System.out.println("*------------------------------------------------------------------------*");
			        	if(checkUser==true) {
			        	System.out.println("\t" +"\t" +"\t" +"\t"+"Successful login");
			        	  System.out.println("*------------------------------------------------------------------------*");
			        	
			        //Admin accessible features
			          do {
			        
			        		System.out.println("\t" +"1.View Donors List" + "\t" + "\t" +"\t" +"\t" +"\t"+"\t"+ "2.View Hospitals List");
			  				System.out.println("");
			  				System.out.println("\t" +"3.Update Donors details"  + "\t" + "\t" + "\t" +"\t" +"\t" +"\t"+ "4.Update Hospitals details" );
			  				System.out.println("");
			  				System.out.println("\t" +"5.Delete Donor Profile" + "\t" + "\t" + "\t"+"\t" +"\t" +"\t"+ "6.Delete Hospital Profile" );
			  				System.out.println("");			  		
			  				System.out.println("\t" +"7.Check Referrals for Blood Donations" + "\t" + "\t"+ "\t"+"\t" +" 8.Check Feedback of Donor"  );
			  				System.out.println("");
			  				//System.out.println("\t" +"9.Change admin account password" + "\t" + "\t"+ "\t"+" 10.Home"  );
			  				System.out.println("\t" +"\t" +"\t" +"\t" +"\t" +"\t" +"\t"+"9. Home" + "\t" + "\t" + "\t" );
			  				System.out.println("");
			  				System.out.println("\t" +"\t" +"\t" +"PLEASE ENTER YOUR CHOICE NOW:");

			        	     int opt =0;
								try {
							       opt = Integer.parseInt(br.readLine());
								    }
								catch(Exception E) {
									System.out.println("Please Enter correct input");
									opt = Integer.parseInt(br.readLine());
									}
								
					  switch(opt) {
					  case 1:
							donordao.DonorsList();
							break;
						case 2:
							hospitaldao.Hospitalslist();
							break;
						case 3:
							donordao.updateDonor(donor);
							break;
						case 4:
							hospitaldao.UpdateHospital(hospital);
							break;
						case 5:
							System.out.println("Enter ID of the profile to Delete :");
							int donorid = Integer.parseInt(br.readLine());
							donordao.deleteDonor(donorid);
							break;
						case 6:
							System.out.println("Enter the id related to the hospital to delete the Hospital Profile :");
							int id = Integer.parseInt(br.readLine());
							hospitaldao.deletehospital(id);
							break;
						case 7:
							referaldao.seereferals();
							break;
						case 8:
							feedbackdao.checkfeedback();
							break;
						case 9:	
							System.out.println("Home");
							Main m = new Main();
							m.main(args);
							break;
					
						default:  System.out.println("You entered wrong option");
								
								 }
								System.out.println("Do you want to continue as Admin:");
								 yes=br.readLine();
			        	  } while(yes.equalsIgnoreCase("yes"));
			          }
			        	
			    	else {
			        		System.out.println("Invalid credentials");
			        	 }
		}
	    break;
	    // end of the admin
	         
	         //donor  function entry
		case 2:
			do {
				System.out.println("");
				System.out.println("***********GO THROUGH BELOW OPTION***********");
				System.out.println("\t" +"1.Register as Donor"+ "\t" + "\t" + "\t"+ "\t"+"2.Update your Profile Details");
				System.out.println("");
				System.out.println("\t" +"3.Delete your Profile"+ "\t" + "\t" + "\t"+ "\t"+"4.Refer your friends");
				System.out.println("\t" +"");
				System.out.println("\t" +"5.Give your Feedback"+ "\t" + "\t"+"\t"+ "\t"+  "6.Donate Blood");
				System.out.println("\t" +"");
				System.out.println("\t" +"7.View Camp Details"+ "\t" + "\t"+"\t"+ "\t"+  "8.View donors list");
				System.out.println("");
				System.out.println("\t"+"\t"+"\t"+"\t"+"\t"+"9.Home"+ "\t" + "\t");
				System.out.println("");
				System.out.println("\t" +"\t" +"\t" +"PLEASE ENTER YOUR CHOICE NOW:");
				
				int options = 0;
				try{
					options = Integer.parseInt(br.readLine());
				}
				catch(Exception e){
					System.out.println("Please enter correct input");
					options = Integer.parseInt(br.readLine());
				}
				
				//entry after login
				switch(options) {
				case 1:
					donordao.addDonor(donor);
					break;
				case 2:
					donordao.updateDonor(donor);
					break;
				case 3:
					System.out.println("Enter donor_id which you wnat to delete :");
					int donorid = Integer.parseInt(br.readLine());
					donordao.deleteDonor(donorid);
					break;
				case 4:
					referaldao.referrals();
					break;
				case 5:
					System.out.println("Please write your Feedback");
					feedbackdao.addfeedback();
					break;
				case 6:
					donordao.addDonationDetails(donations);
					break;
				case 7:
					System.out.println("Details are as below");
					System.out.println("");
					hospitaldao.viewCampDetails();
					break;
				case 8: 
					donordao.DonorsList();
					break;
				case 9:
					System.out.println("Home");
					Main m = new Main();
					m.main(args);
					break;
				default:  System.out.println("You entered wrong option");
				}
			  System.out.println("Do you want to continue as user? yes/no :");
			 yes=br.readLine();
			}while(yes.equalsIgnoreCase("yes"));
		break;
		//end of user 
		
		//Hospital Managemnt entry
		case 3:
			do {
				System.out.println("");
				System.out.println("********************HOSPITAL**************");
				System.out.println("1. Register your hospital");
				System.out.println("2. Login");
				System.out.println("3. Home");
				System.out.print("Please enter an option :");
				System.out.print("");
				int option = 0;
				
				try{
					option = Integer.parseInt(br.readLine());
				} 
				catch (Exception e) {
					System.out.println("Please enter correct input:");
					option = Integer.parseInt(br.readLine());
				}
				
				switch(option) {
			//register as new hospital
				case 1:
//					System.out.println("Enter ID of your Hospital");
//					int hospital_id = Integer.parseInt(br.readLine());
//					if(hospitaldao.checkid(hospital_id)==false) {
					System.out.println("Enter name of the Hospital :");
					String hospital_name = br.readLine();
					System.out.println("Enter hospital Phone-number :");
					long phone_num = Long.parseLong(br.readLine()); 
					System.out.println("Enter Address of the Hospital :");
					String address = br.readLine();
					System.out.println("Enter your username :");
					String username = br.readLine();
					System.out.println("Enter your email address :");
					String email1 = br.readLine();
					System.out.println("Enter your password :");
					String password1 = br.readLine();
					
//					hospital.setHospital_id(hospital_id);
					hospital.setHospital_name(hospital_name);
					hospital.setPhone_no(phone_num);
					hospital.setAddress(address);
					hospital.setUsername(username);
					hospital.setEmail(email1);
					hospital.setPassword(password1);
				//check validation
					boolean check=adminValidation.Hospitalvalidation(email1,password1);
		        	  System.out.println("*------------------------------------------------------------------------*");
		        	if(check==true) {
		        		System.out.println("Register Sucessfully");
		        		hospitaldao.addHospital(hospital);
		        	}
	        	else {
		        		System.out.println("Please check input ");
		         }
//					}
//					
//					else
//					{
//						System.out.println("ID Already exists. Please try using a different ID");
//					}
					break;
					//login as hospital 
				case 2:
				System.out.println("");
				System.out.println("Enter your email address :");
				String emailid = br.readLine();
				System.out.println("Enter your password :");
				String password = br.readLine();
				 hospitallogin.setEmailid(emailid);
				 hospitallogin.setPassword(password);
				 if(hospitaldao.hoslogin(hospitallogin)==true)
				 {
					//option agter login
					 HospitalLoginSuccess hospitalloginsuccess = new HospitalLoginSuccess();
						hospitalloginsuccess.success();
				 }
				 
				 else {
					 System.out.println("Please check your details");
				 }
				 break;	
				case 3:
					System.out.println("Home");
					Main m = new Main();
					m.main(args);
					break;
			     default:  System.out.println("You entered wrong option");
				}
				
				System.out.println("Do you want to continue? yes/no :");
				yes=br.readLine();
			}while(yes.equalsIgnoreCase("yes"));
			break;
			//end of hospital
			
		case 4:
			System.out.println("Exit");
			break;
			default:
			}
		System.out.println("Do you want to continue as general? yes/no :");
	  yes=br.readLine();

	}while(yes.equalsIgnoreCase("yes"));

}
}
