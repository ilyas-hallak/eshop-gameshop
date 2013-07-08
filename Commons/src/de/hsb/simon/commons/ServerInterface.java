package de.hsb.simon.commons;

/**
 * @description Interface fuer die Verbindung von Client und Server Seite ueber das SessionInterface
 */
public interface ServerInterface extends ServiceVInterface {
	public SessionInterface login(ClientInterface client);
}
