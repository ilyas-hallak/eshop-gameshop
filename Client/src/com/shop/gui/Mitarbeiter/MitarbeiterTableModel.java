package com.shop.gui.Mitarbeiter;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.shop.valueobjects.Mitarbeiter;
import com.shop.valueobjects.Person;

/**
 * Klasse fuer den Inhalt der Mitarbeiter Tabelle
 *
 */
public class MitarbeiterTableModel extends DefaultTableModel {
	
	private Vector<String> columnNames;
	private Vector<Vector<String>> data;
	
	public MitarbeiterTableModel(ArrayList<Person> arrayList) {
		super();
		
		//Bezeichnungen in der Mitarbeiter Tabelle
		columnNames = new Vector<String>();
		columnNames.add("Nummer");
		columnNames.add("Name");
		columnNames.add("Email");
		columnNames.add("Passwort");
		
		data = new Vector<Vector<String>>();
		updateDataVector(arrayList);
	}
	
	// Daten der Mitarbeiter auslesen und in Tabelle einschreiben
	public void updateDataVector(ArrayList<Person> mitarbeiter) {
		data.clear();
		
		for (Person m: mitarbeiter) {
			Vector<String> mVector = new Vector<String>();
			if(m instanceof Mitarbeiter) {
				Mitarbeiter m1 = (Mitarbeiter)m;
				mVector.add(m1.getNr()+"");
				mVector.add(m1.getName());
				mVector.add(m1.getEMail()+"");
				mVector.add(m1.getPassword()+"");
				data.add(mVector);
			}
		}
		
		//einschreiben der Daten und Beschriftungen in Tabelle
		setDataVector(data, columnNames);
	}
}
