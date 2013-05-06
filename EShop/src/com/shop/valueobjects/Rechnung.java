package com.shop.valueobjects;

import java.util.Date;
import java.util.Map;

public class Rechnung {
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
	public Map<Artikel, Number> getArticleList() {
		return articleList;
	}
	public void setArticleList(Map<Artikel, Number> articleList) {
		this.articleList = articleList;
	}
}
