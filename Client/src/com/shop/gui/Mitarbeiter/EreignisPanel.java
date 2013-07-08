package com.shop.gui.Mitarbeiter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import de.hsb.simon.client.net.ServiceVInterfaceImpl;

public class EreignisPanel extends JPanel {
	
	/**
	 * Konstruktor fuer das Ereignis Panel
	 * @param shop - Variable um über die ServiceV auf den shop auf dem Server zugreifen zu koennen
	 */
	public EreignisPanel(final ServiceVInterfaceImpl shop) {
		super();
	
		// Tabelle mit allen erstellten Ereignissen erzeugen
		JTable table = new JTable( new EreignisTableModel(shop.getAllEreignisse()));
		add( new JScrollPane(table) );
		
		// Beschriftung dem Panel hinzufuegen
		add(new JLabel("Ereignisse"));
	}
}
