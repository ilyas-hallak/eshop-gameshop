package com.shop.logic;

import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import com.shop.exceptions.CustomerExistsExeption;
import com.shop.exceptions.PersonNotFoundException;
import com.shop.persistence.FilePersistenceManager;
import com.shop.valueobjects.Kunde;
import com.shop.valueobjects.Person;

public class PersonV implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6716462328018957529L;

	private ArrayList<Person> personStock;
	private FilePersistenceManager pm;
	private String file;
	
	// empty constructor for Serializable
	public PersonV() {
		
	}
	
	
	public PersonV(String file) {
		this.personStock = new ArrayList<Person>();
		pm = new FilePersistenceManager(file);
		this.file = file;
	}
	
	/**
	 * 
	 * @param p
	 * @return 
	 * @throws PersonNotFoundException
	 */
	public Person login(Person p) throws PersonNotFoundException {		
		Iterator<Person> i = this.personStock.iterator();
		while(i.hasNext()) {
			Person person = i.next();
			if(p.geteMail().equals(person.geteMail() ) && p.getPassword().equals(person.getPassword())) {
				return person;
			}
		}
		throw new PersonNotFoundException(p.geteMail());
	}

	public void insertCustomer(Kunde k) throws CustomerExistsExeption {
		if(!this.personStock.contains(k)) {
			this.personStock.add(k);
			this.savePerson();
		} else {
			throw new CustomerExistsExeption(k.geteMail());
		}
	}
	
	public void savePerson() {
		try {
			pm.openForWriting();
			if(!personStock.isEmpty()) {
				pm.save(this);
			}
		} catch(IOException e) {
			// TODO fix error messages
			//e.printStackTrace();
		} finally {
			pm.close();
		}
	}

	public void loadPersonen(String file) {
		try {
			pm.openForReading();
			PersonV p = (PersonV)pm.read();
			if(p != null) {
				this.personStock = p.personStock;
			}
		} catch (Exception e) {
			// TODO fix error messages
			// System.out.println(e.getMessage());
			// e.printStackTrace();
		} finally {
			pm.close();
		}
	}

	/**
	 * @return the personStock
	 */
	public ArrayList<Person> getPersonStock() {
		return personStock;
	}

	/**
	 * @param personStock the personStock to set
	 */
	public void setPersonStock(ArrayList<Person> personStock) {
		this.personStock = personStock;
	}

	/**
	 * @return the pm
	 */
	public FilePersistenceManager getPm() {
		return pm;
	}

	/**
	 * @param pm the pm to set
	 */
	public void setPm(FilePersistenceManager pm) {
		this.pm = pm;
	}
	
}
