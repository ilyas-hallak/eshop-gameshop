package com.shop.logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.shop.persistence.FilePersistenceManager;
import com.shop.valueobjects.Artikel;
import com.shop.valueobjects.Ereignis;
import com.shop.valueobjects.Person;

public class EreignisV implements Serializable {

	private static final long serialVersionUID = 6451799215728455338L;
	private FilePersistenceManager pm;
	private ArrayList<Ereignis> actionList;
	
	public EreignisV(String file) {
		this.pm = new FilePersistenceManager(file);
		this.actionList = new ArrayList<Ereignis>();
		this.loadEreignisse();
	}
	
	public void create(int count, Person p, Artikel a, String msg) {
		try {
			Ereignis e = new Ereignis(count, p, a, msg);
			this.actionList.add(e);
			
			pm.openForWriting();
			pm.save(this.actionList);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}
	}
	
	public void loadEreignisse() {
		try {
			this.pm.openForReading();
		} catch (FileNotFoundException e) {
		}
		this.actionList = (ArrayList<Ereignis>) this.pm.read();
		this.pm.close();
	}

	public void create(Person p, Map<Artikel, Number> aMap, String msg) {
		try {
			Iterator iterator = aMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<Artikel, Number> pair = (Map.Entry<Artikel, Number>) iterator.next();
				Ereignis e = new Ereignis(pair.getValue().intValue(), p, pair.getKey(), msg);
				this.actionList.add(e);
			}
			pm.openForWriting();
			pm.save(this.actionList);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}
	}
	
	public ArrayList<Ereignis> getAllEreignisse() {
		return this.actionList;
	}
	
}
