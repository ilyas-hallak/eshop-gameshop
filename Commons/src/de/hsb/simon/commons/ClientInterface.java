package de.hsb.simon.commons;

import java.io.Serializable;

import de.root1.simon.SimonUnreferenced;

public interface ClientInterface extends Serializable, SimonUnreferenced {

	public void receiveMessage(String message);
	
}
