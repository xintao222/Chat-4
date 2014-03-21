package commons;

import java.io.Serializable;
import java.util.ArrayList;

public class ClientListFromServer implements Serializable {
	private int value;
	public String id;
	private ArrayList<SharedClient> clients;

	public ClientListFromServer(ArrayList<SharedClient> clients) {
		this.clients = clients;
	}

	public ArrayList<SharedClient> getClients() {
		return clients;
	}
}