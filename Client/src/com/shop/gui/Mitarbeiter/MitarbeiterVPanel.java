package com.shop.gui.Mitarbeiter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import de.hsb.simon.client.net.ServiceVInterfaceImpl;

public class MitarbeiterVPanel extends JPanel {
	public MitarbeiterVPanel(final ServiceVInterfaceImpl shop) {
		super();
		
		JTable table = new JTable( new MitarbeiterTableModel(shop.getAllMitarbeiter()) );
		add( new JScrollPane(table) );
		
		add(new JLabel("MitarbeirterV"));
		
	}
}
