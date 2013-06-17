package com.shop.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class PanelManager {
	
	private EShopClientGui client;
	
	public PanelManager(EShopClientGui eShopClientGui) {
		this.client = eShopClientGui;
	}
	
	public void changePanel(JPanel panel) {
		// this.client.remove(this.client.getContentPane());
		this.client.add(panel, BorderLayout.CENTER);
//		this.client.revalidate();  
//		this.client.repaint();
	}
	
	public void changePanel(JPanel panel, String postion) {
		this.client.add(panel, postion);
	}
}
