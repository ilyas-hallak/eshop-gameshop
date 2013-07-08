package com.shop.valueobjects;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @description Klasse Rechnung - enthaelt die Werte der Rechnung mit getter und setter
 *
 */
public class Rechnung implements Serializable {

	// ATTRIBUTE der Klasse
	private static final long serialVersionUID = -690973173810667484L;
	private Map<Artikel, Number> articleList;
	private Date date;
	private Kunde kunde;
	
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the kunde
	 */
	public Kunde getKunde() {
		return kunde;
	}
	/**
	 * @param kunde the kunde to set
	 */
	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}
	
	/**
	 * @return the articleList
	 */
	public Map<Artikel, Number> getArticleList() {
		return articleList;
	}
	
	/**
	 * @param articleList - Liste der Artikel die gekauft wurden
	 */
	public void setArticleList(Map<Artikel, Number> articleList) {
		this.articleList = articleList;
	}
}
