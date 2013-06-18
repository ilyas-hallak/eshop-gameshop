package com.shop.gui.Mitarbeiter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.shop.gui.PanelManager;
import com.shop.logic.ServiceV;

public class MitarbeiterMenu extends JPanel {
	
	private JButton artikel;
	private JButton mitarbeiter;
	private JButton ereignisse;
	
	private PanelManager pManager;
	
	public MitarbeiterMenu(PanelManager pm, final ServiceV shop) {
		super();
		
		this.pManager = pm;
		
		JPanel boxPanel = new JPanel();
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
		
		artikel = new JButton("Artikel");
		artikel.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		pManager.changePanel(new MitarbeiterMenu(pManager, shop), new ArtikelPanel(shop), new ArtikelAddPanel(shop, pManager));
        	}
		});
		
		mitarbeiter = new JButton("Mitarbeiter");
		ereignisse = new JButton("Ereignisse");

		boxPanel.add(artikel);
		boxPanel.add(mitarbeiter);
		boxPanel.add(ereignisse);
		
		add(boxPanel);
	}
}
