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

public class ArtikelV {

	/**
	 * Persistenz-Schnittstelle, die für die Details des Dateizugriffs
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
		// überprüfen ob der titel schon im Artikelbestand vorhanden ist
		Iterator<Artikel> it = this.artikelStock.iterator();
		while (it.hasNext()) {
			Artikel article = (Artikel) it.next();
			if (article.getTitle() == title) {
				// wenn er bereits vorhanden ist die Exception schmeißen
				throw new ArtikelexistsException(title + " - in 'einfuegen()'");
			}
			// wenn er nicht vorhanden ist Artikel-Objekt erzeugen und
			// hinzufügen
			else {
				Artikel a = new Artikel(this.artikelStock.size() + 1, title,
						bestand, price);
				this.artikelStock.add(a);
				return a;
			}
		}
		return null;
	}

	/**
	 * 
	 * @return return all article in a list
	 */
	public List<Artikel> getAllArtikel() {
		return this.artikelStock;
	}

	/**
	 * @param s
	 *            search string can be a title or a number
	 * @return list with matched articles
	 */
	public List<Artikel> findArtikelByString(String s) {
		List<Artikel> artikel = new Vector<Artikel>();
		Iterator<Artikel> i = artikelStock.iterator();
		while (i.hasNext()) {
			Artikel a = i.next();
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
	 * find a article by int
	 * 
	 * @param s
	 * @return
	 */
	public List<Artikel> findArtikelByString(int s) {
		return this.findArtikelByString(new Integer(s).toString());
	}

	/**
	 * 
	 * @param nr
	 *            article number
	 * @param stock
	 *            article stock count
	 * @return
	 */
	public boolean raiseStock(int nr, int stock) {
		List<Artikel> a = this.findArtikelByString(nr);
		if (a != null) {
			Artikel _a = a.get(0);
			_a.setStock(stock + _a.getStock());
			return true;
		} else {
			return false;
			// throw new ArtikelNotFoundException();
		}
	}

	/**
	 * 
	 * @param nr
	 * @param stock
	 * @throws ArtikelNotFoundException
	 */
	public void reduceStock(int nr, int stock) throws ArtikelNotFoundException {
		List<Artikel> a = this.findArtikelByString(nr);
		if (a != null) {
			Artikel _a = a.get(0);
			_a.setStock(_a.getStock() - stock);
		} else {
			throw new ArtikelNotFoundException("Artikel mit der Nummer: " + nr + " nicht gefunden!");
		}
	}
	
	/**
	 * 
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
	 * save all articles in a xml file with the file manager
	 */
	public void saveArtikel() {
		try {
			pm.openForWriting();
			if (!artikelStock.isEmpty()) {
				pm.save(this.artikelStock);
			}
		} catch (IOException e) {
			// e.printStackTrace();
			System.out.println("hallo");
		} finally {
			pm.close();
		}
	}

	/**
	 * reads all articles from an xml file with the file manager
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
}
