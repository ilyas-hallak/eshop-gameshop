package com.shop.gui.Kunde;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

import com.shop.exceptions.BestandZuKleinException;
import com.shop.gui.EShopClientGui;
import com.shop.gui.PanelManager;
import com.shop.gui.Mitarbeiter.ArtikelTableModel;
import com.shop.valueobjects.Artikel;

import de.hsb.simon.client.net.ServiceVInterfaceImpl;

public class ArtikelPanelKunde extends JPanel {
	
	final JTable table;
	
	public ArtikelPanelKunde(final ServiceVInterfaceImpl shop, final PanelManager pManager, final EShopClientGui frame) {
		super(new GridLayout());
		ArtikelPanelKunde self = this;
		final ArtikelTableModel model = new ArtikelTableModel(shop.getAllArtikel());
		table = new JTable( model );
		
		// show menubar
		frame.getJMenuBar().setVisible(true);
		
		// hide save item
		frame.getJMenuBar().getMenu(0).getItem(0).setVisible(false);
		
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JTable table = (JTable)e.getSource();
				int row = table.getSelectedRow();
				
				// get article id
				int id = (int) table.getValueAt(row, 0);
				Artikel artikel = shop.findArtikelByString(String.valueOf(id)).get(0);
				
				artikel.setTitle( (String)table.getValueAt(row, 1) );
				artikel.setPrice( (double)table.getValueAt(row, 2) );
				artikel.setStock( (int)table.getValueAt(row, 2) );
				
				shop.saveArtikel();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
		});
		
		add( new JScrollPane(table) );
		
		GridBagConstraints cs = new GridBagConstraints();
		
        
		 /// Artikel zum Warenkorb
        JLabel stockLabel = new JLabel("St��ckzahl: ");
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
        			
				pManager.changePanel(new JPanel(), new ArtikelPanelKunde(shop, pManager, frame), new WarenkorbPanel(pManager, shop));
					
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