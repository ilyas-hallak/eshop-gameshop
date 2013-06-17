package com.shop.gui;
import java.awt.BorderLayout;

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

import com.shop.exceptions.PersonNotFoundException;
import com.shop.gui.Mitarbeiter.MitarbeiterMenu;
import com.shop.gui.Mitarbeiter.MitarbeiterPanel;
import com.shop.logic.ServiceV;
import com.shop.valueobjects.Kunde;
import com.shop.valueobjects.Person;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 2306226515609595372L;
	private JLabel usernameLabel;
	private JTextField usernameField;
	
	private JLabel passwortLabel;
	private JPasswordField passwortField;
	
	private JButton loginBtn;
	
	private final ServiceV shop;
	private PanelManager pManager;
	
	public LoginPanel(final ServiceV shop, PanelManager pm) {
		super(new GridBagLayout());

		this.shop = shop;
		this.pManager = pm;
		
		GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;

        usernameLabel = new JLabel("Benutzername: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(usernameLabel, cs);

        usernameField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        this.add(usernameField, cs);

        passwortLabel = new JLabel("Passwort: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        this.add(passwortLabel, cs);

        passwortField = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        this.add(passwortField, cs);
        this.setBorder(new LineBorder(Color.GRAY));
        
        loginBtn = new JButton("Login");

        
        loginBtn.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
				Person person = new Person(usernameField.getText().trim(), passwortField.getText().trim());

					Person loggedPerson = null;
					try {
						loggedPerson = shop.login(person);
					} catch (PersonNotFoundException e1) {
						
					}
					
					if(loggedPerson instanceof Kunde) {
						usernameLabel.setText("KUNDE!");
					} else {
						usernameLabel.setText("Mitarbeiter!");
						pManager.changePanel(new MitarbeiterPanel());
						pManager.changePanel(new MitarbeiterMenu(pManager), BorderLayout.WEST);
					}
			}
        });
        
        this.add(loginBtn);
      
  
	}
}
