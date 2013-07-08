package com.shop.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.shop.exceptions.ArtikelNotFoundException;
import com.shop.exceptions.ArtikelexistsException;
import com.shop.exceptions.BestandZuKleinException;
import com.shop.exceptions.CustomerExistsExeption;
import com.shop.exceptions.MassenArtikelAnzahlException;
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
	public void insertArtikel(String title, int bestand, double price, Mitarbeiter m) throws ArtikelexistsException {
		Artikel a = this.artikelV.insertArtikel(title, bestand, price);
		this.ereignisV.create(bestand, m, a, "Neuer Artikel");
	}
	
	// Artikel mit festgelegter Stückzahl
	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#insertArtikel(int, java.lang.String, int, double, int)
	 */
	@Override
	public void insertArtikel(int nr, String title, int bestand, double price, int mengeneinheit, Mitarbeiter m) throws ArtikelexistsException {
		Artikel a = this.artikelV.insertArtikel(title, bestand, price, mengeneinheit);
		this.ereignisV.create(bestand, m, a, "Neuer Massenartikel Artikel");
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
	public boolean raiseStock(int nr, int stock, Mitarbeiter m) {
		Artikel a = this.artikelV.findArtikelByString(nr).get(0);
		this.ereignisV.create(stock, m, a, "Bestand erhöht");
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
	public Kunde addArtikelToCart(Artikel a, int count, Kunde k) throws BestandZuKleinException, MassenArtikelAnzahlException {
		//this.cart.addArtikel(a, count);
		Artikel tmpArtikel = this.artikelV.findArtikelByString(a.getNr()).get(0);
		if(tmpArtikel.getStock() < count) {
			throw new BestandZuKleinException(tmpArtikel);
		}
		k.getCart().addArtikel(a, count);
		return k;
	}
	
	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#removeArtikelFromCart(com.shop.valueobjects.Artikel)
	 */
	@Override
	public Kunde removeArtikelFromCart(Artikel a, Kunde k) {
		k.getCart().removeArtikel(a);
		return k;
	}

	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#getAllArtikelFromCart()
	 */
	@Override
	public Map<Artikel, Number> getAllArtikelFromCart(Kunde k) {
		return k.getCart().getAllArtikel();
	}
	
	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#buy(com.shop.valueobjects.Kunde)
	 */
	@Override
	public Rechnung buy(Kunde k) {
		try {
			this.artikelV.reduceStock(k.getCart().getAllArtikel());
		} catch (ArtikelNotFoundException e) {
		}
		this.ereignisV.create(k, k.getCart().getAllArtikel(), "Artikel wurde gekauft");

		return k.getCart().buy(k);
	}
	
	/* (non-Javadoc)
	 * @see com.shop.logic.ServiceVInterface#complete()
	 */
	@Override
	public void complete(Kunde k) throws ArtikelNotFoundException {
		this.artikelV.reduceStock(k.getCart().getAllArtikel());
		this.ereignisV.create(k, k.getCart().getAllArtikel(), "Bestand reduziert");
		k.getCart().complete();
		this.savedata();
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

	@Override
	public void updateArtikel(int nr, String title, int bestand, double price,
			int menegeneinheit, Mitarbeiter m) throws ArtikelNotFoundException {
		this.artikelV.updateArtikel(nr, title, bestand, price, menegeneinheit);
		this.ereignisV.create(bestand, m, new Artikel(title, bestand, price), "Artikel aktualisiert");
	}
	
}
