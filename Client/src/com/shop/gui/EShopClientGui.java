package com.shop.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.shop.valueobjects.Person;

import de.hsb.simon.client.net.ClientInterfaceImpl;
import de.hsb.simon.client.net.ServiceVInterfaceImpl;
import de.root1.simon.exceptions.EstablishConnectionFailed;
import de.root1.simon.exceptions.LookupFailedException;

/**
* @description Hauptklasse fuer die Benutzer Oberflaeche (Client)
*/
public class EShopClientGui extends JFrame {
	
	private ServiceVInterfaceImpl shop;
	private PanelManager pManager;
	private EShopClientGui self;
	private JLabel msgLabel;
	// person wird nach dem einloggen gespeichert
	private Person person; 
	private ClientInterfaceImpl net;
	
	/**
	* @description Konstruktor der ClientGui
	* mit dem Namen Gameshop und eigenem Panelmanager
	* baut Verbindung zum Server auf und fuegt das Client-Menue ein
	*/
	public EShopClientGui() {
		super("Gameshop");
		pManager = new PanelManager(this);
		self = this;
		
		this.net = new ClientInterfaceImpl(new JTextArea());
		
		try {
			net.connectToServer();
		} catch (UnknownHostException | LookupFailedException | EstablishConnectionFailed e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		
		this.shop = net.getShop();
		
		this.initMenu();
		this.init();
	}
	
	/**
	* @description Fenster fuer Kundenlogin Panel erzeugen
	*/
	private void init() {
	    setSize(1024, 786);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    this.msgLabel = pManager.changePanel(new JPanel(), new LoginPanel(shop, pManager, this), new JPanel());
	    
	    setVisible(true);
	}

	/**
	* @description Menuebar fuer das Filemenue
	*/
	private void initMenu() {
		JMenu fileMenu = new FileMenu();
		
		JMenuBar bar = new JMenuBar();
		bar.add(fileMenu);
		
		setJMenuBar(bar);
	}
	
	/**
	* @description Filemenue zum speichern, ausloggen und schliessen des Programms mit Listener
	*/
	class FileMenu extends JMenu implements ActionListener {
		public FileMenu() {
			super("File");
			
			JMenuItem saveItem = new JMenuItem("Save");
			add(saveItem);
			saveItem.addActionListener(this);
			
			JMenuItem logout = new JMenuItem("Logout");
			add(logout);
			logout.addActionListener(this);
			
			addSeparator();
			JMenuItem quitItem = new JMenuItem("Quit");
			add(quitItem);
			quitItem.addActionListener(this);
			
			this.setVisible(true);
		}
		/**
		* @description Implementierung der Files was bei der jeweiligen Benutzung ausgefuehrt werden soll
		*/
		@Override
		public void actionPerformed(ActionEvent ae) {
			if (ae.getActionCommand().equals("Save")) {
				shop.savedata();
			} else if(ae.getActionCommand().equals("Logout")) {
        		pManager.changePanel(new JPanel(), new LoginPanel(shop, pManager, self), new JPanel());
			} else {
				setVisible(false);
				dispose();
				shop.savedata();
				System.exit(0);				
			}
		}
	}
	
	/**
	* 
	* @param msg - Nachricht der jeweiligen Exception
	*/
	public void setMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
	
	/**
	* @param args
	* @description Hauptausfuehrung zum erzeugen einer Oberfleache fuer den Benutzer (Client)
	*/
	public static void main(String[] args) {
		new EShopClientGui();
	}
	
	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

}
