package com.shop.gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Klasse welche den Austausch der jeweiligen Bereiche (Panels) regelt
 *
 */
public class PanelManager {
	
	private EShopClientGui client;
	
	/**
	 * 
	 * @param eShopClientGui - Variable fuer den Zugriff auf die Benutzer Oberflaeche (Client-Gui)
	 * um die Panels auf Client-Seite austauschen zu koennen
	 */
	//Konstruktor
	public PanelManager(EShopClientGui eShopClientGui) {
		this.client = eShopClientGui;
	}
	

	/**
	 * Methode zum Wechsel der Panels west, center und east
	 * north und south bleiben immer auf gleicher Position
	 * @param west - Variable des Panels im Westen (links)
	 * @param center - Variable des Panels in der Mitte
	 * @param east - Variable des Panels East (rechts)
	 * @return - Rueckgabe der Nachricht bei Fehlermeldung
	 */
	public JLabel changePanel(JPanel west, JPanel center, JPanel east) {
		//loeschen aller aktuell angezeigten Panels
		this.client.getContentPane().removeAll();
		
		//hinzufuegen des north - Logopanel
		this.client.add(new LogoPanel(), BorderLayout.NORTH);

		//hinzufuegen der neu leeren Panels, in denen das gewuenschte Panel geladen werden kann
		this.client.add(west, BorderLayout.WEST);
		this.client.add(center, BorderLayout.CENTER);
		this.client.add(east, BorderLayout.EAST);
		
		// Nachrichten Panel
		MessagePanel mp = new MessagePanel();
		this.client.add(mp, BorderLayout.SOUTH);
		
		this.client.revalidate();  
		
		return mp.getMessageLabel();
	}
	
}
