package com.shop.gui.Mitarbeiter;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.shop.gui.EShopClientGui;

import de.hsb.simon.client.net.ServiceVInterfaceImpl;

/**
 * Klasse fuer das ArtikelPanel mit der Artikelliste 
 *
 */
public class ArtikelPanel extends JPanel {
	/**
	 * Konstruktor fuer die ArikelPanel Klasse
	 * @param shop - Variable fuer den Zugriff auf den Server ueber die ServiceV
	 * @param frame - Variable zur Uebergabe der Benutzer Oberfleache (ClientGui)
	 * @param artikelInsertPanel - Artikeleinfuegen Panel mit uebergeben um schon vorhandene Artikel Informationen direkt in dessen Felder einzuschreiben
	 * wenn ein Artikel in der Artikelliste markiert wird
	 */
	public ArtikelPanel(ServiceVInterfaceImpl shop, EShopClientGui frame, JPanel artikelInsertPanel) {
		super(new GridBagLayout());
		// add(new JLabel("Artikelliste"));
		
		// neue Tabelle mit allen angelegten Artikeln aus dem shop erzeugen
		JTable table = new JTable( new ArtikelTableModel(shop.getAllArtikel()) );

		table.setAutoCreateRowSorter(true);
		
		// mackierten Artikel aus der Artikelliste an das ArtikelInsertPanel uebergeben
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(new MySelectionListener(table, artikelInsertPanel));
		
		add( new JScrollPane(table) );
	}
}

// mackierten Artikel aus der Artikeltabelle auslesen 
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
            // ein panel tiefer gehen in dem sich die massengut attribute befinden
            if(components[i].getClass().getName().toString().equals("javax.swing.JPanel")) {
            	
            	JPanel innerPanel = (JPanel) components[i];
                Component[] innerComps =  innerPanel.getComponents();
                for (int j = 0; j < innerComps.length; j++) {
                	
                	// checkbox selectieren, deselectieren
                	if(innerComps[j].getClass().getName().toString().equals("javax.swing.JCheckBox")) {
                    	JCheckBox check = (JCheckBox) innerComps[j];
                        if(table.getValueAt(row, 4) != "-") {
                        	// massengut artikel
                        	check.setSelected(true);
                        } else {
                        	check.setSelected(false);
                        }
                    }
                	// textfeld füllen, ansonsten leer lassen und ausblenden
                	if(innerComps[j].getClass().getName().toString().equals("javax.swing.JTextField")) {
                		 JTextField innerField = (JTextField) innerComps[j];
                		 if(table.getValueAt(row, 4) != "-") {
                         	// massengut artikel
                			 innerField.setText(table.getValueAt(row, 4).toString());
                        	 innerField.setVisible(true);

                         } else {
                        	 innerField.setText("");
                        	 innerField.setVisible(false);
                         }
                	}

                }
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

	/**
	  * Konstruktor des MySelectionListener
	  * @param table - Artikeltabelle um auslesen zu koennen
	  * @param artikelInsertPanel - Panel um Artikel in shop einzufuegen um die eingelesenen Daten an dieses zu ubergeben
	  */
	public MySelectionListener(JTable table, JPanel artikelInsertPanel) {
		this.table = table;
		this.panel = artikelInsertPanel;
	}

}
