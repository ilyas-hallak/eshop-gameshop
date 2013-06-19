package com.shop.gui;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.shop.exceptions.PersonNotFoundException;
import com.shop.gui.EShopClientGui.FileMenu;
import com.shop.gui.Kunde.ArtikelPanelKunde;
import com.shop.gui.Kunde.WarenkorbPanel;
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
	
	public LoginPanel(final ServiceV shop, PanelManager pm, final EShopClientGui frame) {
		super(new GridBagLayout());

		this.shop = shop;
		this.pManager = pm;
		
		// hide the menubar
		frame.getJMenuBar().setVisible(false);
		
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
						
						shop.setPerson(loggedPerson);
						
						if(loggedPerson instanceof Kunde) {
							pManager.changePanel(new JPanel(), new ArtikelPanelKunde(shop, pManager), new WarenkorbPanel(pManager, shop));
						} else {
			        		pManager.changePanel(new MitarbeiterMenu(pManager, shop, frame), new MitarbeiterPanel(frame), new JPanel());
						}
						
					} catch (PersonNotFoundException e1) {
						frame.setMessage("Logindaten falsch!");
					}
			}
        });
        
        this.add(loginBtn);
      
  
	}
}
