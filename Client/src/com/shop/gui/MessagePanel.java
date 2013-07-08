package com.shop.gui;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Klasse fuer den Nachrichten-Bereich
 * hier werden die jeweiligen Nachrichten der Exceptions ausgegeben
 * erbt von JPanel
 * @param msg - Variabel für die Exception Nachricht
 *
 */
public class MessagePanel extends JPanel {
	private JLabel msg;
	
	// Konstruktor der Klasse erzeugt leeres JLabel welches mit der msg gefuellt wird
	public MessagePanel() {
		super();
		msg = new JLabel("");
		add(msg);
		setPreferredSize(new Dimension(30, 100));
	}
	public JLabel getMessageLabel() {
		return this.msg;
	}
}
