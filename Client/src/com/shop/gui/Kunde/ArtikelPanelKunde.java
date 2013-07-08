package com.shop.gui.Kunde;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.shop.exceptions.BestandZuKleinException;
import com.shop.exceptions.MassenArtikelAnzahlException;
import com.shop.gui.EShopClientGui;
import com.shop.gui.PanelManager;
import com.shop.gui.Mitarbeiter.ArtikelTableModel;
import com.shop.valueobjects.Artikel;
import com.shop.valueobjects.Kunde;

import de.hsb.simon.client.net.ServiceVInterfaceImpl;

/**
* @description Klasse fuer Artikellist im Kundenmenue, um Artikel dem Warenkorb hinzuzufuegen 
*/
public class ArtikelPanelKunde extends JPanel {
	
	final JTable table;
	
	/**
	* @description Konstruktor ArtikelPanelKunde
	* @param shop - Variable fuer den Zugriff auf den Server ueber die ServiceV
	* @param pManager - Variable fuer den PanelManager, zum autauschen der Panels
	* @param frame - Variable zur Uebergabe der Benutzer Oberfleache (ClientGui)
	*/
	public ArtikelPanelKunde(final ServiceVInterfaceImpl shop, final PanelManager pManager, final EShopClientGui frame) {
		super(new GridLayout(2, 1));

		ArtikelPanelKunde self = this;
		final ArtikelTableModel model = new ArtikelTableModel(shop.getAllArtikel());
		table = new JTable( model );
		
		table.setAutoCreateRowSorter(true);
		
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
				//System.out.println();
				/*int id = Integer.parseInt(table.getValueAt(row, 0).toString());
				
				Artikel artikel = shop.findArtikelByString(String.valueOf(id)).get(0);
				
				artikel.setTitle( (String)table.getValueAt(row, 1) );
				artikel.setPrice( Double.parseDouble( table.getValueAt(row, 2).toString() ) );
				
				artikel.setStock( Integer.parseInt(table.getValueAt(row, 3).toString()) );
				
				//shop.saveArtikel();*/
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
			
		});
		
		GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
		
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        add( new JScrollPane(table));
        
		JPanel boxPanel = new JPanel( new FlowLayout() );

		 /// Artikel zum Warenkorb
        JLabel stockLabel = new JLabel("Stückzahl: ");
        cs.gridx = 0;
        cs.gridy = 1;
        boxPanel.add(stockLabel);

        final JTextField stockField = new JTextField(5);
        cs.gridx = 1;
        cs.gridy = 1;
        boxPanel.add(stockField, cs);
		
        JButton hinzu = new JButton("Artikel zum Warenkorb");
        cs.gridx = 3;
        cs.gridy = 1;
        boxPanel.add(hinzu, cs);
        
		add(boxPanel);
        
        hinzu.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		
        		try {
	        			
	        		int selectedRowIndex = table.getSelectedRow();
	                int selectedColumnIndex = table.getSelectedColumn();
	                
	                String selectedNumber = (String) table.getValueAt(selectedRowIndex, 0);
	                
	                List<Artikel> aList = shop.findArtikelByString(selectedNumber);
					// Artikel mit der gesuchten Artikelnummer ausgeben und
					// Artikel-Objekt erzeugen
					Artikel artikel = aList.get(0);
					
					// standard anzahl 1
					int count = 1;
					// falls eine anzahl angegeben ist, wird diese genommen
					if(stockField.getText().trim().length()>0){
						 count = Integer.parseInt(stockField.getText().trim());
					}

					// warenkorb hinzufuegen
					// kunden objekt mit neuen daten refreshen
					Kunde k = shop.addArtikelToCart(artikel, count, (Kunde)frame.getPerson());
					frame.setPerson(k);
        			
					pManager.changePanel(new JPanel(), new ArtikelPanelKunde(shop, pManager, frame), new WarenkorbPanel(pManager, shop, frame));
					
				} catch (NumberFormatException e1) {
					frame.setMessage("Bitte bei Stückzahl eine Nummer angeben");
				} catch (BestandZuKleinException e1) {
					frame.setMessage(e1.getMessage());
				} catch (MassenArtikelAnzahlException e1) {
					frame.setMessage(e1.getMessage());
				}
        	}
        });
	
	}
	
}