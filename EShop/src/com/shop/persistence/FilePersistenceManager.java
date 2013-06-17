package com.shop.persistence;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


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
	
	public void openForReading() throws FileNotFoundException {
	//	this.out = new XMLDecoder(new FileInputStream(this.file));
		this.out = new XMLDecoder(new BufferedInputStream(new FileInputStream(this.file)));
	}
	
	public void openForWriting() throws FileNotFoundException   {
		// writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		this.in = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(this.file)));
	}
	
	public void save(Object o) {
		this.in.writeObject(o);
	}
	
	public Object read() {
		return this.out.readObject();
	}
	
}
