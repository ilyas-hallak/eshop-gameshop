package com.shop.gui.Mitarbeiter;

import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.shop.gui.EShopClientGui;
import com.shop.logic.ServiceV;

public class ArtikelPanel extends JPanel {
	public ArtikelPanel(ServiceV shop, EShopClientGui frame) {
		super(new GridBagLayout());
		// add(new JLabel("Artikelliste"));
		
		JTable table = new JTable( new ArtikelTableModel(shop.getAllArtikel()) );
		add( new JScrollPane(table) );
	}
}
