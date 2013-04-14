package com.shop.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.shop.valueobjects.Artikel;


public class FilePersistenceManager implements PersistenceManager {
	private BufferedReader reader = null;
	private PrintWriter writer = null;
	
	@Override
	public boolean close() {
		if (writer != null)
			writer.close();
		
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				
				return false;
			}
		}
		return true;
	}
	@Override
	public Artikel loadArtikel() throws IOException {
		int nr = -1;
		try {
			nr = Integer.parseInt(this.readLine());
			
			String title = this.readLine();
			if(title == null) {
				return null;
			}
			int stock = Integer.parseInt(this.readLine());
			return new Artikel(nr, title, stock);
		} catch(Exception e) {
			return null;
		}
	} 
	@Override
	public void openForReading(String file) throws IOException {
		reader = new BufferedReader(new FileReader(file));
	}
	@Override
	public void openForWriting(String file) throws IOException {
		writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
	}
	@Override
	public boolean saveArtikel(Artikel b) throws IOException {
		this.writeLine(Integer.valueOf(b.getNr()).toString());
		this.writeLine(b.getTitle());
		this.writeLine(Integer.valueOf(b.getStock()).toString());
		return true;
	}
	
	private String readLine() throws IOException {
		if (reader != null)
			return reader.readLine();
		else
			return "";
	}

	private void writeLine(String data) {
		if (writer != null)
			writer.println(data);
	}
}
