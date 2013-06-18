package com.shop.gui.Kunde;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import com.shop.gui.Mitarbeiter.ArtikelTableModel;
import com.shop.logic.ServiceV;

public class ArtikelPanelKunde extends JPanel {

	public ArtikelPanelKunde(ServiceV shop) {
		super();
		JTable table = new JTable( new ArtikelTableModel(shop.getAllArtikel()) );
		add( new JScrollPane(table) );
	}
	
}