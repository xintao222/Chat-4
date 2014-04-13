package GUI;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;

import client.ClientModel;

public class BetterListModel extends DefaultListModel implements Observer {

	private static final long serialVersionUID = 1L;
	private JList list;
	private ClientModel clientModel;

	public BetterListModel(ClientModel clientModel, JList list) {
		this.clientModel = clientModel;
		this.list = list;
		clientModel.addObserver(this);
		list.setModel(this);
		ArrayList<String> clients = clientModel.getConnectedClients();
		for (String s : clients) {
			if (!contains(s)) {
				add(size(), s);
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
//		String with = clientModel.getChatWith();//Chat with bytes
//        list.setSelectedValue(with, true);
		int i = list.getSelectedIndex();
		ListModel m = list.getModel();
		String s = null;
		if(i != -1){
		s = (String) m.getElementAt(i);
		}
		removeAllElements();

		ArrayList<String> clients = clientModel.getConnectedClients();
		for (String s1 : clients) {
			if (!contains(s1)) {
				add(size(), s1);
			}
		}
		list.setSelectedValue(s, true);
	}
}
