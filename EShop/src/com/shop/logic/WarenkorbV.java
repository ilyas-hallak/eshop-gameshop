package com.shop.logic;

import java.util.List;
import com.shop.valueobjects.Artikel;


public class WarenkorbV {
	
	private List<Artikel> warenkorb;
	
	public void addArtikel(Artikel a) {
		this.warenkorb.add(a);
	}
	
	

}
