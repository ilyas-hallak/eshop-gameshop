package de.hsb.simon.client.net;

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

import de.hsb.simon.commons.ServerInterface;
import de.hsb.simon.commons.ServiceVInterface;

/**
 * @description Klasse mit Methodenaufrufe um Daten vom Server auszulesen oder neu einzuspielen
 * @description verweist auf das ServiceVInterface
 */
public class ServiceVInterfaceImpl implements ServiceVInterface {

	private ServerInterface server;
	
	/**
	 * @description Konstruktor der Klasse ServiceVInterfaceImpl
	 * @param server - Variable fuer die Verbindung mit dem ServerInterface, um die Daten von dort abzurufen
	 */
	public ServiceVInterfaceImpl(ServerInterface server) {
		this.server = server;
	}


	@Override
	public List<Artikel> getAllArtikel() {
		return this.server.getAllArtikel();
	}

	@Override
	public void savedata() {
		this.server.savedata();
	}

	@Override
	public void saveArtikel() {
		this.server.saveArtikel();
	}

	@Override
	public List<Artikel> findArtikelByString(String s) {
		return this.server.findArtikelByString(s);
	}


	@Override
	public void insertCustomer(Kunde k) throws CustomerExistsExeption {
		this.server.insertCustomer(k);
	}

	@Override
	public Person login(Person p) throws PersonNotFoundException {
		return this.server.login(p);
	}


	@Override
	public Rechnung buy(Kunde k) {
		return this.server.buy(k);
	}


	@Override
	public ArrayList<Person> getAllMitarbeiter() {
		return this.server.getAllMitarbeiter();
	}

	@Override
	public void insertMitarbeiter(String name, String mail, String password) {
		this.server.insertMitarbeiter(name, mail, password);
	}

	@Override
	public ArrayList<Ereignis> getAllEreignisse() {
		return this.server.getAllEreignisse();
	}

	@Override
	public void insertArtikel(int nr, String title, int bestand, double price,
			int mengeneinheit, Mitarbeiter m) throws ArtikelexistsException {
		this.server.insertArtikel(nr, title, bestand, price, mengeneinheit, m);
	}

	@Override
	public void updateArtikel(int nr, String title, int bestand, double price,
			int menegeneinheit, Mitarbeiter m) throws ArtikelNotFoundException {
		this.server.updateArtikel(nr, title, bestand, price, menegeneinheit, m);
	}

	@Override
	public boolean raiseStock(int nr, int stock, Mitarbeiter m) {
		return this.server.raiseStock(nr, stock, m);
	}

	@Override
	public Kunde addArtikelToCart(Artikel a, int count, Kunde k)
			throws BestandZuKleinException, MassenArtikelAnzahlException {
		return this.server.addArtikelToCart(a, count, k);
	}

	@Override
	public Kunde removeArtikelFromCart(Artikel a, Kunde k) {
		return this.server.removeArtikelFromCart(a, k);
	}


	@Override
	public void insertArtikel(String title, int bestand, double price,
			Mitarbeiter m) throws ArtikelexistsException {
		this.server.insertArtikel(title, bestand, price, m);
	}


	@Override
	public Map<Artikel, Number> getAllArtikelFromCart(Kunde k) {
		return this.server.getAllArtikelFromCart(k);
	}


	@Override
	public void complete(Kunde k) throws ArtikelNotFoundException {
		this.server.complete(k);
	}

}
