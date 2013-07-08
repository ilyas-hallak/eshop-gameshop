package com.shop.valueobjects;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Warenkorb implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9198575105132720166L;
	/**
	 * Warenkorb 1:n Artikel => Artikel Liste im Warenkorb
	 * Artikel 1:1 Anzahl => Hashtable <Artikel, Anzahl>
	 */
	private Map<Artikel, Number> warenkorb = new HashMap<Artikel, Number>();
	
	/**
	 * @return the warenkorb
	 */
	public Map<Artikel, Number> getWarenkorb() {
		return warenkorb;
	}

	/**
	 * @param warenkorb the warenkorb to set
	 */
	public void setWarenkorb(Map<Artikel, Number> warenkorb) {
		this.warenkorb = warenkorb;
	}

	/**
	 * Adds an article to the cart, if the article exists, 
	 * the count will be increment
	 * @param a Article Object
	 * @param count of Articles
	 */
	public void addArtikel(Artikel a, int count) {
		this.warenkorb.put(a, count);
//		if(this.warenkorb.containsKey(a)) {
//			this.warenkorb.put(a, count);
//		} else {
//			int x = this.warenkorb.get(a).intValue() + 1;
//			this.removeArtikel(a);
//			this.warenkorb.put(a, x);
//		}
	}
	
	/**
	 * removes an article from cart if these exists
	 * @param a article object
	 */
	public void removeArtikel(Artikel a) {
		Iterator iterator = this.warenkorb.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Artikel, Number> pair = (Map.Entry<Artikel, Number>) iterator.next();
			if(pair.getKey().getTitle() == a.getTitle() && pair.getKey().getNr() == a.getNr()) {
				this.warenkorb.remove(pair.getKey());
			}
		}
	}
	
	/**
	 * get all article as map
	 * @return
	 */
	public Map<Artikel, Number> getAllArtikel() {
		return this.warenkorb;
	}
	
	/**
	 * creates an 'rechnungs' object
	 * @param k Kunde
	 * @return Rechung object
	 */
	public Rechnung buy(Kunde k) {
		Rechnung r = new Rechnung();
		r.setDate(new Date());
		r.setArticleList(this.warenkorb);
		r.setKunde(k);
		return r;
	}
	
	public void complete() {
		this.warenkorb.clear();
	}
}
