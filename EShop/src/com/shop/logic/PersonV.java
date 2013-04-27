package com.shop.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import com.shop.exceptions.CustomerExistsExeption;
import com.shop.exceptions.PersonNotFoundException;
import com.shop.valueobjects.Kunde;
import com.shop.valueobjects.Person;

public class PersonV implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6716462328018957529L;

	private ArrayList<Person> personStock;
	
	public PersonV() {
		
	}
	
	/**
	 * 
	 * @param p
	 * @return 
	 * @throws PersonNotFoundException
	 */
	public boolean login(Person p) throws PersonNotFoundException {
		if(!this.personStock.contains(p)) {
			throw new PersonNotFoundException(p.geteMail());
		} else {
			Iterator<Person> i = this.personStock.iterator();
			while(i.hasNext()) {
				Person person = i.next();
				if(p.geteMail() == person.geteMail() && p.getPassword() == person.getPassword()) {
					return true;
				}
			}
		}
		return false;
	}

	public void insertCustomer(Kunde k) throws CustomerExistsExeption {
		if(!this.personStock.contains(k)) {
			this.personStock.add(k);
		} else {
			throw new CustomerExistsExeption(k.geteMail());
		}
	}
	
}
