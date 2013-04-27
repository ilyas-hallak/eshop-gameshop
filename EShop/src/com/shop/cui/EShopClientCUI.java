package com.shop.cui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.shop.exceptions.CustomerExistsExeption;
import com.shop.logic.ServiceV;
import com.shop.valueobjects.Artikel;
import com.shop.valueobjects.Kunde;
import com.shop.valueobjects.Person;

public class EShopClientCUI {
	
	private ServiceV shop;
	private BufferedReader in;
	
	
	public EShopClientCUI(String file) throws IOException {
		// die EShop-Verwaltung nimmt die ersten Aufgaben entgegen 
		// und leitet diese weiter an den Service
		shop = new ServiceV(file);

		// Stream-Objekt fuer Texteingabe ueber Konsolenfenster erzeugen
		in = new BufferedReader(new InputStreamReader(System.in));
	}
	
	// Der Benutzer kann wählen ob er Kunde oder Mitarbeiter ist
	private void selection() {
		System.out.print(" Einloggen > 'l'");
		System.out.print(" \n registrieren > 'r'");
	} 
	
	// Der Kunde oder Mitarbeiter kann sich einloggen
	private void login(String enter) throws IOException {
		System.out.print(" \n Bitte geben Sie Ihre E-Mail ein:");
		String eMail = readInput();
		System.out.print(" \n Bitte geben Sie Ihr Passwort ein:");
		String pw = readInput();
		Person p = new Person(eMail, pw);
	}
	
	// Hier kann der Kunde sich im E-Shop registrieren
	private void registerCustomer(String enter) throws IOException {
		System.out.print(" \n Bitte geben Sie Ihren Namen ein:");
		String name = readInput();
		System.out.print(" \n Bitte geben Sie Ihre E-Mail Adresse ein:");
		String eMail = readInput();
		System.out.print(" \n Bitte geben Sie Ihre komplette Adresse ein(Straße,Hausnr.,Stadt):");
		String address = readInput();
		System.out.print(" \n Bitte geben Sie Ihr persönliches Passwort ein:");
		String pw = readInput();
		Kunde k = new Kunde(name, eMail, address, pw);
		try {
			shop.insertCustomer(k);
		} catch(CustomerExistsExeption e) {
			System.out.println("Die n sie Ihre Eingabe");
		}
		
	}
	
	/* (non-Javadoc)
	 * 
	 * Interne (private) Methode zur Ausgabe des Menüs.
	 */
	private void outputMenu() {
		System.out.print("Befehle: \n  Artikel einfuegen: 'e'");
		System.out.print("         \n  Artikel ausgeben:  'a' nach Titel oder 'aa' nach Nummer");
		System.out.print("         \n  Artikel suchen:    'f'");
		System.out.print("         \n  Daten sichern:     's'");
		System.out.print("         \n  Bestand erhöhen:   'b'");
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
			
			System.out.println("Bestand  > ");
			String bestand = readInput();
			int stock = Integer.parseInt(bestand);
			// 
			boolean isok = shop.insertArtikel(aNr, title, stock);
			
			if (isok)
				System.out.println("Der Artikel wurde hinzugefügt");
			else 
				System.out.println("Die Eingabe ist fehlgeschlagen, bitte überprüfen sie Ihre Eingabe");
		}
		// Der Bestand kann erhöht oder veringert werden
		else if (line.equals("b")) {
			System.out.print("Bei welchem Artikel möchten Sie den Bestand erhöhen?");
			List<Artikel> list = shop.getAllArtikel();
			giveOutArtikellist(list);
			System.out.print("Geben Sie die gewünschte Artikelnummmer an > ");
			String number = readInput();
			int aNr = Integer.parseInt(number);
			System.out.println("Um wie viel möchten Sie den Bestand erhöhen?  > ");
			String bestand = readInput();
			int stock = Integer.parseInt(bestand);
			shop.raiseStock(aNr, stock);
		}
		// Die Artikel Liste wird ueber die EShopV, nach Titel sortiert, ausgegeben
		else if (line.equals("a")) {
			List<Artikel> list = shop.getAllArtikel();
			giveOutArtikellist(list);
		}
		// Artikel sortiert nach Nummer ausgeben
//		else if (line.equals("aa")) {
//			List<Artikel> list = shop.getAllArtikel("nr");
//			giveOutArtikellist(list);
//		}
		/* Es wird ueber eine Eingabe nach einem Titel oder Nummer
		 * in der Artikel Liste gesucht 
		*/
		else if (line.equals("f")) {
			System.out.print("Artikeltitel oder Nummer  > ");
			String input  = readInput();
			List<Artikel> list = shop.findArtikelByString(input);
			if (list == null){
				System.out.print("Es wurde kein passender Artikel gefunden");
			} 	else {
				System.out.print("Es wurden folgende Artikel gefunden");	
				giveOutArtikellist(list);
			}
		}
		// Ein neuer Artikel wird dem Shop hinzugefuegt
		else if (line.equals("s")) {
			shop.saveArtikel();
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
		String enter = "";
	
		// Hauptschleife der Benutzungsschnittstelle für den Mitabreiter
		do {
			try {
			selection();
			enter = in.readLine();
				
			if (enter.equals("l")) {
				login(enter);
				// Kunden oder Mitarbeiter Menü
				outputMenu();
				input = in.readLine();
				processInput(input);
			} 	else { 
					registerCustomer(enter);
				}
			} 	catch (IOException e) {
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
			cui = new EShopClientCUI("SHOP.xml");
			cui.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
