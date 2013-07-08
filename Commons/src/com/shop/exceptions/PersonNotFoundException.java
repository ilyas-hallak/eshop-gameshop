package com.shop.exceptions;

import com.shop.valueobjects.Person;

public class PersonNotFoundException extends Exception {
	public PersonNotFoundException(Person p) {
		super("Die Person " + p.getName() + " wurde nicht gefunden!");
	}
}
