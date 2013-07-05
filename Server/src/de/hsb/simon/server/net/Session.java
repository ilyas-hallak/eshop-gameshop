package de.hsb.simon.server.net;

import de.hsb.simon.commons.ClientInterface;
import de.hsb.simon.commons.SessionInterface;
import de.root1.simon.annotation.SimonRemote;

@SimonRemote(value={SessionInterface.class})
public class Session implements SessionInterface {

	private static final long serialVersionUID = -8445326392443617259L;
	private ClientInterface client;
	private ServerInterfaceImpl server;
	
	public Session(ClientInterface client, ServerInterfaceImpl server) {
		this.client = client;
		this.server = server;
	}

	@Override
	public void unreferenced() {
		server.removeSession(this);
	}

	@Override
	public ClientInterface getClient() {
		return this.client;
	}

	@Override
	public void sendMessage(String message) {
		server.broadcastMessage(message);
	}
	
	
}
