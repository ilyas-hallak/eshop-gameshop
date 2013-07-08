package com.shop.gui.Mitarbeiter;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.shop.gui.EShopClientGui;
import com.shop.gui.PanelManager;
import com.shop.valueobjects.Artikel;
import com.shop.valueobjects.Ereignis;

import de.hsb.simon.client.net.ServiceVInterfaceImpl;

public class ArtikelHistoryPanel extends JPanel {
	ChartPanel cp;
	XYSeries series = new XYSeries("Bestandskurve");
	private ArrayList<Integer> data;
	
	public ArtikelHistoryPanel(ServiceVInterfaceImpl shop, EShopClientGui frame, PanelManager pManager, String days, Artikel a) {
		super(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		
		
		this.data = new ArrayList<Integer>();
		
		ArrayList<Ereignis> ereignisse = shop.getAllEreignisse();
		
        // aktuelles datum minus die ausgewaehlten tage
		Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DATE, Integer.parseInt(days) * -1); 
        Date pastDate = cal.getTime();
        
		// konstanten aus den ereignissen
		final String artikelGekauft = "Artikel wurde gekauft";
		final String neuerArtikel = "Neuer Artikel";
		final String neuerMassenArtikel = "Neuer Massenartikel Artikel";
		final String updateArtikel =  "Artikel aktualisiert";
		
		Iterator i = ereignisse.iterator();
		while(i.hasNext()) {
			Ereignis e = (Ereignis)i.next();
			System.out.println(e.getMessage());

			// passenden artikel aus der liste auslesen
			if(e.getArticle().getNr() == a.getNr()) {
				if(pastDate.getTime() < e.getDate().getTime()) {
					switch(e.getMessage()) {
						case neuerArtikel:
							data.add( e.getArticle().getStock() );
							break;
						case artikelGekauft:
							data.add( e.getArticle().getStock() - e.getCount() );
							break;
						case neuerMassenArtikel:
							data.add( e.getArticle().getStock() );
							break;
						case updateArtikel:
							data.add( e.getArticle().getStock() );
							break;
						default:
					}
				}
			}
		}
		
		Iterator it = this.data.iterator();
		int currentVal = 0;

		while(it.hasNext()) {
			int x = (int)it.next();
			System.out.println("x:" + x);
			series.add(currentVal, x);
			currentVal++;
		}
		
		cs.gridx = 0;
        cs.gridy = 0;
		
		// Fuegt dem Dataset die Serie zu!
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		// Generiert den Graphen
		JFreeChart chart = ChartFactory.createXYLineChart(a.getTitle(), // Titel
		// des
		// Graphen
		"Tage", // Beschriftung der X-Achse
		"Bestand", // Beschriftung der Y-Achse
		dataset, // Dataset
		PlotOrientation.VERTICAL, // Orientierung
		true, // Legende zeigen
		true, // Tooltips zeigen
		false);
		// Achsennummierung
		NumberAxis xAxis = new NumberAxis();
		xAxis.setTickUnit(new NumberTickUnit(1));
		// Gibs dem Chart!
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setDomainAxis(xAxis);
		cp = new ChartPanel(chart);
		add(cp);
	}
}
