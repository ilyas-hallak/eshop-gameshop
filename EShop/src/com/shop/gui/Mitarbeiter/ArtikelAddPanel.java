package com.shop.gui.Mitarbeiter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import com.shop.exceptions.ArtikelexistsException;
import com.shop.gui.PanelManager;
import com.shop.logic.ServiceV;

public class ArtikelAddPanel extends JPanel {
	public ArtikelAddPanel(final ServiceV shop, final PanelManager pManager) {
		super(new GridBagLayout());

		GridBagConstraints cs = new GridBagConstraints();
		
		/// TITEL
		
        JLabel titleLabel = new JLabel("Titel: ");
        cs.gridx = 0;
        cs.gridy = 0;
        this.add(titleLabel, cs);

        final JTextField titleTextfield = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        this.add(titleTextfield, cs);
        
        ///PREIS
        
        JLabel priceLabel = new JLabel("Preis: ");
        cs.gridx = 0;
        cs.gridy = 1;
        this.add(priceLabel, cs);

        final JTextField priceField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        this.add(priceField, cs);

        /// LAGERBESTAND
        JLabel stockLabel = new JLabel("Lagerbestand: ");
        cs.gridx = 0;
        cs.gridy = 2;
        this.add(stockLabel, cs);

        final JTextField stockField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        this.add(stockField, cs);
        
        JButton anlegen = new JButton("Artikel anlegen");
        cs.gridx = 1;
        cs.gridy = 3;
        this.add(anlegen, cs);
        
        anlegen.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		
        		try {
					shop.insertArtikel(titleTextfield.getText(), Integer.parseInt(stockField.getText()), Integer.parseInt(priceField.getText()));
					
	        		pManager.changePanel(new MitarbeiterMenu(pManager, shop), new ArtikelPanel(shop), new ArtikelAddPanel(shop, pManager));
					
				} catch (NumberFormatException e1) {
					// Bitte Nummer angeben
				} catch (ArtikelexistsException e1) {
					// artikel schon vorhanden!
				}
        	}
        });
	}
}
