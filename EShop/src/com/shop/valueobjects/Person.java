package com.shop.valueobjects;

public class Person {

	protected int nr;
	protected String name;
	protected int password;
	protected int eMail;
	
	public Person (int eMail, int password) {
		this.eMail = eMail;
		this.password = password;
	}

	/**
	 * @return the nr
	 */
	public int getNr() {
		return nr;
	}

	/**
	 * @param nr the nr to set
	 */
	public void setNr(int nr) {
		this.nr = nr;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public int getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(int password) {
		this.password = password;
	}

	/**
	 * @return the eMail
	 */
	public int geteMail() {
		return eMail;
	}

	/**
	 * @param eMail the eMail to set
	 */
	public void seteMail(int eMail) {
		this.eMail = eMail;
	}
}
