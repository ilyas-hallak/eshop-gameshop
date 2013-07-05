package com.shop.gui.Mitarbeiter;

import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.shop.gui.EShopClientGui;

public class MitarbeiterPanel extends JPanel {
	
	public MitarbeiterPanel(EShopClientGui frame) {
		super();
		add(new JLabel("Herzlich Willkommen!"));
		frame.getJMenuBar().setVisible(true);
		// show save item
		frame.getJMenuBar().getMenu(0).getItem(0).setVisible(true);
	}
}
