package com.shop.logic;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.shop.exceptions.ArtikelNotFoundException;
import com.shop.exceptions.ArtikelexistsException;
import com.shop.exceptions.BestandZuKleinException;
import com.shop.exceptions.CustomerExistsExeption;
import com.shop.exceptions.PersonNotFoundException;
import com.shop.valueobjects.Artikel;
import com.shop.valueobjects.Kunde;
import com.shop.valueobjects.Person;
import com.shop.valueobjects.Rechnung;

/**
 * Klasse zur Verwaltung eines (sehr einfachen) Shop.
 * Bietet Methoden zum Zurückgeben/Suche/Einfügen/Speichern von Artikeln
 * @author hallakoglu
 * @version 0
 */
public class ServiceV {
	
	private String file = "";
	private ArtikelV artikelV;
	private PersonV personV;
	private WarenkorbV cart;
	private EreignisV ereignisV;
	private Person person;
	
	public ServiceV(String file) throws IOException {
		this.file = file;
		this.artikelV = new ArtikelV(file + "_Artikel.xml");
		this.personV = new PersonV(file + "_Person.xml");
		this.cart = new WarenkorbV(); 
		this.ereignisV = new EreignisV(file + "_Ereignis.xml");
	}
	
	public void insertArtikel(String title, int bestand, double price) throws ArtikelexistsException {
		
		Artikel a = this.artikelV.insertArtikel(title, bestand, price);
		this.ereignisV.create(bestand, this.person, a, "Neuer Artikel");
	}
	
	// Artikel mit festgelegter Stückzahl
	public void insertArtikel(int nr, String title, int bestand, double price, int mengeneinheit) throws ArtikelexistsException {
	}
	
	public List<Artikel> getAllArtikel() {
		return artikelV.getAllArtikel(); //shop.getAllArtikel();
	}
	
	public List<Artikel> sucheNachTitel(String titel) {
		// einfach delegieren an meineBuecher
		return null;//meineBuecher.sucheBuecher(titel); 
	}

	public void saveArtikel() {
		this.artikelV.saveArtikel();
	}

	public List<Artikel> findArtikelByString(String s) {
		return artikelV.findArtikelByString(s);
	}

	public boolean raiseStock(int nr, int stock) {
		Artikel a = this.artikelV.findArtikelByString(nr).get(0);
		this.ereignisV.create(stock, this.person, a, "Bestand erhöht");
		return artikelV.raiseStock(nr, stock);
	}
	
	public void insertCustomer(Kunde k) throws CustomerExistsExeption {
		this.personV.insertCustomer(k);
	}
	
	public Person login(Person p) throws PersonNotFoundException {
		return this.personV.login(p);
	}
	
	public void addArtikel(Artikel a, int count) throws BestandZuKleinException {
		this.cart.addArtikel(a, count);
	}
	
	public void removeArtikelFromCart(Artikel a) {
		this.cart.removeArtikel(a);
	}

	public Map<Artikel, Number> getAllArtikelFromCart() {
		return this.cart.getAllArtikel();
	}
	
	public Rechnung buy(Kunde k) {
		return this.cart.buy(k);
	}
	
	public void complete() throws ArtikelNotFoundException {
		this.artikelV.reduceStock(this.cart.getAllArtikel());
		this.ereignisV.create(person, this.cart.getAllArtikel(), "Bestand reduziert");
		this.cart.complete();
	}

	public void setPerson(Person person) {
		// TODO Auto-generated method stub
		this.person = person;
	}
}
