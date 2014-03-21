package GUI;

import java.util.Observable;
import java.util.Observer;

import sun.awt.geom.AreaOp.AddOp;
import client.ClientModel;

public class ConnectedClientPane implements Observer {
	private ClientModel clientModel;

	public ConnectedClientPane(ClientModel clientModel) {
		this.clientModel = clientModel;
		clientModel.addObserver(this);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		clientModel.getConnectedClients();

	}

}
