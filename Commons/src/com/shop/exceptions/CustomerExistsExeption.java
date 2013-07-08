package com.shop.exceptions;

import com.shop.valueobjects.Kunde;

public class CustomerExistsExeption extends Exception {
	
	public CustomerExistsExeption(Kunde k) {
		super("Der Kunde mit dem Namen " + k.getName() + " und der Mailadresse " +  k.geteMail()+ " ist schon vorhanden!");
	}

}
