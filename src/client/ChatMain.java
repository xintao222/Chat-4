package client;

import GUI.ChatClientGui;

public class ChatMain {
	
	public static void main(String[] args){
		
		ChatClientGui gui = new ChatClientGui();

		ClientModel clientModel = gui.init();
		new Thread(clientModel).run();;
	}
}
