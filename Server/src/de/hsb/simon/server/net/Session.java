package de.hsb.simon.server.net;

import de.hsb.simon.commons.ClientInterface;
import de.hsb.simon.commons.SessionInterface;
import de.root1.simon.annotation.SimonRemote;

/**
 * @description Klasse fuer die Sitzung mit dem Cient 
 * verweist auf das SessionInterface
 */
@SimonRemote(value={SessionInterface.class})
public class Session implements SessionInterface {

	private static final long serialVersionUID = -8445326392443617259L;
	private ClientInterface client;
	private ServerInterfaceImpl server;
	
	/**
	 * @description Konstruktor der Klasse Session
	 * @param client - Variable fuer den Client aus dem ClientInterface
	 * @param server - variable fuer den Server aus dem ServerInterfaceImpl
	 */
	public Session(ClientInterface client, ServerInterfaceImpl server) {
		this.client = client;
		this.server = server;
	}
	
	/**
	 * @description Bei Verbindungsverlust wird die Session geloescht
	 */
	@Override
	public void unreferenced() {
		server.removeSession(this);
	}

	@Override
	public ClientInterface getClient() {
		return this.client;
	}

}
