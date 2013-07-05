package com.shop.exceptions;

public class ArtikelexistsException extends Exception {
	public ArtikelexistsException(String msg) {
		super(msg + " ist bereits vorhanden!");
	}
}
