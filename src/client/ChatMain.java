package client;

public class ChatMain {
	
	public static void main(String[] args) {

		ClientModel clientModel = new ClientModel();
		new ChatClientGui(clientModel);
	}
}
