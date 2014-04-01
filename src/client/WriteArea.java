package client;

import javax.swing.JTextPane;


@SuppressWarnings("serial")
public class WriteArea extends JTextPane{

	public WriteArea(ClientModel clientModel) {
		ActionButton ab = new ActionButton(this, clientModel);
		
	}

	
}
