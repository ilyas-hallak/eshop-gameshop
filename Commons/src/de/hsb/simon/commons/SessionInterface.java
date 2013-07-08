package de.hsb.simon.commons;

import java.io.Serializable;

import de.root1.simon.SimonUnreferenced;

/**
 * @description Sitzungsinterface, welches Cient enthaelt und diese weiter leitet 
 */
public interface SessionInterface extends Serializable, SimonUnreferenced {
	public ClientInterface getClient();
}
