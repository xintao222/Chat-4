package client;

import java.util.Observable;

public class ClientModel extends Observable{

	public void setLoginName(String text) {
		// TODO Auto-generated method stub
		
	}

	public void setIp(String text) {
		// TODO Auto-generated method stub
		
	}

	public void setPort(String text) {
		// TODO Auto-generated method stub
		
	}

	public void sendMessage(String text) {
		setChanged();
		notifyObservers();
		
	}

	public String getChatHistory() {
		// TODO Auto-generated method stub
		return null;
	}

}
