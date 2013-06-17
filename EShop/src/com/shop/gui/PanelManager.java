package com.shop.gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.shop.gui.Mitarbeiter.MitarbeiterMenu;

public class PanelManager {
	
	private EShopClientGui client;
	
	public PanelManager(EShopClientGui eShopClientGui) {
		this.client = eShopClientGui;
	}
	

	public void changePanel(JPanel west, JPanel center, JPanel east) {
		this.client.getContentPane().removeAll();
		
		this.client.add(new LogoPanel(), BorderLayout.NORTH);

		this.client.add(west, BorderLayout.WEST);
		this.client.add(center, BorderLayout.CENTER);
		this.client.add(east, BorderLayout.EAST);
		
		this.client.revalidate();  

	}
	
}
