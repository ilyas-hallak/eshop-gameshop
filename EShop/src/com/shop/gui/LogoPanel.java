package com.shop.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LogoPanel extends JPanel {
	public LogoPanel() {
		super();
		add(new JLabel("LOGO"));
		setPreferredSize(new Dimension(30, 100));
	}
}
