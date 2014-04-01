package GUI;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import client.ClientModel;


public class BetterListModel extends DefaultListModel implements Observer{

	private static final long serialVersionUID = 1L;
	private JList list;
	private ClientModel clientModel;

	public BetterListModel(ClientModel clientModel, JList list){
		this.clientModel = clientModel;
		this.list = list;
		clientModel.addObserver(this);
		list.setModel(this);
		ArrayList<String> clients = clientModel.getConnectedClients();

		for(String s : clients){
			if(!contains(s)){
				add(size(), s);
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		removeAllElements();
		ArrayList<String> clients = clientModel.getConnectedClients();
//		System.out.println("update BetterListModel contains: ");
		for(String s : clients){
//			System.out.println(s);
			if(!contains(s)){
				add(size(), s);
			}
		}
	}
}
