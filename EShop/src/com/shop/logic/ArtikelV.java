package com.shop.logic;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.shop.exceptions.ArtikelexistsException;
import com.shop.persistence.*;
import com.shop.valueobjects.Artikel;

public class ArtikelV {

	// Verwaltung des Artikelbestands in einem Vector
	private List<Artikel> artikelStock;

	// Persistenz-Schnittstelle, die für die Details des Dateizugriffs verantwortlich ist
	private PersistenceManager pm = new FilePersistenceManager();
	
	public ArtikelV() {
		this.artikelStock = new Vector<Artikel>();
	}
	
	public void insertArtikel(Artikel a) throws ArtikelexistsException {
		if (!artikelStock.contains(a))
			this.artikelStock.add(a);
		else
			throw new ArtikelexistsException(a.getTitle() + " - in 'einfuegen()'");
	}

	public List<Artikel> getAllArtikel() {
		// TODO Auto-generated method stub
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

	public void saveArtikel(String file) {
		try {
			pm.openForWriting(file);
			if(!artikelStock.isEmpty()) {
				Iterator<Artikel> i = artikelStock.iterator();
				while(i.hasNext()) {
					Artikel a = i.next();
					pm.saveArtikel(a);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}
	}
	
	public void readArtikel(String file) {
		try {
			pm.openForReading(file);
			Artikel a;
			do {
				a = pm.loadArtikel();
				if(a != null) {
					try {
						this.insertArtikel(a);
					} catch(Exception e) {// wird niemals auftreten da vorher schon abegfangen}
					}
				}
			} while(a != null);
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		} finally {
			pm.close();
		}
	}
}
