package client;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextArea;

public class HistoryArea extends JTextArea implements Observer{
	private ClientModel clientModel;

	public HistoryArea(ClientModel clientModel) {
		this.clientModel = clientModel;
		clientModel.addObserver(this);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		setText(clientModel.getChatHistory());
		
	}
}
