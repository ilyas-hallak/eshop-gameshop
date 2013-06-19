package com.shop.gui.Kunde;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.shop.gui.PanelManager;
import com.shop.gui.Mitarbeiter.ArtikelTableModel;
import com.shop.logic.ServiceV;

public class ArtikelPanelKunde extends JPanel {

	public ArtikelPanelKunde(final ServiceV shop, final PanelManager pManager) {
		super(new GridLayout());
		final JTable table = new JTable( new ArtikelTableModel(shop.getAllArtikel()) );
		add( new JScrollPane(table) );
		
		GridBagConstraints cs = new GridBagConstraints();
		
        
		 /// Artikel zum Warenkorb
        JLabel stockLabel = new JLabel("Stückzahl: ");
        cs.gridx = 0;
        cs.gridy = 2;
        this.add(stockLabel, cs);

        final JTextField stockField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        this.add(stockField, cs);
		
        JButton hinzu = new JButton("Artikel zum Warenkorb");
        cs.gridx = 1;
        cs.gridy = 3;
        this.add(hinzu, cs);
        
        
        hinzu.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		
        		
        		
        		try {
	        			
	        		int selectedRowIndex = table.getSelectedRow();
	                int selectedColumnIndex = table.getSelectedColumn();
	                
	                String selectedObject = (String) table.getModel().getValueAt(selectedRowIndex, selectedColumnIndex);
	                
	                
	                add(new JLabel(selectedObject));
        //			shop.addArtikel(String selectedObject(), Integer.parseInt(stockField.getText()));
        			
					pManager.changePanel(new JPanel(), new ArtikelPanelKunde(shop, pManager), new WarenkorbPanel(pManager, shop));
					
				} catch (NumberFormatException e1) {
					// Bitte Nummer angeben
		//		} catch (ArtikelexistsException e1) {
					// artikel schon vorhanden!
				}
        	}
        });
	
	}
	
}