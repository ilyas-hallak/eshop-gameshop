package com.shop.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import de.hsb.simon.client.net.ClientInterfaceImpl;
import de.hsb.simon.client.net.ServiceVInterfaceImpl;
import de.hsb.simon.commons.ServiceVInterface;
import de.root1.simon.exceptions.EstablishConnectionFailed;
import de.root1.simon.exceptions.LookupFailedException;

public class EShopClientGui extends JFrame {
	
	private ServiceVInterfaceImpl shop;
	private PanelManager pManager;
	private EShopClientGui self;
	private JLabel msgLabel;
	
	private ClientInterfaceImpl net;
	
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
	
	
	private void init() {
	    setSize(1024, 786);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    this.msgLabel = pManager.changePanel(new JPanel(), new LoginPanel(shop, pManager, this), new JPanel());
	    
	    setVisible(true);
	}

	private void initMenu() {
		JMenu fileMenu = new FileMenu();
		
		JMenuBar bar = new JMenuBar();
		bar.add(fileMenu);
		
		setJMenuBar(bar);
	}
	
	
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

		@Override
		public void actionPerformed(ActionEvent ae) {
			if (ae.getActionCommand().equals("Save")) {
				shop.savedata();
			} else if(ae.getActionCommand().equals("Logout")) {
        		pManager.changePanel(new JPanel(), new LoginPanel(shop, pManager, self), new JPanel());
			} else {
				setVisible(false);
				dispose();
				System.exit(0);				
			}
		}
	}
	
	
	public void setMessage(String msg) {
		this.msgLabel.setText(msg);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new EShopClientGui();
	}

}
