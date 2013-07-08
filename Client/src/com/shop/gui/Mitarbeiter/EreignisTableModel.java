package com.shop.gui.Mitarbeiter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.shop.valueobjects.Ereignis;

public class EreignisTableModel extends DefaultTableModel {


	private Vector<String> columnNames;
	private Vector<Vector<String>> data;
	
	public EreignisTableModel(ArrayList<Ereignis> ereignisse) {
		super();
		
		columnNames = new Vector<String>();
		columnNames.add("Nachricht");
		columnNames.add("Artikel");
		columnNames.add("Email");
		columnNames.add("Count");
		columnNames.add("Datum");
		columnNames.add("Person");

		data = new Vector<Vector<String>>();
		updateDataVector(ereignisse);
	}
	
	public void updateDataVector(ArrayList<Ereignis> ereignisse) {
		data.clear();
		
		for (Ereignis e: ereignisse) {
			Vector<String> mVector = new Vector<String>();
				mVector.add(e.getMessage());
				
			if(e.getArticle() != null) {
				mVector.add(e.getArticle().getTitle());
			} else {
				mVector.add(" - ");
			}
			
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


			data.add(mVector);
		}
		
		setDataVector(data, columnNames);
	}
}
