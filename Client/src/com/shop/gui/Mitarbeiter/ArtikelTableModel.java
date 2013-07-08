package com.shop.gui.Mitarbeiter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.shop.valueobjects.Artikel;
import com.shop.valueobjects.MassenArtikel;

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
		columnNames.add("Massengut");

		data = new Vector<Vector<String>>();
		updateDataVector(artikel);
	}
	
	
	public ArtikelTableModel(Map<Artikel, Number> warenkorb) {
		super();
		
		columnNames = new Vector<String>();
		columnNames.add("Nummer");
		columnNames.add("Titel");
		columnNames.add("Preis");
		columnNames.add("Stückzahl");
		
		data = new Vector<Vector<String>>();
		
		updateDataMap(warenkorb);
	}
	
	// Methode updateDataWarenkorb - Warenkorb durchgehen und Tabelle zufügen
	
	public void updateDataMap(Map<Artikel, Number> warenkorb) {
		data.clear();
		
		Iterator it = warenkorb.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Artikel, Number> pair = (Map.Entry<Artikel, Number>) it.next();
			Vector<String> warenVector = new Vector<String>();
			warenVector.add(pair.getKey().getNr()+"");
			warenVector.add(pair.getKey().getTitle());
			warenVector.add(pair.getKey().getPrice()+"");
			warenVector.add(pair.getValue().intValue()+"");

			data.add(warenVector);
		}
		
		setDataVector(data, columnNames);

	}
	
	
	
	public void updateDataVector(List<Artikel> artikel) {
		data.clear();
		
		for (Artikel a: artikel) {
			Vector<String> buchVector = new Vector<String>();
			buchVector.add(a.getNr()+"");
			buchVector.add(a.getTitle());
			buchVector.add(a.getPrice()+"");
			buchVector.add(a.getStock()+"");
			
			if(a instanceof MassenArtikel) {
				buchVector.add(((MassenArtikel) a).getAnzahl()+"");
			} else {
				buchVector.add("-");
			}
			
			data.add(buchVector);
		}
		
		setDataVector(data, columnNames);
	}

}
