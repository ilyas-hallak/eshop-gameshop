package com.shop.gui.Kunde;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.shop.exceptions.ArtikelNotFoundException;
import com.shop.gui.Mitarbeiter.ArtikelTableModel;
import com.shop.valueobjects.Artikel;
import com.shop.valueobjects.Kunde;
import com.shop.valueobjects.Rechnung;

import de.hsb.simon.client.net.ServiceVInterfaceImpl;

public class RechnungPanel extends JPanel {

	public RechnungPanel(ServiceVInterfaceImpl shop) {
		super();
		add(new JLabel("Rechnung"));
		
		
		Rechnung r = shop.buy((Kunde)shop.getPerson());
		
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
		
		try {
			shop.complete();
			
		} catch (ArtikelNotFoundException e) {
			// frame.setMessage("");
			
			// showMessageDialog(e.getMessage());
		}
	}



}
