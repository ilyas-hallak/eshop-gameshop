package com.shop.logic;

import java.io.IOException;
import java.io.Serializable;

import com.shop.persistence.FilePersistenceManager;
import com.shop.valueobjects.Artikel;
import com.shop.valueobjects.Ereignis;
import com.shop.valueobjects.Person;

public class EreignisV implements Serializable {

	private static final long serialVersionUID = 6451799215728455338L;
	private FilePersistenceManager pm;
	private Ereignis ereignis; 
	
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

	/**
	 * @return the ereignis
	 */
	public Ereignis getEreignis() {
		return ereignis;
	}

	/**
	 * @param ereignis the ereignis to set
	 */
	public void setEreignis(Ereignis ereignis) {
		this.ereignis = ereignis;
	}

	public EreignisV(){}
	
	public EreignisV(String file) {
		this.pm = new FilePersistenceManager(file);
	}
	
	public void create(int count, Person p, Artikel a, String msg) {
		try {
			this.ereignis = new Ereignis(count, p, a, msg);
			pm.save(this);
		} finally {
			pm.close();
		}
	}
}
