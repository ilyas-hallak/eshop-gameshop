package com.shop.gui;

import java.awt.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		
		this.init();
		
	}
	
	
	private void init() {
	    setSize(1024, 786);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    JPanel north = new JPanel(new CardLayout());
	    north.add(new LogoPanel());
	    
	    JPanel south = new JPanel();
	    south.add(new JLabel("TEXT"));

	    
	    JPanel east = new JPanel(new CardLayout());
	    JPanel west = new JPanel(new CardLayout());
	    JPanel center = new JPanel(new CardLayout());
	    
	    JPanel jP = new JPanel();
	    jP.setLayout(new BoxLayout(jP, BoxLayout.PAGE_AXIS));


	    add(north, BorderLayout.NORTH);
	    
	    south.setPreferredSize(new Dimension(30, 100)); //Angabe für Breite wird vom BorderLayout ignoriert
	    add(south, BorderLayout.SOUTH);
	    
	    east.setPreferredSize(new Dimension(200, 30)); //Angabe für Höhe wird vom BorderLayout ignoriert
	    add(east, BorderLayout.EAST);
	    
	    west.setPreferredSize(new Dimension(200, 30)); //Angabe für Höhe wird vom BorderLayout ignoriert
	    add(jP, BorderLayout.WEST);
	    
	    center.setPreferredSize(new Dimension(30, 30)); //Angaben für Breite und Höhe werden vom BorderLayout ignoriert
        
	    JPanel panelCenter = new LoginPanel(this.shop, pManager);
	    
	    center.add(panelCenter);
	    
	    add(center, BorderLayout.CENTER);
	    
	    setVisible(true);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new EShopClientGui();
	}

}
