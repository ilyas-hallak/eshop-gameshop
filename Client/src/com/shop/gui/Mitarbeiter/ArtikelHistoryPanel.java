package com.shop.gui.Mitarbeiter;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.shop.gui.EShopClientGui;
import com.shop.gui.PanelManager;
import com.shop.valueobjects.Artikel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import de.hsb.simon.client.net.ServiceVInterfaceImpl;

public class ArtikelHistoryPanel extends JPanel {
	ChartPanel cp;
	XYSeries series = new XYSeries("Bestandskurve");
	
	public ArtikelHistoryPanel(ServiceVInterfaceImpl shop,
			EShopClientGui frame, PanelManager pManager, String days, Artikel a) {
		super(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		
		int currentVal = 0;
		for (int i = Integer.parseInt(days); i >= 0; i--) {
			series.add(i, currentVal);
			currentVal++;
		}
		cs.gridx = 0;
        cs.gridy = 0;
		
		// Fügt dem Dataset die Serie zu!
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
