package com.shop.gui.Kunde;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.shop.exceptions.ArtikelexistsException;
import com.shop.exceptions.BestandZuKleinException;
import com.shop.gui.PanelManager;
import com.shop.gui.Mitarbeiter.ArtikelAddPanel;
import com.shop.gui.Mitarbeiter.ArtikelPanel;
import com.shop.gui.Mitarbeiter.ArtikelTableModel;
import com.shop.gui.Mitarbeiter.MitarbeiterMenu;
import com.shop.logic.ServiceV;
import com.shop.valueobjects.Artikel;
import com.shop.valueobjects.Person;

public class ArtikelPanelKunde extends JPanel {

	public ArtikelPanelKunde(final ServiceV shop, final PanelManager pManager) {
		super(new GridLayout());
		final JTable table = new JTable( new ArtikelTableModel(shop.getAllArtikel()) );
		
		table.setBackground(Color.BLACK);
		table.setForeground(Color.RED);
		
		add( new JScrollPane(table) );
		
		GridBagConstraints cs = new GridBagConstraints();
		
        
		 /// Artikel zum Warenkorb
        JLabel stockLabel = new JLabel("Stückzahl: ");
        cs.gridx = 0;
        cs.gridy = 2;
 //       this.add(stockLabel, cs);

        final JTextField stockField = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 2;
    //    this.add(stockField, cs);
		
        JButton hinzu = new JButton("Artikel zum Warenkorb");
        cs.gridx = 1;
        cs.gridy = 3;
  //      this.add(hinzu, cs);
        
        JPanel boxPanel = new JPanel();
		boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
		
		boxPanel.add(hinzu);
		boxPanel.add(stockField);
		boxPanel.add(stockLabel, cs);
		
		add(boxPanel);
        
        
        hinzu.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		
        		
        		
        		
        		
        		try {
        			
        			
	        			
	        		int selectedRowIndex = table.getSelectedRow();
	                int selectedColumnIndex = table.getSelectedColumn();
	                
	           //     String selectedObject = (String) table.getModel().getValueAt(selectedRowIndex, selectedColumnIndex);
	                String selectedNumber = (String) table.getValueAt(selectedRowIndex, 0);
	                
	                List<Artikel> aList = shop.findArtikelByString(selectedNumber);
					// Artikel mit der gesuchten Artikelnummer ausgeben und
					// Artikel-Objekt erzeugen
					Artikel artikel = aList.get(0);

					int count = 1;
					if(stockField.getText().trim().length()>0){
						 count = Integer.parseInt(stockField.getText().trim());
					}

					shop.addArtikel(artikel, count);

	                
	                
	              //  add(new JLabel(selectedNumber));
        //			shop.addArtikel(String selectedObject(), Integer.parseInt(stockField.getText()));
        			
				pManager.changePanel(new JPanel(), new ArtikelPanelKunde(shop, pManager), new WarenkorbPanel(pManager, shop));
					
				} catch (NumberFormatException e1) {
					// Bitte Nummer angeben
		//		} catch (ArtikelexistsException e1) {
					// artikel schon vorhanden!
				} catch (BestandZuKleinException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
	
	}
	
}