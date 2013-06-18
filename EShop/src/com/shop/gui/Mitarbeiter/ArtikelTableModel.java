package com.shop.gui.Mitarbeiter;

import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;


import com.shop.valueobjects.Artikel;

public class ArtikelTableModel extends DefaultTableModel {
	private Vector<String> columnNames;
	private Vector<Vector<String>> data;
	
	public ArtikelTableModel(List<Artikel> artikel) {
		super();
		
		columnNames = new Vector<String>();
		columnNames.add("Nummer");
		columnNames.add("Titel");
		columnNames.add("Preis");
		columnNames.add("Lagerbestand");
		
		data = new Vector<Vector<String>>();
		updateDataVector(artikel);
	}
	
	public void updateDataVector(List<Artikel> artikel) {
		data.clear();
		
		for (Artikel a: artikel) {
			Vector<String> buchVector = new Vector<String>();
			buchVector.add(a.getNr()+"");
			buchVector.add(a.getTitle());
			buchVector.add(a.getPrice()+"");
			buchVector.add(a.getStock()+"");
			
			data.add(buchVector);
		}
		
		setDataVector(data, columnNames);
	}

}
