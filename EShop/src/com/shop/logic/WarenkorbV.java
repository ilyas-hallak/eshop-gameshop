package com.shop.logic;

import java.util.HashMap;
import java.util.Map;

import com.shop.valueobjects.Artikel;

public class WarenkorbV {
	
	/**
	 * Warenkorb 1:n Artikel => Artikel Liste im Warenkorb
	 * Artikel 1:1 Anzahl => Hashtable <Artikel, Anzahl>
	 */
	private Map<Artikel, Number> warenkorb = new HashMap<Artikel, Number>();
	
	/**
	 * Adds an article to the cart, if the article exists, 
	 * the count will be increment
	 * @param a Article Object
	 * @param count of Articles
	 */
	public void addArtikel(Artikel a, int count) {
		if(this.warenkorb.containsKey(a)) {
			this.warenkorb.put(a, count);
		} else {
			int x = this.warenkorb.get(a).intValue() + 1;
			this.removeArtikel(a);
			this.warenkorb.put(a, x);
		}
	}
	
	/**
	 * removes an article from cart, if these exists
	 * @param a article object
	 */
	public void removeArtikel(Artikel a) {
		if(this.warenkorb.containsKey(a)) {
			 this.warenkorb.remove(a);
		}
	}
	
	/**
	 * get all article as map
	 * @return
	 */
	public Map<Artikel, Number> getAllArtikel() {
		return this.warenkorb;
	}

}
