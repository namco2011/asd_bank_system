package banking;

import java.util.Date;

public class Customer {
	private String name;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String AccountNumber;
	private String emailAddress;


	private Date Birthday;
	private int noOfEmployee;
	private Date expirationDate;




	public Customer(String AccountNumber, String name, String emailAddress, String street, String city, String state, String zip) {
		this.AccountNumber = AccountNumber;
		this.name = name;
		this.emailAddress = emailAddress;
		this.street = street;
		this.city= city;
		this.state = state;
		this.zip = zip;


	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public String getAccountNumber() {
		return AccountNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}

	public void setNoOfEmployee(int noOfEmployee) {
		this.noOfEmployee = noOfEmployee;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

}
