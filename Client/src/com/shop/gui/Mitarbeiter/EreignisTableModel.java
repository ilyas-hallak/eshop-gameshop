package com.shop.gui.Mitarbeiter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.shop.valueobjects.Ereignis;

/**
 * Klasse fuer die Ereignis Tabelle
 *
 */
public class EreignisTableModel extends DefaultTableModel {


	private Vector<String> columnNames;
	private Vector<Vector<String>> data;
	
	/**
	 * Konstruktor der KLasse EreignisTableModel
	 * @param ereignisse - Uebergabe der Variable um die erstellten Ereignisse nutzen zu koennen und der Tabelle hinzuzufuegen
	 */
	public EreignisTableModel(ArrayList<Ereignis> ereignisse) {
		super();
		
		// Beschriftung der Tabellenfelder
		columnNames = new Vector<String>();
		columnNames.add("Nachricht");
		columnNames.add("Artikel");
		columnNames.add("Email");
		columnNames.add("Count");
		columnNames.add("Datum");
		columnNames.add("Person");

		// Ereignisse der Tabelle updaten
		data = new Vector<Vector<String>>();
		updateDataVector(ereignisse);
	}
	
	/**
	 * updateDataVector - Methode um Ereignisse auszulesen und hinzuzufuegen
	 * @param ereignisse
	 */
	public void updateDataVector(ArrayList<Ereignis> ereignisse) {
		data.clear();
		
		// auslesen der Ereignisse und hinzufuegen der Parameter (Art des Ereignisses, Titel des Artikels, Person, Anzahl, Datum)
		for (Ereignis e: ereignisse) {
			Vector<String> mVector = new Vector<String>();
				mVector.add(e.getMessage());
				
			if(e.getArticle() != null) {
				mVector.add(e.getArticle().getTitle());
			} else {
				mVector.add(" - ");
			}
			
			// Person zu einem Ereignis zufuegen, wenn vorhanden
			if(e.getPerson() != null) {
				mVector.add(e.getPerson().geteMail());
			} else {
				mVector.add(" - ");
			}
			
			mVector.add(e.getCount()+"");
			
			// DATE
			DateFormat df2 = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			String formattedDate = df2.format(e.getDate().getTime());
			mVector.add(formattedDate+"");
			
			if(e.getPerson() != null) {
				mVector.add(e.getPerson().getName());
			} else {
				mVector.add(" - ");
			}

			// Daten (Parameter) dem Vector zufuegen
			data.add(mVector);
		}
		
		// Daten und Bezeichnungen der Tabelle hinzufuegen
		setDataVector(data, columnNames);
	}
}
