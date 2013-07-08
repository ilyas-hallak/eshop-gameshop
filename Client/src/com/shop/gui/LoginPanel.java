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

import com.shop.exceptions.PersonNotFoundException;
import com.shop.gui.Kunde.ArtikelPanelKunde;
import com.shop.gui.Kunde.WarenkorbPanel;
import com.shop.gui.Mitarbeiter.MitarbeiterMenu;
import com.shop.gui.Mitarbeiter.MitarbeiterPanel;
import com.shop.valueobjects.Kunde;
import com.shop.valueobjects.Person;

import de.hsb.simon.client.net.ServiceVInterfaceImpl;

/**
* @description Klasse für die Definierung des Login-Panels erbt von JPanel
*
*/
public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 2306226515609595372L;
	private JLabel usernameLabel;
	private JTextField usernameField;
	
	private JLabel passwortLabel;
	private JPasswordField passwortField;
	
	private JButton loginBtn;
	
	private final ServiceVInterfaceImpl shop;
	private PanelManager pManager;
	private JButton registerBtn;
	
	/**
	* @description Konstruktor der LoginPanel-Klasse
	* @param shop - Variable fuer den Zugriff auf den Server ueber die ServiceV
	* @param pm - Variable fuer den PanelManager, zum autauschen der Panels
	* @param frame - Variable zur Uebergabe der Benutzer Oberfleache (ClientGui)
	*/
	public LoginPanel(final ServiceVInterfaceImpl shop, PanelManager pm, final EShopClientGui frame) {
		super(new GridBagLayout());

		this.shop = shop;
		this.pManager = pm;
		
		// hide the menubar
		frame.getJMenuBar().setVisible(false);
		
		GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        
        // BENUTZERNAME LABEL 
        usernameLabel = new JLabel("Benutzername: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        this.add(usernameLabel, cs);
        
        // BENUTZERNAME FELD
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
        	/**
        	* @description erzeugen eines Person-Objekts beim betaetigen des Login Buttons
        	* und versuchen diese Person auf dem Server einzuloggen (ggf. Exception bei falschen Daten)
        	* ueberprüfen ob Kunde oder Mitarbeiter und jweiliges Menue ausgeben mit Hilfe des Panel-Managers
        	*/
        	@Override
			public void actionPerformed(ActionEvent e) {
				Person person = new Person(usernameField.getText().trim(), passwortField.getText().trim());

					Person loggedPerson = null;
					try {
						loggedPerson = shop.login(person);
						
						frame.setPerson(loggedPerson);
						
						if(loggedPerson instanceof Kunde) {
							
							pManager.changePanel(new JPanel(), new ArtikelPanelKunde(shop, pManager, frame), new WarenkorbPanel(pManager, shop, frame));
						} else {
			        		pManager.changePanel(new MitarbeiterMenu(pManager, shop, frame), new MitarbeiterPanel(frame), new JPanel());
						}
						
					} catch (PersonNotFoundException e1) {
						frame.setMessage("Logindaten falsch!");
					}
			}
        });
        
        cs.gridx = 1;
        cs.gridy = 2;
        this.add(loginBtn, cs);
        
        registerBtn = new JButton("Registieren");
        
        registerBtn.addActionListener(new ActionListener() {
        	/**
        	* @description Registrier Button - wechselt per Klick Panels zum Registerpanel
        	*/
        	@Override
			public void actionPerformed(ActionEvent e) {
        		pManager.changePanel(new JPanel(), new RegisterPanel(shop, pManager, frame), new JPanel());
        	}
        });
        
        cs.gridx = 1;
        cs.gridy = 3;
        this.add(registerBtn, cs);
	}
}
