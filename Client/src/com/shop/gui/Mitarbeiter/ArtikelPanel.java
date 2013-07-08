package com.shop.gui.Mitarbeiter;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.shop.gui.EShopClientGui;
import com.shop.gui.PanelManager;
import com.shop.valueobjects.Artikel;

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
	private JButton historyBtn;
	private PanelManager pManager;

	public ArtikelPanel(final ServiceVInterfaceImpl shop, final EShopClientGui frame, JPanel artikelInsertPanel, PanelManager pm) {
		super(new GridBagLayout());
		// add(new JLabel("Artikelliste"));
		
		// neue Tabelle mit allen angelegten Artikeln aus dem shop erzeugen
		this.pManager = pm;
		
		final JTable table = new JTable( new ArtikelTableModel(shop.getAllArtikel()) );

		// default sortierung
		table.setAutoCreateRowSorter(true);
		
		GridBagConstraints cs = new GridBagConstraints();
		
		cs.gridx = 0;
        cs.gridy = 0;
		add( new JScrollPane(table), cs);

		this.historyBtn = new JButton("Historie anzeigen");
		this.historyBtn.setVisible(false);
		cs.gridx = 0;
        cs.gridy = 1;
		add(historyBtn, cs);
		
		this.historyBtn.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		String days = JOptionPane.showInputDialog("fuer wie viele Tage soll die Historie angezeigt werden?");
        		int row = table.getSelectedRow();
        		String nr = table.getValueAt(row, 0).toString();
        		Artikel a = shop.findArtikelByString(nr).get(0);
        		
        		pManager.changePanel(new MitarbeiterMenu(pManager, shop, frame), new ArtikelHistoryPanel(shop, frame, pManager, days, a), new JPanel());
        	}
		});
		
		// tabellen event
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(new MySelectionListener(table, artikelInsertPanel, this.historyBtn));
	}
}

// mackierten Artikel aus der Artikeltabelle auslesen 
class MySelectionListener implements ListSelectionListener {

	JTable table;
	
	// panel right to insert data
	private JPanel panel;

	private JButton btn;
	
	@Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
        	return;
        }
        int row = table.getSelectedRow();
        
        Component[] components =  this.panel.getComponents();
        
        this.btn.setVisible(true);
        
        int textfieldCounter = 0;
        for (int i = 0; i < components.length; i++) {
            if(components[i].getClass().getName().toString().equals("javax.swing.JTextField")) {
                
                JTextField field = (JTextField) components[i];
                
                // die verschiedenen attributen in die entsprechenden textfeldern eintragen
                field.setText((String) table.getValueAt(row, textfieldCounter++));
                
            }
            if(components[i].getClass().getName().toString().equals("javax.swing.JLabel")) {
            	JLabel label = (JLabel) components[i];
	        	// falls kein massengut artikel ausgewaehlt wurder, soll das label ausgeblendet werden
	            if(table.getValueAt(row, 4) == "-" && label.getText() == "Massengutartikel") {
	            	label.setVisible(false);
	            } else {
	            	label.setVisible(true);
	            }
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
                        	check.setVisible(true);
                        } else {
                        	check.setSelected(false);
                        	check.setVisible(false);
                        }
                    }
                	// textfeld fuellen, ansonsten leer lassen und ausblenden
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
	public MySelectionListener(JTable table, JPanel artikelInsertPanel, JButton historyBtn) {
		this.table = table;
		this.panel = artikelInsertPanel;
		this.btn = historyBtn;
	}

}
