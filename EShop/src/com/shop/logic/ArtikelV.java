package com.shop.logic;

import java.util.List;
import java.util.Vector;

import com.shop.exceptions.ArtikelexistsException;
import com.shop.valueobjects.Artikel;

public class ArtikelV {

	// Verwaltung des Artikelbestands in einem Vector
	private List<Artikel> artikelStock;

	// Persistenz-Schnittstelle, die für die Details des Dateizugriffs verantwortlich ist
	// private PersistenceManager pm = new FilePersistenceManager();
	
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

	public void saveData(String string) {
		/*
		 // PersistenzManager für Schreibvorgänge öffnen
		pm.openForWriting(datei);

		if (!buchBestand.isEmpty()) {
			Iterator<Buch> iter = buchBestand.iterator();
			while (iter.hasNext()) {
				Buch b = iter.next();
				pm.speichereBuch(b);				
			}
		}			
		
		// Persistenz-Schnittstelle wieder schlie�en
		pm.close(); 
		 */
		
	}



}
