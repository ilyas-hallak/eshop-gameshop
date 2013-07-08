package de.hsb.simon.server.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.hsb.simon.server.net.ServerInterfaceImpl;
import de.root1.simon.exceptions.NameBindingException;

public class ServerGUI extends JFrame {

	private static final long serialVersionUID = 3256901050204363805L;
	
	/** Netzwerk */
	private ServerInterfaceImpl connection;
	
	/** GUI-Komponenten */
	private JLabel status;		// Status des Servers
	private JButton start;		// Button zum Starten des Servers
	private JButton stop;		// Button zum Stoppen des Servers

	/**
	 * @description Konstruktor
	 */
	public ServerGUI() {
		super("Simon Server");

		try {
			connection = new ServerInterfaceImpl();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		// Initialisiere GUI
		this.initComponents();

		// Fuege Listener hinzu
		this.initListeners();

		// Setze Frame sichtbar
		this.setVisible(true);
	}

	/**
	 * Initialisiert die GUI-Komponenten
	 */
	private void initComponents() {
		// Allgemeine Eigenschaften
		this.setSize(300, 100);
		this.setLayout(new GridLayout(1, 3));
		
		// Komponenten initialisieren
		status = new JLabel("Status: stopped");
		start = new JButton("Start");
		stop = new JButton("Stop");
		
		// Einfuegen (von oben links nach unten rechts)
		this.add(status);
		this.add(start);
		this.add(stop);
	}

	/**
	 * Initialisiert die Listener fuer einige Komponenten
	 */
	private void initListeners() {
		// Listener zum Starten des Servers
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					connection.startServer();
					status.setText("Status: gestartet");
				} catch (IOException | NameBindingException e) {
					JOptionPane.showMessageDialog(ServerGUI.this, e.getMessage());
				}
			}
		});
		
		// Listener zum Beenden des Servers
		stop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				connection.stopServer();
				status.setText("Status: gestoppt");
			}
		});
	}
	
	/**
	 * 
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws UnsupportedLookAndFeelException
	 */
	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new ServerGUI();
	}
}
