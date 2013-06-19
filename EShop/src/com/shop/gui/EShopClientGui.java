package com.shop.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


import com.shop.logic.ServiceV;

public class EShopClientGui extends JFrame {
	
	private ServiceV shop;
	private PanelManager pManager;
	
	public EShopClientGui() {
		super("Gameshop");
		pManager = new PanelManager(this);
		
		try {
			this.shop = new ServiceV("SHOP");
		} catch (IOException e) {
			
		}
		
		this.initMenu();
		this.init();
	}
	
	
	private void init() {
	    setSize(1024, 786);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    pManager.changePanel(new JPanel(), new LoginPanel(shop, pManager), new JPanel());
	    
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
			} else {
				setVisible(false);
				dispose();
				System.exit(0);				
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new EShopClientGui();
	}

}
