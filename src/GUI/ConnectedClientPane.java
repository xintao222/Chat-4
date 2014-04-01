//package GUI;
//
//import java.util.Observable;
//import java.util.Observer;
//
//import javax.swing.JList;
//
//import client.ClientModel;
//
//@SuppressWarnings("serial")
//public class ConnectedClientPane extends JList implements Observer {
//	
//	private ClientModel clientModel;
//
//	public ConnectedClientPane(ClientModel clientModel) {
//		this.clientModel = clientModel;
//		clientModel.addObserver(this);
//	}
//
//	@Override
//	public void update(Observable arg0, Object arg1) {
//		clientModel.getConnectedClients();
//	}
//}
