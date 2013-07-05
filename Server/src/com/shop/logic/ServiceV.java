package com.shop.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.shop.exceptions.ArtikelNotFoundException;
import com.shop.exceptions.ArtikelexistsException;
import com.shop.exceptions.BestandZuKleinException;
import com.shop.exceptions.CustomerExistsExeption;
import com.shop.exceptions.PersonNotFoundException;
import com.shop.valueobjects.Artikel;
import com.shop.valueobjects.Ereignis;
import com.shop.valueobjects.Kunde;
import com.shop.valueobjects.Mitarbeiter;
import com.shop.valueobjects.Person;
import com.shop.valueobjects.Rechnung;

import de.hsb.simon.commons.ServiceVInterface;

/**
 * Klasse zur Verwaltung eines (sehr einfachen) Shop.
 * Bietet Methoden zum Zur��ckgeben/Suche/Einf��gen/Speichern von Artikeln
 * @author hallakoglu
 * @version 0
 */
public class ServiceV implements ServiceVInterface {
	
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
	
	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#insertArtikel(java.lang.String, int, double)
	 */
	@Override
	public void insertArtikel(String title, int bestand, double price) throws ArtikelexistsException {
		
		Artikel a = this.artikelV.insertArtikel(title, bestand, price);
		this.ereignisV.create(bestand, this.person, a, "Neuer Artikel");
	}
	
	// Artikel mit festgelegter St��ckzahl
	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#insertArtikel(int, java.lang.String, int, double, int)
	 */
	@Override
	public void insertArtikel(int nr, String title, int bestand, double price, int mengeneinheit) throws ArtikelexistsException {
	}
	
	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#getAllArtikel()
	 */
	@Override
	public List<Artikel> getAllArtikel() {
		return artikelV.getAllArtikel(); //shop.getAllArtikel();
	}
	
	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#sucheNachTitel(java.lang.String)
	 */
	@Override
	public List<Artikel> sucheNachTitel(String titel) {
		// einfach delegieren an meineBuecher
		return null;//meineBuecher.sucheBuecher(titel); 
	}

	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#savedata()
	 */
	@Override
	public void savedata() {
		this.artikelV.saveArtikel();
		this.personV.savePerson();
	}
	
	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#saveArtikel()
	 */
	@Override
	public void saveArtikel() {
		this.artikelV.saveArtikel();
	}

	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#findArtikelByString(java.lang.String)
	 */
	@Override
	public List<Artikel> findArtikelByString(String s) {
		return artikelV.findArtikelByString(s);
	}

	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#raiseStock(int, int)
	 */
	@Override
	public boolean raiseStock(int nr, int stock) {
		Artikel a = this.artikelV.findArtikelByString(nr).get(0);
		this.ereignisV.create(stock, this.person, a, "Bestand erh��ht");
		return artikelV.raiseStock(nr, stock);
	}
	
	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#insertCustomer(com.shop.valueobjects.Kunde)
	 */
	@Override
	public void insertCustomer(Kunde k) throws CustomerExistsExeption {
		this.personV.insertCustomer(k);
	}
	
	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#login(com.shop.valueobjects.Person)
	 */
	@Override
	public Person login(Person p) throws PersonNotFoundException {
		return this.personV.login(p);
	}
	
	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#addArtikel(com.shop.valueobjects.Artikel, int)
	 */
	@Override
	public void addArtikel(Artikel a, int count) throws BestandZuKleinException {
		this.cart.addArtikel(a, count);
	}
	
	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#removeArtikelFromCart(com.shop.valueobjects.Artikel)
	 */
	@Override
	public void removeArtikelFromCart(Artikel a) {
		this.cart.removeArtikel(a);
	}

	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#getAllArtikelFromCart()
	 */
	@Override
	public Map<Artikel, Number> getAllArtikelFromCart() {
		return this.cart.getAllArtikel();
	}
	
	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#buy(com.shop.valueobjects.Kunde)
	 */
	@Override
	public Rechnung buy(Kunde k) {
		return this.cart.buy(k);
	}
	
	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#complete()
	 */
	@Override
	public void complete() throws ArtikelNotFoundException {
		this.artikelV.reduceStock(this.cart.getAllArtikel());
		this.ereignisV.create(person, this.cart.getAllArtikel(), "Bestand reduziert");
		this.cart.complete();
		this.savedata();
	}

	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#setPerson(com.shop.valueobjects.Person)
	 */
	@Override
	public void setPerson(Person person) {
		// TODO Auto-generated method stub
		this.person = person;
	}
	
	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#getAllMitarbeiter()
	 */
	@Override
	public ArrayList<Person> getAllMitarbeiter() {
		return this.personV.getPersonStock();
	}

	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#insertMitarbeiter(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void insertMitarbeiter(String name, String mail, String password) {
		this.personV.insertMitarbeiter(new Mitarbeiter(mail, name, password));
	}
	
	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#getAllEreignisse()
	 */
	@Override
	public ArrayList<Ereignis> getAllEreignisse() {
		return this.ereignisV.getAllEreignisse();
	}
	
	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#getPerson()
	 */
	@Override
	public Person getPerson() {
		return this.person;
	}

	@Override
	public void updateArtikel(int nr, String title, int bestand, double price,
			int menegeneinheit) throws ArtikelNotFoundException {
		this.artikelV.updateArtikel(nr, title, bestand, price, menegeneinheit);
		this.ereignisV.create(bestand, this.person, new Artikel(title, bestand, price), "Artikel aktualisiert");
	}
	
}
