package com.shop.persistence;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @description Persistens Manager welche mithilfe von XML Daten ausliest und speichert 
 */
public class FilePersistenceManager {
	private XMLEncoder in = null;
	private XMLDecoder out = null;

	private String file;
	
	/**
	 * @description XML Datei schliessen
	 * @return Bei erfolgreich geschlossen true, ansonsten false
	 */
	public boolean close() {
		if(in != null) {
			this.in.flush();
			this.in.close();
			
			return true;
		} else if(out != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param file XML Dateiname
	 */
	public FilePersistenceManager(String file) {
		this.file = file;
	}
	
	/**
	 * @description Öffnet die XML zum lesen
	 * @throws FileNotFoundException
	 */
	public void openForReading() throws FileNotFoundException {
		this.out = new XMLDecoder(new BufferedInputStream(new FileInputStream(this.file)));
	}
	
	/**
	 * @description XML Datei zum schreiben oeffnen
	 * @throws FileNotFoundException
	 */
	public void openForWriting() throws FileNotFoundException   {
		// writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		this.in = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(this.file)));
	}
	
	/**
	 * Speichern von Objekten in XML Datei
	 * @param o
	 */
	public void save(Object o) {
		this.in.writeObject(o);
	}
	
	/**
	 * Lesen von Objekten aus XML Datei (muessen spaeter geparst sein)
	 * @return
	 */
	public Object read() {
		return this.out.readObject();
	}
	
}
