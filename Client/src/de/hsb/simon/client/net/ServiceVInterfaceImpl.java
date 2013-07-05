package de.hsb.simon.client.net;

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
import com.shop.valueobjects.Person;
import com.shop.valueobjects.Rechnung;

import de.hsb.simon.commons.ServerInterface;
import de.hsb.simon.commons.ServiceVInterface;

public class ServiceVInterfaceImpl implements ServiceVInterface {

	private ServerInterface server;
	
	public ServiceVInterfaceImpl(ServerInterface server) {
		this.server = server;
	}

	@Override
	public void insertArtikel(String title, int bestand, double price)
			throws ArtikelexistsException {
		this.server.insertArtikel(title, bestand, price);
	}

	@Override
	public void insertArtikel(int nr, String title, int bestand, double price,
			int mengeneinheit) throws ArtikelexistsException {
		this.server.insertArtikel(title, bestand, price);
	}

	@Override
	public List<Artikel> getAllArtikel() {
		// TODO Auto-generated method stub
		return this.server.getAllArtikel();
	}

	@Override
	public List<Artikel> sucheNachTitel(String titel) {
		// TODO Auto-generated method stub
		return this.server.sucheNachTitel(titel);
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
	public boolean raiseStock(int nr, int stock) {
		return this.server.raiseStock(nr, stock);
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
	public void addArtikel(Artikel a, int count) throws BestandZuKleinException {
		this.server.addArtikel(a, count);
	}

	@Override
	public void removeArtikelFromCart(Artikel a) {
		this.server.removeArtikelFromCart(a);
	}

	@Override
	public Map<Artikel, Number> getAllArtikelFromCart() {
		return this.server.getAllArtikelFromCart();
	}

	@Override
	public Rechnung buy(Kunde k) {
		return this.server.buy(k);
	}

	@Override
	public void complete() throws ArtikelNotFoundException {
		this.server.complete();
	}

	@Override
	public void setPerson(Person person) {
		// TODO create person array
		this.server.setPerson(person);
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
	public Person getPerson() {
		// TODO return from person array
		return this.server.getPerson();
	}

	@Override
	public void updateArtikel(int nr, String title, int bestand, double price,
			int menegeneinheit) throws ArtikelNotFoundException {
		this.server.updateArtikel(nr, title, bestand, price, menegeneinheit);
	}

}
