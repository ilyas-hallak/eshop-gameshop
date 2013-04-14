package com.shop.logic;

import java.io.IOException;
import java.util.List;

import com.shop.exceptions.ArtikelexistsException;

import com.shop.valueobjects.Artikel;

/**
 * Klasse zur Verwaltung eines (sehr einfachen) Shop.
 * Bietet Methoden zum Zurückgeben/Suche/Einfügen/Speichern von Artikeln,
 * @author hallakoglu
 * @version 0
 */
public class ServiceV {
	
	private String file = "";
	private ArtikelV artikelV;

	public ServiceV(String file) throws IOException {
		this.file = file;
		this.artikelV = new ArtikelV();
	}
	
	public boolean insertArtikel(int nr, String title) {
		try {
			Artikel a = new Artikel(nr, title);
			artikelV.insertArtikel(a);
		} catch (ArtikelexistsException e) {
			return false;
		}
		return true;
	}
	
	public List<Artikel> getAllArtikel() {
		return artikelV.getAllArtikel(); //shop.getAllArtikel();
	}
	
	public List<Artikel> sucheNachTitel(String titel) {
		// einfach delegieren an meineBuecher
		return null;//meineBuecher.sucheBuecher(titel); 
	}

	public void loadData() {
		// TODO Auto-generated method stub
		
	}
	
	public void saveArtikel() throws IOException {
		artikelV.saveData(file+"_B.txt");
	}

	public List<Artikel> findArtikelByString(String s) {
		return artikelV.findArtikelByString(s);
	}

}
