package com.shop.gui.Mitarbeiter;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.shop.exceptions.ArtikelNotFoundException;
import com.shop.exceptions.ArtikelexistsException;
import com.shop.gui.EShopClientGui;
import com.shop.gui.PanelManager;
import com.shop.valueobjects.Artikel;

import de.hsb.simon.client.net.ServiceVInterfaceImpl;

public class ArtikelAddPanel extends JPanel {
	public ArtikelAddPanel(final ServiceVInterfaceImpl shop, final PanelManager pManager, final EShopClientGui frame) {
		super(new GridBagLayout());

		GridBagConstraints cs = new GridBagConstraints();
		
		// ID
//		
//		JTextField idField = new JTextField(20);
//		this.add(idField);
		
		// ID
		
		final JTextField idField = new JTextField(20);
		idField.setVisible(false);
        cs.gridx = 2;
        cs.gridy = 0;
        this.add(idField, cs);
        
		
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
        
        // dem panel einen speichern button hinzufügen
        JButton saveBtn = new JButton("Speichern");
        saveBtn.setVisible(false);
		cs.gridx = 0;
        cs.gridy = 3;
        this.add(saveBtn, cs);
        
        saveBtn.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		
        		try {
					shop.updateArtikel( Integer.parseInt(idField.getText()) , titleTextfield.getText() , Integer.parseInt(stockField.getText()), Double.parseDouble( priceField.getText() ), 0);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ArtikelNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        		
        		// refresh panel
				JPanel panel = new ArtikelAddPanel(shop, pManager, frame);
        		pManager.changePanel(new MitarbeiterMenu(pManager, shop, frame), new ArtikelPanel(shop, frame, panel), panel);
        	}
        });
        
        JButton anlegen = new JButton("Artikel anlegen");
        cs.gridx = 1;
        cs.gridy = 3;
        this.add(anlegen, cs);
        
        anlegen.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		
        		try {
					shop.insertArtikel(titleTextfield.getText(), Integer.parseInt(stockField.getText()), Integer.parseInt(priceField.getText()));
					JPanel panel = new ArtikelAddPanel(shop, pManager, frame);
	        		pManager.changePanel(new MitarbeiterMenu(pManager, shop, frame), new ArtikelPanel(shop, frame, panel), panel);
					
				} catch (NumberFormatException e1) {
					// Bitte Nummer angeben
				} catch (ArtikelexistsException e1) {
					// artikel schon vorhanden!
				}
        	}
        });
	}
}
