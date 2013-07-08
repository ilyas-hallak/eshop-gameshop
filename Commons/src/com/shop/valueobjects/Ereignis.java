package com.shop.valueobjects;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @description Klasse Ereignis - enthaelt die Werte eines Ereignisses mit getter und setter
 */
public class Ereignis implements Serializable {
	
	//ATTRIBUTE der Klasse
	private static final long serialVersionUID = -3761434968520023786L;
	private Date date;
	private int count;
	private Person person;
	private Artikel article;
	private String message;
	
	// leerer Konstruktor fuer die Persistenz
	public Ereignis() {}
	
	/**
	 * @description Konstruktor zum erzeugen eines Ereignisses
	 * @param count - anzahl des artikels
	 * @param p - Die Person die eingeloggt ist und das Ereignis erzeugt
	 * @param a - der betroffende Artikel
	 * @param message - Beschriftung welche Art von Ereignis passiert ist
	 */
	public Ereignis(int count, Person p, Artikel a, String message) {
		this.date = new Date();
		this.count = count;
		this.article = a;
		this.person = p;
		this.message = message;
	}

	/**
	 * @return the date 
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * @return the article
	 */
	public Artikel getArticle() {
		return article;
	}

	/**
	 * @param article the article to set
	 */
	public void setArticle(Artikel article) {
		this.article = article;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
