package com.shop.gui.Mitarbeiter;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.shop.gui.EShopClientGui;

import de.hsb.simon.client.net.ServiceVInterfaceImpl;

public class ArtikelPanel extends JPanel {
	public ArtikelPanel(ServiceVInterfaceImpl shop, EShopClientGui frame, JPanel artikelInsertPanel) {
		super(new GridBagLayout());
		// add(new JLabel("Artikelliste"));
		
		JTable table = new JTable( new ArtikelTableModel(shop.getAllArtikel()) );
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(new MySelectionListener(table, artikelInsertPanel));
		
		add( new JScrollPane(table) );
	}
}

class MySelectionListener implements ListSelectionListener {

	JTable table;
	
	// panel right to insert data
	private JPanel panel;
	
	@Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
        	return;
        }
        // System.out.println();
        int row = table.getSelectedRow();
       
        
        Component[] components =  this.panel.getComponents();

        int textfieldCounter = 0;
        for (int i = 0; i < components.length; i++) {
        	
            if(components[i].getClass().getName().toString().equals("javax.swing.JTextField")) {
                
                JTextField field = (JTextField) components[i];
                
                // die verschiedenen attributen in die entsprechenden textfeldern eintragen
                field.setText((String) table.getValueAt(row, textfieldCounter++));
            }
            if(components[i].getClass().getName().toString().equals("javax.swing.JButton")) {
            	// show save button
            	JButton button = (JButton) components[i];
            	if(button.getText() == "Speichern") {
            		button.setVisible(true);
            	}
            }
        }
       
        
    }
	public MySelectionListener(JTable table, JPanel artikelInsertPanel) {
		this.table = table;
		this.panel = artikelInsertPanel;
	}

}
