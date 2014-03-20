package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JEditorPane;

public class WriteArea extends JEditorPane implements ActionListener{
	private ClientModel clientModel;
	
	
	public WriteArea(ClientModel clientModel){
		this.clientModel = clientModel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		clientModel.sendMessage(getText());
		setText("");
		
	}
	

}
