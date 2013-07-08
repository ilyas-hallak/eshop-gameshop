package com.shop.gui.Mitarbeiter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.shop.gui.EShopClientGui;
import com.shop.gui.PanelManager;

import de.hsb.simon.client.net.ServiceVInterfaceImpl;

/**
 * Mitarbeiter Menue mit Buttons um Artikel, Mitarbeiter oder Ereignisse Verwalten zu koennen
 *
 */
public class MitarbeiterMenu extends JPanel {
	
	private JButton artikel;
	private JButton mitarbeiter;
	private JButton ereignisse;
	
	private PanelManager pManager;
	
	/**
	 * Konstruktor MitarbeiterMenu
	 * @param pm - Variabel um Panels auszutauschen zum aktualisieren
	 * @param shop - zum Zugriff auf die ServiceV auf dem Server
	 * @param frame - Benutzeroberflache uebergeben
	 */
	public MitarbeiterMenu(PanelManager pm, final ServiceVInterfaceImpl shop, final EShopClientGui frame) {
		super();
		
		this.pManager = pm;
		
		JPanel boxPanel = new JPanel();
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
		
		/// BUTTON ARTIKEL
		artikel = new JButton("Artikel");
		artikel.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		JPanel panel = new ArtikelAddPanel(shop, pManager, frame);
        		pManager.changePanel(new MitarbeiterMenu(pManager, shop, frame), new ArtikelPanel(shop, frame, panel, pManager), panel);
        	}
		});
		
		//BUTTON MITARBEITER
		mitarbeiter = new JButton("Mitarbeiter");
		mitarbeiter.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		pManager.changePanel(new MitarbeiterMenu(pManager, shop, frame), new MitarbeiterVPanel(shop), new MitarbeiterAddPanel(shop, pManager, frame));
        	}
		});
		
		//BUTTON EREIGNISSE
		ereignisse = new JButton("Ereignisse");
		ereignisse.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		pManager.changePanel(new MitarbeiterMenu(pManager, shop, frame), new EreignisPanel(shop), new JPanel());
        	}
		});

		//Buttons dem Boxpanel hinzufuegen
		boxPanel.add(artikel);
		boxPanel.add(mitarbeiter);
		boxPanel.add(ereignisse);
		
		// Das Panel hinzufuegen
		add(boxPanel);
	}
}
