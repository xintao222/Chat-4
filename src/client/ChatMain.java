package client;

import GUI.ChatClientGui;

public class ChatMain {
	
	public static void main(String[] args){

		ClientModel clientModel = new ClientModel();
		
		new Thread(clientModel).start();
		new ChatClientGui(clientModel);
	}
}
