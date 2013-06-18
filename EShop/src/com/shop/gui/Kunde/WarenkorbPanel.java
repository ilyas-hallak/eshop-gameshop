package com.shop.gui.Kunde;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.shop.gui.PanelManager;
import com.shop.gui.Mitarbeiter.ArtikelPanel;
import com.shop.gui.Mitarbeiter.MitarbeiterMenu;

public class WarenkorbPanel extends JPanel {

	private JButton kaufen;

	private PanelManager pManager;
	
	public WarenkorbPanel(PanelManager pm) {
		super();
		add(new JLabel("Warenkorb"));
	//	setPreferredSize(new Dimension(30, 100));
		
		this.pManager = pm;
		
		JPanel boxPanel = new JPanel();
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.X_AXIS));
		
		kaufen = new JButton("Kaufen");
		kaufen.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		pManager.changePanel(new RechnungPanel(), new JPanel(), new JPanel());
        	}
		});
		

		boxPanel.add(kaufen);
		
		add(boxPanel);
		
	}


}
