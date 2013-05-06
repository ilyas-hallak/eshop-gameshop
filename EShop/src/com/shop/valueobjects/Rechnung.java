package com.shop.valueobjects;

import java.util.Date;
import java.util.List;

public class Rechnung {
	private List<Artikel> articleList;
	private Date date;
	private Kunde kunde;
	
	/**
	 * @return the articleList
	 */
	public List<Artikel> getArticleList() {
		return articleList;
	}
	/**
	 * @param articleList the articleList to set
	 */
	public void setArticleList(List<Artikel> articleList) {
		this.articleList = articleList;
	}
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
}
