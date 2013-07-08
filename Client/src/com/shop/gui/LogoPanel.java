package com.shop.gui;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Klasse fuer den Logo-Kopf, bleibt immer an gleicher Stelle mit gleicher Größe 
 *
 */
public class LogoPanel extends JPanel {
	public LogoPanel() {
		super();
		add(new JLabel("LOGO"));
		setPreferredSize(new Dimension(30, 100));
	}
}
