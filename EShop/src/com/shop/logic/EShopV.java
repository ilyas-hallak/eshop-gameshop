package com.shop.logic;

import java.io.IOException;
import java.util.List;

import com.shop.valueobjects.Artikel;

/**
 * 
 */
public class EShopV {
	
	private ServiceV service;
	
	public EShopV(String file) throws IOException {
		service = new ServiceV(file);
	}
	
	public List<Artikel> getAllArtikel() {
		return this.service.getAllArtikel();
	}

	public boolean insertArtikel(int nr, String title, int bestand) {
		return this.service.insertArtikel(nr, title, bestand);
	}
	
	public boolean raiseStock(int nr, int stock) {
		return this.service.raiseStock(nr, stock);
	}
	
	public List<Artikel> findArtikelByString(String s) {
		return this.service.findArtikelByString(s);
	}
	
}
