package com.shop.gui.Mitarbeiter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.shop.logic.ServiceV;

public class MitarbeiterVPanel extends JPanel {
	public MitarbeiterVPanel(final ServiceV shop) {
		super();
		
		JTable table = new JTable( new MitarbeiterTableModel(shop.getAllMitarbeiter()) );
		add( new JScrollPane(table) );
		
		add(new JLabel("MitarbeirterV"));
		
	}
}
