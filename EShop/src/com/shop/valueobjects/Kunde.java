package com.shop.valueobjects;

public class Kunde extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2345625329911573006L;
	private String address;

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	// serialize default constructor
	public Kunde() {
	}
		
	public void setEMail(String eMail) {
		this.eMail = eMail;
	}
	
	public Kunde(String name, String eMail, String address, String pw) {
		super(eMail, pw);
		this.name = name;
		this.address = address;
		this.password = pw;
	}



}
