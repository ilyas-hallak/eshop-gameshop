package de.hsb.simon.commons;

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

public interface ServiceVInterface {

	public abstract void insertArtikel(String title, int bestand, double price)
			throws ArtikelexistsException;

	// Artikel mit festgelegter St??ckzahl
	public abstract void insertArtikel(int nr, String title, int bestand,
			double price, int mengeneinheit) throws ArtikelexistsException;

	public abstract List<Artikel> getAllArtikel();

	public abstract List<Artikel> sucheNachTitel(String titel);

	public abstract void savedata();

	public abstract void saveArtikel();

	public abstract List<Artikel> findArtikelByString(String s);

	public abstract boolean raiseStock(int nr, int stock);

	public abstract void insertCustomer(Kunde k) throws CustomerExistsExeption;

	public abstract Person login(Person p) throws PersonNotFoundException;

	public abstract void addArtikel(Artikel a, int count)
			throws BestandZuKleinException;

	public abstract void removeArtikelFromCart(Artikel a);

	public abstract Map<Artikel, Number> getAllArtikelFromCart();

	public abstract Rechnung buy(Kunde k);

	public abstract void complete() throws ArtikelNotFoundException;

	public abstract void setPerson(Person person);

	public abstract ArrayList<Person> getAllMitarbeiter();

	public abstract void insertMitarbeiter(String name, String mail,
			String password);

	public abstract ArrayList<Ereignis> getAllEreignisse();

	public abstract Person getPerson();

}