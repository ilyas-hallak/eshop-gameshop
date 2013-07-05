package de.hsb.simon.server.net;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.shop.exceptions.ArtikelNotFoundException;
import com.shop.exceptions.ArtikelexistsException;
import com.shop.exceptions.BestandZuKleinException;
import com.shop.exceptions.CustomerExistsExeption;
import com.shop.exceptions.PersonNotFoundException;
import com.shop.logic.ServiceV;
import com.shop.valueobjects.Artikel;
import com.shop.valueobjects.Ereignis;
import com.shop.valueobjects.Kunde;
import com.shop.valueobjects.Person;
import com.shop.valueobjects.Rechnung;

import de.hsb.simon.commons.ClientInterface;
import de.hsb.simon.commons.ServerInterface;
import de.hsb.simon.commons.SessionInterface;
import de.root1.simon.Registry;
import de.root1.simon.Simon;
import de.root1.simon.annotation.SimonRemote;
import de.root1.simon.exceptions.NameBindingException;

@SimonRemote(value={ServerInterface.class})
public class ServerInterfaceImpl implements ServerInterface {

	private Registry registry;
	private List<SessionInterface> sessions;
	private ServiceV shop;
	
	public ServerInterfaceImpl() throws IOException {
		sessions = new ArrayList<SessionInterface>();
		this.shop = new ServiceV("SHOP");
	}
	
	public void startServer() throws UnknownHostException, IOException, NameBindingException {
		// registry erstellen
		registry = Simon.createRegistry(4753);
		
		// registry an das Serverinterface-Objekt binden und Namen vergeben
		registry.bind("server", this);
		
	}
	
	public void stopServer() {
		if(registry != null) {
			// bindung von der registry lösen
			registry.unbind("server");
			
			// registry stoppen
			registry.stop();
		}
		
	}

	@Override
	public SessionInterface login(ClientInterface client) {
		System.out.println("hallo client: " + sessions.size());
		
		// authentifizieren
		// ...
		// session für diesen client erstellen
		Session session = new Session(client, this);
		
		// session eintragen
		sessions.add(session);
		
		this.broadcastMessage("Benutzer: " + session.getClient().toString() + " hat sich angemeldet");

		
		// session zurückerstellen
		
		return session;
	}

	public void broadcastMessage(String message) {
		for(SessionInterface session : this.sessions) {
			ClientInterface client = session.getClient();
			client.receiveMessage(message);
		}
	}

	public void removeSession(Session session) {
		sessions.remove(session);	
		this.broadcastMessage("Benutzer: " + session.getClient().toString() + " hat sich abgemeldet");
	}

	@Override
	public void insertArtikel(String title, int bestand, double price)
			throws ArtikelexistsException {
		this.shop.insertArtikel(title, bestand, price);
	}

	@Override
	public void insertArtikel(int nr, String title, int bestand, double price,
			int mengeneinheit) throws ArtikelexistsException {
		this.shop.insertArtikel(title, bestand, price);
	}

	@Override
	public List<Artikel> getAllArtikel() {
		return this.shop.getAllArtikel();
	}

	@Override
	public List<Artikel> sucheNachTitel(String titel) {
		return this.shop.sucheNachTitel(titel);
	}

	@Override
	public void savedata() {
		this.shop.savedata();
	}

	@Override
	public void saveArtikel() {
		this.shop.saveArtikel();
	}

	@Override
	public List<Artikel> findArtikelByString(String s) {
		return this.shop.findArtikelByString(s);
	}

	@Override
	public boolean raiseStock(int nr, int stock) {
		return this.shop.raiseStock(nr, stock);
	}

	@Override
	public void insertCustomer(Kunde k) throws CustomerExistsExeption {
		this.shop.insertCustomer(k);
	}

	@Override
	public Person login(Person p) throws PersonNotFoundException {
		return shop.login(p);
	}

	@Override
	public void addArtikel(Artikel a, int count) throws BestandZuKleinException {
		this.shop.addArtikel(a, count);
	}

	@Override
	public void removeArtikelFromCart(Artikel a) {
		this.shop.removeArtikelFromCart(a);
	}

	@Override
	public Map<Artikel, Number> getAllArtikelFromCart() {
		return this.shop.getAllArtikelFromCart();
	}

	@Override
	public Rechnung buy(Kunde k) {
		return this.shop.buy(k);
	}

	@Override
	public void complete() throws ArtikelNotFoundException {
		this.shop.complete();
	}

	@Override
	public void setPerson(Person person) {
		// TODO person array
		this.shop.setPerson(person);
	}

	@Override
	public ArrayList<Person> getAllMitarbeiter() {
		return this.shop.getAllMitarbeiter();
	}

	@Override
	public void insertMitarbeiter(String name, String mail, String password) {
		this.shop.insertMitarbeiter(name, mail, password);
	}

	@Override
	public ArrayList<Ereignis> getAllEreignisse() {
		return this.shop.getAllEreignisse();
	}

	@Override
	public Person getPerson() {
		// TODO get person from array
		return this.shop.getPerson();
	}

	@Override
	public void updateArtikel(int nr, String title, int bestand, double price,
			int menegeneinheit) throws ArtikelNotFoundException {
		this.shop.updateArtikel(nr, title, bestand, price, menegeneinheit);
	}
}
