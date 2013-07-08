package com.shop.gui.Mitarbeiter;

import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.shop.gui.EShopClientGui;

public class MitarbeiterPanel extends JPanel {
	
	/**
	 * Konstruktor der Klasse MitarbeiterPanel - Willkommen Starttext im Mitarbeitermenue
	 * @param frame -  auf Benutzeroberflaeche
	 */
	public MitarbeiterPanel(EShopClientGui frame) {
		super();
		//Willkommen Label fuer Panel
		add(new JLabel("Herzlich Willkommen!"));
		//Menubar diesem Panel hinzufuegen
		frame.getJMenuBar().setVisible(true);
		// show save item
		frame.getJMenuBar().getMenu(0).getItem(0).setVisible(true);
	}
}
