package de.hsb.simon.commons;

import java.io.Serializable;

import de.root1.simon.SimonUnreferenced;

/**
 * Interface welches die Nachrichten auf ClientSeite beinhaltet
 *
 */
public interface ClientInterface extends Serializable, SimonUnreferenced {

	public void receiveMessage(String message);
	
}
