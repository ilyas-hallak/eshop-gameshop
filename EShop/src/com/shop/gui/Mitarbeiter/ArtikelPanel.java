package com.shop.gui.Mitarbeiter;

import java.awt.GridBagLayout;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import com.shop.logic.ServiceV;
import com.shop.valueobjects.Artikel;

public class ArtikelPanel extends JPanel {
	public ArtikelPanel(ServiceV shop) {
		super(new GridBagLayout());
		// add(new JLabel("Artikelliste"));
		
		JTable table = new JTable( new ArtikelTableModel(shop.getAllArtikel()) );
		add( new JScrollPane(table) );
	}
}
