package com.shop.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.shop.exceptions.ArtikelNotFoundException;
import com.shop.exceptions.ArtikelexistsException;
import com.shop.persistence.FilePersistenceManager;
import com.shop.valueobjects.Artikel;
import com.shop.valueobjects.MassenArtikel;

/**
 * @description manager class for articles 
 */
public class ArtikelV {

	/**
	 * Persistenz-Schnittstelle, die fuer die Details des Dateizugriffs
	 * verantwortlich ist
	 */
	private FilePersistenceManager pm;

	/**
	 * Verwaltung des Artikelbestands
	 */
	private ArrayList<Artikel> artikelStock;

	/**
	 * init the file manager and the article stock
	 * 
	 * @param file
	 *            filename for the file manager
	 */
	public ArtikelV(String file) {
		this.artikelStock = new ArrayList<Artikel>();
		pm = new FilePersistenceManager(file);
		this.readArtikel();
	}

	/**
	 * Adds an article to the article stock
	 * 
	 * @param nr
	 *            article nr
	 * @param title
	 *            article title
	 * @param bestand
	 *            article stock
	 * @throws ArtikelexistsException
	 */
	public Artikel insertArtikel(String title, int bestand, double price)
			throws ArtikelexistsException {
		// ueberpruefen ob der titel schon im Artikelbestand vorhanden ist
		Iterator<Artikel> it = this.artikelStock.iterator();
		while (it.hasNext()) {
			Artikel article = (Artikel) it.next();
			if (article.getTitle() == title) {
				// wenn er bereits vorhanden ist die Exception schmeissen
				throw new ArtikelexistsException(article);
			}
			// wenn er nicht vorhanden ist Artikel-Objekt erzeugen und
			// hinzufuegen
			else {
				// create article nr: get last id from stock and increment
				int nr = this.artikelStock.get(artikelStock.size() - 1).getNr() + 1;
				
				Artikel a = new Artikel(nr, title, bestand, price);
				this.artikelStock.add(a);
				return a;
			}
		}
		return null;
	}
	
	/**
	 * @description Erzeugt ein Massengut Artikel
	 * @param title - Bezeichnung des Artikels
	 * @param bestand - Bestandhoehe
	 * @param price - Preis
	 * @param mengeneinheit Einheit des Massengut Artikel
	 * @return - Gibt den angelegten Artikel zurueck
	 * @throws ArtikelexistsException
	 */
	public Artikel insertArtikel(String title, int bestand, double price,
			int mengeneinheit) throws ArtikelexistsException {
		Iterator<Artikel> it = this.artikelStock.iterator();
		while (it.hasNext()) {
			Artikel article = (Artikel) it.next();
			if (article.getTitle() == title) {
				// wenn er bereits vorhanden ist die Exception schmeissen
				throw new ArtikelexistsException(article);
			}
			// wenn er nicht vorhanden ist Artikel-Objekt erzeugen und
			// hinzufuegen
			else {
				Artikel a = new MassenArtikel(this.artikelStock.size() + 1, title,
						bestand, price, mengeneinheit);
				this.artikelStock.add(a);
				return a;
			}
		}
		return null;
	}

	/**
	 * @description get all article in stock
	 * @return return all article in a list
	 */
	public List<Artikel> getAllArtikel() {
		return this.artikelStock;
	}

	/**
	 * @description find articles by string
	 * @param s -  search string can be a title or a number
	 * @return list with matched articles
	 */
	public List<Artikel> findArtikelByString(String s) {
		List<Artikel> artikel = new Vector<Artikel>();
		Iterator<Artikel> i = artikelStock.iterator();
		while (i.hasNext()) {
			Artikel a = i.next();
			// parse the nr from string
			int nr;
			try {
				nr = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				nr = -1;
			}
			if (a.getTitle().equals(s) || nr == a.getNr()) {
				artikel.add(a);
			}
		}
		return artikel;
	}

	/**
	 * find a article by integer number
	 * @param s - artivle number
	 * @return founded articles as a list
	 */
	public List<Artikel> findArtikelByString(int s) {
		return this.findArtikelByString(new Integer(s).toString());
	}

	/**
	 * @description raise the stock from a article
	 * @param nr article number
	 * @param stock  - article stock count to set
	 * @return
	 */
	public boolean raiseStock(int nr, int stock) {
		List<Artikel> a = this.findArtikelByString(nr);
		if (a != null) {
			// get first founded article
			Artikel _a = a.get(0);
			_a.setStock(stock + _a.getStock());
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @description reduce the stock from an article
	 * @param nr
	 * @param stock
	 * @throws ArtikelNotFoundException
	 */
	public void reduceStock(int nr, int stock) throws ArtikelNotFoundException {
		List<Artikel> a = this.findArtikelByString(nr);
		if (a != null) {
			// get the first article object
			Artikel _a = a.get(0);
			_a.setStock(_a.getStock() - stock);
		} else {
			throw new ArtikelNotFoundException(a.get(0));
		}
	}
	
	/**
	 * @description  reduce the stock from articles in a hashmap
	 * @param aMap
	 * @throws ArtikelNotFoundException 
	 */
	public void reduceStock(Map<Artikel, Number> aMap) throws ArtikelNotFoundException {
		if(aMap != null) {
			Iterator iterator = aMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<Artikel, Number> pair = (Map.Entry<Artikel, Number>) iterator.next();
				// find article by number
				List<Artikel> foundedArtikel = this.findArtikelByString( pair.getKey().getNr() );
				// check first element, other elements are unimportant
				if(foundedArtikel.get(0) != null) {
					// reduce the stock
					this.reduceStock(foundedArtikel.get(0).getNr(), pair.getValue().intValue());
				}
				
			}
		}
	}

	/**
	 * @description save all articles in a xml file with the file manager
	 */
	public void saveArtikel() {
		try {
			pm.openForWriting();
			if (!artikelStock.isEmpty()) {
				pm.save(this.artikelStock);
			}
		} catch (IOException e) {
		} finally {
			pm.close();
		}
	}

	/**
	 * @description  reads all articles from an xml file with the file manager
	 * 
	 * @param file
	 */
	public void readArtikel() {
		try {
			pm.openForReading();
			ArrayList<Artikel> a = (ArrayList<Artikel>) pm.read();
			if (a != null)
				this.artikelStock = a;
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		} finally {
			pm.close();
		}
	}
	
	/**
	 * @description update artikel
	 * @param nr
	 * @param title
	 * @param bestand
	 * @param price
	 * @param menegeneinheit
	 * @return the updated artikel
	 * @throws ArtikelNotFoundException 
	 */
	public Artikel updateArtikel(int nr, String title, int bestand, double price, int mengeneinheit) throws ArtikelNotFoundException {
		if(mengeneinheit != 0) {
			MassenArtikel mA = (MassenArtikel) this.findArtikelByString(nr).get(0);
			if(mA == null) {
				throw new ArtikelNotFoundException(mA);
			} else {
				mA.setPrice(price);
				mA.setStock(bestand);
				mA.setTitle(title);
				mA.setAnzahl(mengeneinheit);
			}
			return mA;
		} else {
			Artikel artikel = this.findArtikelByString(nr).get(0);
			if(artikel == null) {
				throw new ArtikelNotFoundException(artikel);
			} else {
				artikel.setPrice(price);
				artikel.setStock(bestand);
				artikel.setTitle(title);
			}
			return artikel;
		}
	}
	
}
