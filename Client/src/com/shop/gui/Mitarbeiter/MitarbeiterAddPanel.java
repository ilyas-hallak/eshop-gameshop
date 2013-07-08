package com.shop.gui.Mitarbeiter;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.shop.gui.EShopClientGui;
import com.shop.gui.PanelManager;

import de.hsb.simon.client.net.ServiceVInterfaceImpl;

public class MitarbeiterAddPanel extends JPanel {
	/**
	 * Konstruktor MitarbeiterAddPanel um neuen Mitarbeiter zu registrieren
	 * @param shop - ServiceV Schnittstelle
	 * @param pManager - Variable fuer PanaelManager
	 * @param frame - Zugriff auf Benuteroberflaeche
	 */
	public MitarbeiterAddPanel(final ServiceVInterfaceImpl shop, final PanelManager pManager, final EShopClientGui frame) {
		super(new GridBagLayout());

		GridBagConstraints cs = new GridBagConstraints();
		
		//Eingabefelder und Beschriftungen
		/// NAME
		
        JLabel nameLabel = new JLabel("Name: ");
        cs.gridx = 0;
        cs.gridy = 0;
        this.add(nameLabel, cs);

        final JTextField nameField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        this.add(nameField, cs);
		
        /// EMAIL
        
        JLabel emailLabel = new JLabel("Email: ");
        cs.gridx = 0;
        cs.gridy = 1;
        this.add(emailLabel, cs);

        final JTextField emailField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        this.add(emailField, cs);
		
        
        /// PASSWORT
        
        JLabel passwordLabel = new JLabel("Passwort: ");
        cs.gridx = 0;
        cs.gridy = 2;
        this.add(passwordLabel, cs);

        final JTextField passwortField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        this.add(passwortField, cs);

        /// BUTTON "ANLEGEN"
        
        JButton anlegen = new JButton("Mitarbeiter anlegen");
        cs.gridx = 1;
        cs.gridy = 3;
        this.add(anlegen, cs);
        
        // Bei Button Klick - Eingabefelder einlesen und neuen Mitarbeiter dem shop hinzufuegen
        anlegen.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		
    			shop.insertMitarbeiter(nameField.getText(), emailField.getText(), passwortField.getText());
    			
    			// Austauschen der Panels zum aktuallisieren der Ansicht des neuen Mitarbeiter (refresh)
        		pManager.changePanel(new MitarbeiterMenu(pManager, shop, frame), new MitarbeiterVPanel(shop), new MitarbeiterAddPanel(shop, pManager, frame));
    	}
        });
        
        
		
	}
}
