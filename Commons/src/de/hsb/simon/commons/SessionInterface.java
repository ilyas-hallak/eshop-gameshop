package de.hsb.simon.commons;

import java.io.Serializable;

import de.root1.simon.SimonUnreferenced;

public interface SessionInterface extends Serializable, SimonUnreferenced {
	public ClientInterface getClient();
	public void sendMessage(String msg);
}
