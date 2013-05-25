package com.shop.logic;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.shop.exceptions.ArtikelexistsException;
import com.shop.persistence.FilePersistenceManager;
import com.shop.valueobjects.Artikel;

public class ArtikelV {

	/**
	 *  Persistenz-Schnittstelle, die für die Details des Dateizugriffs verantwortlich ist
	 */
	private FilePersistenceManager pm;
	
	/**
	 * Verwaltung des Artikelbestands
	 */
	private ArrayList<Artikel> artikelStock;

	/**
	 * init the file manager and the article stock
	 * @param file filename for the file manager
	 */
	public ArtikelV(String file) {
		this.artikelStock = new ArrayList<Artikel>();
		pm = new FilePersistenceManager(file);
		this.readArtikel();
	}
	
	/**
	 * Adds an article to the article stock
	 * @param nr article nr
	 * @param title article title
	 * @param bestand article stock
	 * @throws ArtikelexistsException 
	 */
	public void insertArtikel(Artikel a) throws ArtikelexistsException {
		if (!artikelStock.contains(a)) {
			this.artikelStock.add(a);
		} else {
			throw new ArtikelexistsException(a.getTitle() + " - in 'einfuegen()'");
		}
	}

	/**
	 * 
	 * @return return all article in a list
	 */
	public List<Artikel> getAllArtikel() {
		return this.artikelStock;
	}

	/**
	 * @param s search string can be a title or a number
	 * @return list with matched articles
	 */
	public List<Artikel> findArtikelByString(String s) {
		List<Artikel> artikel = new Vector<Artikel>();
		Iterator<Artikel> i = artikelStock.iterator();
		while(i.hasNext()) {
			Artikel a = i.next();
			int nr;
			try {
				nr = Integer.parseInt(s);
			} catch(NumberFormatException e) {
				nr = -1;
			}
			if(a.getTitle().equals(s) || nr == a.getNr()) {
				artikel.add(a);
			}
		}
		return artikel;
	}

	/**
	 * find a article by int
	 * @param s
	 * @return
	 */
	public List<Artikel> findArtikelByString(int s) {
		return this.findArtikelByString(new Integer(s).toString());
	}
	
	/**
	 * 
	 * @param nr article number
	 * @param stock article stock count
	 * @return
	 */
	public boolean raiseStock(int nr, int stock) {
		List<Artikel> a = this.findArtikelByString(nr);
		if(a != null) {
			Artikel _a = a.get(0);
			_a.setStock(stock + _a.getStock());
			return true;
		} else {
			return false;
			// throw new ArtikelNotFoundException();
		}
	}
	
	/**
	 * save all articles in a xml file with the file manager
	 */
	public void saveArtikel() {
		try {
			pm.openForWriting();
			if(!artikelStock.isEmpty()) {
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
	 * @param file
	 */
	public void readArtikel() {
		try {
			pm.openForReading();
			ArrayList<Artikel> a = (ArrayList<Artikel>)pm.read();
			if(a != null)
				this.artikelStock = a;
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		} finally {
			pm.close();
		}
	}
}
