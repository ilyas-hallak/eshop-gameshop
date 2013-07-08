package com.shop.gui.Kunde;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
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
import com.shop.valueobjects.Rechnung;

import de.hsb.simon.client.net.ServiceVInterfaceImpl;

/**
* @description Klasse fuer die Rechnung nach Kauf des Warenkorbs
*
*/
public class RechnungPanel extends JPanel {

	private EShopClientGui frame;
	private JButton backBtn;
	
	/**
	* @description Konstruktor des Rechnungspanel
	* @param shop - Variable fuer den Zugriff auf den Server ueber die ServiceV uebergeben
	*/
	public RechnungPanel(final ServiceVInterfaceImpl shop, final EShopClientGui frame, final PanelManager pm) {
		super(new GridLayout(6, 1));
		add(new JLabel("Rechnung"));
		
		this.frame = frame;
		
		Rechnung r = shop.buy((Kunde)frame.getPerson());
		
		DateFormat df2 = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		String formattedDate = df2.format(r.getDate().getTime());
		
		add(new JLabel("Datum " + formattedDate));
		
		add(new JLabel("Name " + r.getKunde().getName()));
		
		final JTable table = new JTable( new ArtikelTableModel(r.getArticleList()) );
		
		add( new JScrollPane(table) );
		
		Iterator i = r.getArticleList().entrySet().iterator();
		
		double sum = 0.0d;
		
		while(i.hasNext()) {
			Map.Entry<Artikel, Number> pair = (Map.Entry<Artikel, Number>) i.next();
			sum += pair.getKey().getPrice() * pair.getValue().intValue();
		}
		
		add(new JLabel("Summe: " + sum));
		
		Kunde k = (Kunde)frame.getPerson();
		k.getCart().complete();
		
		// zurueck button
		this.backBtn = new JButton("Zurueck");
		backBtn.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
				pm.changePanel(new JPanel(), new ArtikelPanelKunde(shop, pm, frame), new WarenkorbPanel(pm, shop, frame));
        	}
		});
		add(this.backBtn);
	}



}
