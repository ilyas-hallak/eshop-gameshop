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

public class ArtikelV implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 304961499015349662L;

	// Persistenz-Schnittstelle, die für die Details des Dateizugriffs verantwortlich ist
	private FilePersistenceManager pm;
	
	// Verwaltung des Artikelbestands in einem Vector
	private ArrayList<Artikel> artikelStock;

	/**
	 * @return the artikel stock
	 */
	public ArrayList<Artikel> getArtikelStock() {
		return artikelStock;
	}

	/**
	 * @param artikelStock the artikelStock to set
	 */
	public void setArtikelStock(ArrayList<Artikel> artikelStock) {
		this.artikelStock = artikelStock;
	}

	/**
	 * @return the pm
	 */
	public FilePersistenceManager getPm() {
		return pm;
	}

	/**
	 * @param pm the pm to set
	 */
	public void setPm(FilePersistenceManager pm) {
		this.pm = pm;
	}

	// empty constructor for Serializable
	public ArtikelV() {
		
	}
	
	public ArtikelV(String file) {
		this.artikelStock = new ArrayList<Artikel>();
		pm = new FilePersistenceManager(file);
	}
	
	public void insertArtikel(Artikel a) throws ArtikelexistsException {
		if (!artikelStock.contains(a))
			this.artikelStock.add(a);
		else
			throw new ArtikelexistsException(a.getTitle() + " - in 'einfuegen()'");
	}

	public List<Artikel> getAllArtikel() {
		return this.artikelStock;
	}

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

	public List<Artikel> findArtikelByString(int s) {
		return this.findArtikelByString(new Integer(s).toString());
	}
	
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

	public void saveArtikel() {
		try {
			
			pm.openForWriting();
			if(!artikelStock.isEmpty()) {
				pm.saveArtikel(this);
			}
		} catch (IOException e) {
			// e.printStackTrace();
			System.out.println("hallo");
		} finally {
			pm.close();
		}
	}
	
	public void readArtikel(String file) {
		try {
			pm.openForReading();
			ArtikelV a = pm.loadArtikel();;
			if(a != null)
				this.artikelStock = a.artikelStock;

				
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		} finally {
			pm.close();
		}
	}
}
