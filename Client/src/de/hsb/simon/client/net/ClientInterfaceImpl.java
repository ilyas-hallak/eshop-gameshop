package de.hsb.simon.client.net;

import java.net.UnknownHostException;

import javax.swing.JTextArea;

import de.hsb.simon.commons.ClientInterface;
import de.hsb.simon.commons.ServerInterface;
import de.hsb.simon.commons.SessionInterface;
import de.root1.simon.Lookup;
import de.root1.simon.Simon;
import de.root1.simon.annotation.SimonRemote;
import de.root1.simon.exceptions.EstablishConnectionFailed;
import de.root1.simon.exceptions.LookupFailedException;

@SimonRemote(value={ClientInterface.class})
public class ClientInterfaceImpl implements  ClientInterface {

	private static final long serialVersionUID = -7272521996636160840L;
	
	private Lookup lookup;
	private SessionInterface session;
	private JTextArea area;
	private boolean safeLogout = false;
	private ServerInterface server;
	private ServiceVInterfaceImpl shop;
	
	public ClientInterfaceImpl(JTextArea area) {
		this.area = area;
	}

	@Override
	public void unreferenced() {
		if(!safeLogout) {
			this.area.append("Die Verbindung wurde unerwartet geschlossen");
		}
	}
	
	/**
	 * 
	 * @throws UnknownHostException
	 * @throws LookupFailedException
	 * @throws EstablishConnectionFailed
	 */
	public void connectToServer() throws UnknownHostException, LookupFailedException, EstablishConnectionFailed {
		// init lookup fuer server
		lookup = Simon.createNameLookup("127.0.0.1", 4753);
	
		// server-object aufsuchen
		server = (ServerInterface) lookup.lookup("server");

		System.out.println("Hallo " + server);
		
		// client au dem Server anmelden und session empfangen
		session = server.login(this);
		
		shop = new ServiceVInterfaceImpl(this.getServer());
	}
	
	public void sendMessage() {
		session.sendMessage("hallo");
	}

	@Override
	public void receiveMessage(String message) {
		area.append(message + "\n");
	}
	
	public void logout() {
		this.lookup.release(server);
		this.safeLogout = true;
	}
	
	public ServiceVInterfaceImpl getShop() {
		return this.shop;
	}
	
	/**
	 * @return the server
	 */
	public ServerInterface getServer() {
		return server;
	}

	/**
	 * @param server the server to set
	 */
	public void setServer(ServerInterface server) {
		this.server = server;
	}


}
