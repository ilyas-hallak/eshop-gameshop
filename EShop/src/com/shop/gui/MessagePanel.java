package com.shop.gui;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MessagePanel extends JPanel {
	public MessagePanel() {
		super();
		add(new JLabel("Error"));
		setPreferredSize(new Dimension(30, 100));
	}
}
