package com.shop.gui;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MessagePanel extends JPanel {
	private JLabel msg;
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
