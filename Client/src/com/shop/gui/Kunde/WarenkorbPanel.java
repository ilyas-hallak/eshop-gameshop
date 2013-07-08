package com.shop.gui.Kunde;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.shop.gui.EShopClientGui;
import com.shop.gui.PanelManager;
import com.shop.gui.Mitarbeiter.ArtikelTableModel;
import com.shop.valueobjects.Artikel;
import com.shop.valueobjects.Kunde;

import de.hsb.simon.client.net.ServiceVInterfaceImpl;

/**
* @description Klasse fuer Warenkorb Bereich, erbt von JPanel
*/
public class WarenkorbPanel extends JPanel {

	private JButton kaufen;

	private PanelManager pManager;

	private JButton clearnBtn;

	private JButton deleteArtikelBtn;

	/**
	* @description Konstruktor des Warenkorb Panels
	* @param pm - Variable fuer den PanelManager, zum autauschen der Panels
	* @param shop - Variable fuer den Zugriff auf den Server ueber die ServiceV
	*/
	public WarenkorbPanel(PanelManager pm, final ServiceVInterfaceImpl shop, final EShopClientGui frame) {
		super(new GridLayout(2, 1));
		this.pManager = pm;
		Map<Artikel, Number> model = shop.getAllArtikelFromCart((Kunde)frame.getPerson());
		final JTable warentable = new JTable( new ArtikelTableModel( model ) );
		
		warentable.setBackground(Color.WHITE);
		warentable.setForeground(Color.GREEN);
		warentable.setPreferredSize(new Dimension(200,400));
		
		add( new JScrollPane(warentable) );
		
		JPanel boxPanel = new JPanel(new FlowLayout());
		
		boxPanel.add(new JLabel("Warenkorb"));
		
		
		kaufen = new JButton("Kaufen");
		kaufen.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		pManager.changePanel(new RechnungPanel(shop, frame, pManager), new JPanel(), new JPanel());
        	}
		});

		boxPanel.add(kaufen);
		
		this.clearnBtn = new JButton("Warenkorb leeren");
		this.clearnBtn.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		Kunde k = (Kunde) frame.getPerson();
        		k.getCart().complete();
				pManager.changePanel(new JPanel(), new ArtikelPanelKunde(shop, pManager, frame), new WarenkorbPanel(pManager, shop, frame));

        	}
		});
		boxPanel.add(this.clearnBtn);
		
		this.deleteArtikelBtn = new JButton("Artikel entfernen");
		this.deleteArtikelBtn.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
        		int selectedRowIndex = warentable.getSelectedRow();
                int selectedColumnIndex = warentable.getSelectedColumn();
                
                String selectedNumber = (String) warentable.getValueAt(selectedRowIndex, 0);
                
				Artikel artikel = shop.findArtikelByString(selectedNumber).get(0);
				
				Kunde k = shop.removeArtikelFromCart(artikel, (Kunde)frame.getPerson());
				// get refreshed kunde data
				frame.setPerson(k);
				
				pManager.changePanel(new JPanel(), new ArtikelPanelKunde(shop, pManager, frame), new WarenkorbPanel(pManager, shop, frame));

        	}
		});
		boxPanel.add(this.deleteArtikelBtn);
		
		add(boxPanel);

	}


}
