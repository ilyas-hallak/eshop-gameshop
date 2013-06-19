package com.shop.gui.Mitarbeiter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.shop.logic.ServiceV;

public class EreignisPanel extends JPanel {
	public EreignisPanel(final ServiceV shop) {
		super();
	
		JTable table = new JTable( new EreignisTableModel(shop.getAllEreignisse()));
		add( new JScrollPane(table) );
		
		add(new JLabel("Ereignisse"));
	}
}
