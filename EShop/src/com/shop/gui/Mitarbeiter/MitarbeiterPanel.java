package com.shop.gui.Mitarbeiter;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MitarbeiterPanel extends JPanel {
	
	public MitarbeiterPanel() {
		super(new CardLayout());
		add(new JLabel("Herzlich Willkommen!"));
	}
}
