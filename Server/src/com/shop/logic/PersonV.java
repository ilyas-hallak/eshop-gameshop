package com.shop.logic;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import com.shop.exceptions.CustomerExistsExeption;
import com.shop.exceptions.PersonNotFoundException;
import com.shop.persistence.FilePersistenceManager;
import com.shop.valueobjects.Kunde;
import com.shop.valueobjects.Mitarbeiter;
import com.shop.valueobjects.Person;

/**
 * @description Manager Klasse fuer Personen (Kunden, Mitarbeiter) 
 */
public class PersonV implements Serializable {
	
	private static final long serialVersionUID = 6716462328018957529L;

	private ArrayList<Person> personStock;
	private FilePersistenceManager pm;
	private String file;
	
	/**
	 * empty constructor for Serializable
	 */
	public PersonV() {}
	
	/**
	 * @description Erstellt den Persistenz Manager
	 * @param file - XML Datei Name
	 */
	public PersonV(String file) {
		this.personStock = new ArrayList<Person>();
		pm = new FilePersistenceManager(file);
		this.file = file;
		
		this.loadPersonen(file);
	}
	
	/**
	 * @description Allgemeine Login Methode fuer Personen
	 * @param p - Person Objekt
	 * @return Person Objekt (kann Kunde oder Mitarbeiter sein)
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
		throw new PersonNotFoundException(p);
	}

	/**
	 * @description Eintragen eines Kunden
	 * @param k - Kunden Objekt
	 * @throws CustomerExistsExeption
	 */
	public void insertCustomer(Kunde k) throws CustomerExistsExeption {
		if(!this.personStock.contains(k)) {
			this.personStock.add(k);
			this.savePerson();
		} else {
			throw new CustomerExistsExeption(k);
		}
	}
	
	/**
	 * @description Eintragen eines Mitarbeiter
	 * @param m - Mitarbeiter Objekt
	 */
	public void insertMitarbeiter(Mitarbeiter m) {
		m.setNr( this.personStock.size() + 1 );
		this.personStock.add(m);
	}
	
	/**
	 * @description Personen in XML speichern 
	 */
	public void savePerson() {
		try {
			pm.openForWriting();
			if(!personStock.isEmpty()) {
				pm.save(this.personStock);
			}
		} catch(IOException e) {
			// TODO fix error messages
			//e.printStackTrace();
		} finally {
			pm.close();
		}
	}

	/**
	 * @description Laden der Personen aus der XML Datei
	 * @param file XML Dateiname
	 */
	public void loadPersonen(String file) {
		try {
			pm.openForReading();
			ArrayList<Person> ps = (ArrayList<Person>) pm.read();
			if(ps != null) {
				this.personStock = ps;
			}
		} catch (Exception e) {
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
