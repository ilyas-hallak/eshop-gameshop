package com.shop.cui;

import java.io.BufferedReader;
import java.io.IOException;

import com.shop.logic.EShopV;

public class EShopClientCUI {
	
	private EShopV shop;
	private BufferedReader in;
	
	
	/**
	 * Methode zur Ausführung der Hauptschleife:
	 * - Menü ausgeben
	 * - Eingabe des Benutzers einlesen
	 * - Eingabe verarbeiten und Ergebnis ausgeben
	 * (EVA-Prinzip: Eingabe-Verarbeitung-Ausgabe)
	 */
	public void run() {
		// Variable für Eingaben von der Konsole
		String input = ""; 
	
		// Hauptschleife der Benutzungsschnittstelle
		do {
			gibMenueAus();
			try {
				input = in.readLine();
				// verarbeiteEingabe(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (!input.equals("q"));
	}

	/* (non-Javadoc)
	 * 
	 * Interne (private) Methode zur Verarbeitung von Eingaben
	 * und Ausgabe von Ergebnissen.
	 */
	private void verarbeiteEingabe(String line) throws IOException {
		
//		// Eingabe bearbeiten:
//		if (line.equals("e")) { 
//			// lese die notwendigen Parameter, einzeln pro Zeile
//			System.out.print("Buchnummer > ");
//			String nummer = liesEingabe();
//			int bNr = Integer.parseInt(nummer);
//			System.out.print("Buchtitel  > ");
//			String titel = liesEingabe();
//			boolean ok = bib.fuegeBuchEin(titel, bNr);
//
//			if (ok)
//				System.out.println("Einfügen ok");
//			else
//				System.out.println("Fehler beim Einfügen");
//		}
//		else if (line.equals("a")) {
//			Vector liste = bib.gibAlleBuecher();
//			gibBuecherlisteAus(liste);
//		}
//		else if (line.equals("f")) {
//			System.out.print("Buchtitel  > ");
//			String titel = liesEingabe();
//			Vector liste = bib.sucheNachTitel(titel);
//			gibBuecherlisteAus(liste);
//		}
//		else if (line.equals("s")) {
//			bib.schreibeBuecher();
//		}
	}

	/* (non-Javadoc)
	 * 
	 * Interne (private) Methode zur Ausgabe des Menüs.
	 */
	private void gibMenueAus() {
		System.out.print("Befehle: \n  Artikel einfuegen: 'e'");
		System.out.print("         \n  Artikel ausgeben:  'a'");
		System.out.print("         \n  Artikel suchen:    'f'");
		System.out.print("         \n  Daten sichern:     's'");
		System.out.println("       \n  Beenden:           'q'");
		System.out.print("> "); // Prompt
		System.out.flush(); // ohne NL ausgeben
	}

	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("test");
	}

}
