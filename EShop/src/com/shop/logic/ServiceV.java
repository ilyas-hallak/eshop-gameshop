package com.shop.logic;

import java.io.IOException;
import java.util.List;

import com.shop.exceptions.ArtikelexistsException;
import com.shop.exceptions.CustomerExistsExeption;
import com.shop.exceptions.PersonNotFoundException;

import com.shop.valueobjects.Artikel;
import com.shop.valueobjects.Kunde;
import com.shop.valueobjects.Person;

/**
 * Klasse zur Verwaltung eines (sehr einfachen) Shop.
 * Bietet Methoden zum Zurückgeben/Suche/Einfügen/Speichern von Artikeln,
 * @author hallakoglu
 * @version 0
 */
public class ServiceV {
	
	private String file = "";
	private ArtikelV artikelV;
	private PersonV personV;

	public ServiceV(String file) throws IOException {
		this.file = file;
		this.artikelV = new ArtikelV(file);
		this.artikelV.readArtikel(file);
		this.personV = new PersonV();
	}
	
	// Mitarbeiter
	
	public boolean insertArtikel(int nr, String title, int bestand) {
		try {
			Artikel a = new Artikel(nr, title, bestand);
			this.artikelV.insertArtikel(a);
		} catch (ArtikelexistsException e) {
			return false;
		}
		return true;
	}
	
	public List<Artikel> getAllArtikel() {
		return artikelV.getAllArtikel(); //shop.getAllArtikel();
	}
	
	public List<Artikel> sucheNachTitel(String titel) {
		// einfach delegieren an meineBuecher
		return null;//meineBuecher.sucheBuecher(titel); 
	}

	public void loadData() {
	}
	
	public void saveArtikel() {
		this.artikelV.saveArtikel();
	}

	public List<Artikel> findArtikelByString(String s) {
		return artikelV.findArtikelByString(s);
	}

	public boolean raiseStock(int nr, int stock) {
		return artikelV.raiseStock(nr, stock);
	}
	
	public void insertCustomer(Kunde k) throws CustomerExistsExeption {
		this.personV.insertCustomer(k);
	}
	
	public boolean login(Person p) throws PersonNotFoundException {
		return this.personV.login(p);
	}
}
