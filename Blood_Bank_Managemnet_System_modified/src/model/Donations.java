package model;

import java.time.LocalDate;

//import java.util.Date;

public class Donations {
   
    private int donor_id;
    private int donations_id;
	private String donation_date;
	private String addresss;
	private int b_id;
	
	
	
	public String getAddresss() {
		return addresss;
	}
	public void setAddresss(String addresss) {
		this.addresss = addresss;
	}
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	public int getDonations_id() {
		return donations_id;
	}
	public void setDonations_id(int donations_id) {
		this.donations_id = donations_id;
	}
	public int getDonor_id() {
		return donor_id;
	}
	public void setDonor_id(int donor_id) {
		this.donor_id = donor_id;
	}
	public String getDonation_date() {
		return donation_date;
	}
	public void setDonation_date(String donation_date) {
		this.donation_date = donation_date;
	}
	

}