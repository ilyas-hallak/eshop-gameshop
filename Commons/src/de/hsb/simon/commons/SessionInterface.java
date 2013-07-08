package de.hsb.simon.commons;

import java.io.Serializable;

import de.root1.simon.SimonUnreferenced;

/**
 * @description Sitzungsinterface, welches Cient und dessen Nachrichten enthaelt und diese weiter leitet 
 */
public interface SessionInterface extends Serializable, SimonUnreferenced {
	public ClientInterface getClient();
	public void sendMessage(String msg);
}
