package com.shop.persistence;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.shop.logic.ArtikelV;
import com.shop.logic.PersonV;
import com.shop.valueobjects.Artikel;


public class FilePersistenceManager {
	private XMLEncoder in = null;
	private XMLDecoder out = null;

	private String file;
	
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
	
	public FilePersistenceManager(String file) {
		this.file = file;
	}
	
	public ArtikelV loadArtikel() {
		try {
			ArtikelV Av = (ArtikelV)this.out.readObject();
			return Av;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	} 
	public void openForReading() throws FileNotFoundException {
		this.out = new XMLDecoder(new FileInputStream(this.file));
	}
	
	public void openForWriting() throws IOException {
		// writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		this.in = new XMLEncoder(new FileOutputStream(this.file));
	}
	
	public boolean saveArtikel(ArtikelV b) {
		try {
			in.writeObject(b);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public void save(Object o) {
		try {
			in.writeObject(o);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Object read() {
		return this.out.readObject();
	}
	
}
