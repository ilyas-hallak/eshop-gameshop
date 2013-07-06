package com.shop.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.shop.exceptions.CustomerExistsExeption;
import com.shop.exceptions.PersonNotFoundException;
import com.shop.gui.Kunde.ArtikelPanelKunde;
import com.shop.gui.Kunde.WarenkorbPanel;
import com.shop.valueobjects.Kunde;

import de.hsb.simon.client.net.ServiceVInterfaceImpl;

public class RegisterPanel extends JPanel {
	
	private final ServiceVInterfaceImpl shop;
	private PanelManager pManager;
	private JLabel emailLabel;
	private JTextField emailField;
	private JLabel passwortLabel;
	private JPasswordField passwortField;
	private JLabel nameLabel;
	private JTextField nameField;
	private JLabel adressLabel;
	private JTextField adressField;
	private JButton registerBtn;
	private JButton abortBtn;
	
	public RegisterPanel(final ServiceVInterfaceImpl shop, PanelManager pm, final EShopClientGui frame) {
		super(new GridBagLayout());
		
		this.shop = shop;
		this.pManager = pm;
		
		// hide the menubar
		frame.getJMenuBar().setVisible(false);
		
		GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        
        /*
		this.address = address;
         */
        
        // MAIL
        emailLabel = new JLabel("E-Mail: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(emailLabel, cs);

        emailField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        this.add(emailField, cs);
        
        // NAME
        nameLabel = new JLabel("Name: ");
        cs.gridx = 0;
        cs.gridy = 1;
        this.add(nameLabel, cs);

        nameField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        this.add(nameField, cs);
        
        // ADRESSE
        adressLabel = new JLabel("Adresse: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        this.add(adressLabel, cs);

        adressField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        this.add(nameField, cs);
        
        
        // PASSWORT
        passwortLabel = new JLabel("Passwort: ");
        cs.gridx = 0;
        cs.gridy = 3;
        cs.gridwidth = 1;
        this.add(passwortLabel, cs);

        passwortField = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 3;
        cs.gridwidth = 2;
        this.add(passwortField, cs);
        this.setBorder(new LineBorder(Color.GRAY));
        
        registerBtn = new JButton("Registrieren");
        
        registerBtn.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		if(nameField.getText().length() == 0 || emailField.getText().length() == 0 || passwortField.getText().length() == 0 || adressField.getText().length() == 0) {
        			frame.setMessage("Bitte fülle alle Felder aus!");
        			return;
        		}
        			
        		
        		Kunde k = new Kunde(nameField.getText(), emailField.getText(), adressField.getText(), passwortField.getText());
        		try {
					shop.insertCustomer(k);

					Kunde loggedKunde = (Kunde)shop.login(k);
					
					frame.setMessage("Hallo " + k.getName() + "!");
					pManager.changePanel(new JPanel(), new ArtikelPanelKunde(shop, pManager, frame), new WarenkorbPanel(pManager, shop));

				} catch (CustomerExistsExeption e1) {
					frame.setMessage(e1.getMessage());
				} catch (PersonNotFoundException e1) {
					frame.setMessage(e1.getMessage());
				} 
        	}
        });
        
        cs.gridx = 1;
        cs.gridy = 4;
        this.add(registerBtn, cs);
        
        abortBtn = new JButton("Abbrechen");
        abortBtn.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		pManager.changePanel(new JPanel(), new LoginPanel(shop, pManager, frame), new JPanel());
        	}
        });
        cs.gridx = 1;
        cs.gridy = 5;
        this.add(abortBtn, cs);
	}
}
