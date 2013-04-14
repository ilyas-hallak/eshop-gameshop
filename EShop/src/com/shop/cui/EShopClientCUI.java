package com.shop.cui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.shop.logic.EShopV;
import com.shop.valueobjects.Artikel;

public class EShopClientCUI {
	
	private EShopV shop;
	private BufferedReader in;
	
	
	public EShopClientCUI(String file) throws IOException {
		// die EShop-Verwaltung nimmt die ersten Aufgaben entgegen 
		// und leitet diese weiter an den Service
		shop = new EShopV(file);

		// Stream-Objekt fuer Texteingabe ueber Konsolenfenster erzeugen
		in = new BufferedReader(new InputStreamReader(System.in));
	}
	
	
	/* (non-Javadoc)
	 * 
	 * Interne (private) Methode zur Ausgabe des Menüs.
	 */
	private void outputMenu() {
		System.out.print("Befehle: \n  Artikel einfuegen: 'e'");
		System.out.print("         \n  Artikel ausgeben:  'a'");
		System.out.print("         \n  Artikel suchen:    'f'");
		System.out.print("         \n  Daten sichern:     's'");
		System.out.println("       \n  Beenden:           'q'");
		System.out.print("> "); // Prompt
		System.out.flush(); // ohne NL ausgeben
	}
	
	
	/* (non-Javadoc)
	 * 
	 * Interne (private) Methode zum Einlesen von Benutzereingaben.
	 */
	private String readInput() throws IOException {
		// einlesen von Konsole
		return in.readLine();
	}
	
	
	/* (non-Javadoc)
	 * 
	 * Interne (private) Methode zur Verarbeitung von Eingaben
	 * und Ausgabe von Ergebnissen.
	 */
	private void processInput(String line) throws IOException {
		
		// Eingabe bearbeiten:
		if (line.equals("e")) { 
			
			/* lese die notwendigen Parameter, einzeln pro Zeile, 
			 * fuer Artikelnummer und Artikeltitel
			*/
			System.out.print("Artikelnummmer > ");
			String number = readInput();
			int aNr = Integer.parseInt(number);
			
			System.out.print("Artikeltitel  > ");
			String title = readInput();
			boolean isok = shop.insertArtikel(aNr, title);

			if (isok)
				System.out.println("Einfügen ok");
			else
				System.out.println("Fehler beim Einfügen");
		}
		// Die Artikel Liste wird ueber die EShopV ausgegeben
		else if (line.equals("a")) {
			List<Artikel> list = shop.getAllArtikel();
			giveOutArtikellist(list);
		}
		/* Es wird ueber eine Eingabe nach einem Titel oder Nummer
		 * in der Artikel Liste gesucht 
		*/
		else if (line.equals("f")) {
			System.out.print("Artikeltitel oder Nummer  > ");
			String input  = readInput();
			List<Artikel> list = shop.findArtikelByString(input);
			giveOutArtikellist(list);
		}
		// Ein neuer Artikel wird dem Shop hinzugefuegt
		else if (line.equals("s")) {
	//		shop.writeArtikel();
		}
	}
	
	/* (non-Javadoc)
	 * 
	 * Interne (private) Methode zum Ausgeben von Bücherlisten.
	 *
	 */
	private void giveOutArtikellist(List<Artikel> artikel) {
		if (artikel.isEmpty()) {
			System.out.println("Es sind keine Artikel vorhanden, die Liste ist leer.");
		} else {
			Iterator<Artikel> it = artikel.iterator();
			while (it.hasNext()) {
				Artikel article = (Artikel) it.next();
				System.out.println(article.getTitle());
			}
		}
	}
	

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
			outputMenu();
			try {
				input = in.readLine();
				processInput(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (!input.equals("q"));
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		EShopClientCUI cui;
		try {
			cui = new EShopClientCUI("SHOP");
			cui.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
