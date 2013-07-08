package com.shop.gui.Mitarbeiter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import de.hsb.simon.client.net.ServiceVInterfaceImpl;

/**
 * MitarbeiterVPanel - Klasse zur Verwaltung der eingetragenden Mitarbeiter
 *
 */
public class MitarbeiterVPanel extends JPanel {
	/**
	 * Konstruktor
	 * @param shop - Fuer den Zugriff auf den Server ueber ServiceV
	 */
	public MitarbeiterVPanel(final ServiceVInterfaceImpl shop) {
		super();
		
		// Tabelle mit allen Mitarbeitern erzeugen ueber die Klasse MitarbeiterTableModel
		JTable table = new JTable( new MitarbeiterTableModel(shop.getAllMitarbeiter()) );
		add( new JScrollPane(table) );
		
		add(new JLabel("MitarbeirterV"));
		
	}
}
