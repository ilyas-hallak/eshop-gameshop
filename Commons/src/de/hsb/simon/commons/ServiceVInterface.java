package de.hsb.simon.commons;

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

public interface ServiceVInterface {

	public abstract void insertArtikel(String title, int bestand, double price, Mitarbeiter m)
			throws ArtikelexistsException;

	// Artikel mit festgelegter St??ckzahl
	public abstract void insertArtikel(int nr, String title, int bestand,
			double price, int mengeneinheit, Mitarbeiter m) throws ArtikelexistsException;
	
	public abstract void updateArtikel(int nr, String title, int bestand, double price, int menegeneinheit, Mitarbeiter m) throws ArtikelNotFoundException;
	
	public abstract List<Artikel> getAllArtikel();

	public abstract List<Artikel> sucheNachTitel(String titel);

	public abstract void savedata();

	public abstract void saveArtikel();

	public abstract List<Artikel> findArtikelByString(String s);

	public abstract boolean raiseStock(int nr, int stock, Mitarbeiter m);

	public abstract void insertCustomer(Kunde k) throws CustomerExistsExeption;

	public abstract Person login(Person p) throws PersonNotFoundException;

	public abstract Kunde addArtikelToCart(Artikel a, int count, Kunde k)
			throws BestandZuKleinException, MassenArtikelAnzahlException ;

	public abstract Kunde removeArtikelFromCart(Artikel a, Kunde k);

	public abstract Map<Artikel, Number> getAllArtikelFromCart(Kunde k);

	public abstract Rechnung buy(Kunde k);

	public abstract void complete(Kunde k) throws ArtikelNotFoundException;

	public abstract ArrayList<Person> getAllMitarbeiter();

	public abstract void insertMitarbeiter(String name, String mail,
			String password);

	public abstract ArrayList<Ereignis> getAllEreignisse();


}