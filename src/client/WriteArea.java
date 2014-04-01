package client;

import javax.swing.JTextPane;


@SuppressWarnings("serial")
public class WriteArea extends JTextPane{
	private ClientModel clientModel;

	public WriteArea(ClientModel clientModel) {
		this.clientModel = clientModel;
		ActionButton ab = new ActionButton(this, clientModel);
		
	}

	
}
