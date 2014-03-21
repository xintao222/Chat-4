package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;

import commons.*;

public class ClientModel extends Observable implements Runnable {
	private String messageHistory;
	private String loginName;
	private String serverIp;
	private String serverPort;
	private Socket clientSocket;
	private InputStream inStream;
	private ObjectInputStream ObjInputStream;
	private OutputStream outStream;
	private ObjectOutputStream objectOutStream;
	private boolean running = false;
	private ArrayList<SharedClient> connectedClients;

	public ClientModel() {
		running = true;
		connectedClients = new ArrayList<SharedClient>();
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Override
	public void run() {
		while (running) {
			Object input = null;
			try {
				input = (Object) ObjInputStream.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			if (input != null && input instanceof ClientListFromServer) {
				ClientListFromServer clientListFromServer = (ClientListFromServer) input;
				connectedClients.addAll(clientListFromServer.getClients());
				setChanged();
				notifyObservers();
			} else if (input != null && input instanceof Message) {
				Message mess = (Message) input;
				updateHistory(mess.getFrom(), mess.getMessage());
			}

		}

	}

	public void setIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public void setPort(String serverPort) {
		this.serverPort = serverPort;
		try {
			clientSocket = new Socket(serverIp, Integer.parseInt(serverPort));
			inStream = clientSocket.getInputStream();
			ObjInputStream = new ObjectInputStream(inStream);
			outStream = clientSocket.getOutputStream();
			objectOutStream = new ObjectOutputStream(outStream);
			connectToServer();

		} catch (Exception e) {
			e.printStackTrace();
		}
		run();
	}

	/*
	 * Request connect to server by sending /C/
	 */
	private void connectToServer() {
		try {
			objectOutStream.writeObject(new String("/C/"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void sendMessage(String message) {

		try {
			objectOutStream.writeObject(new String(message));
		} catch (IOException e) {
			e.printStackTrace();
		}

		updateHistory(loginName, message);
		setChanged();
		notifyObservers();

	}

	public String getChatHistory() {
		return messageHistory;
	}

	public void exterminate() throws IOException {
		objectOutStream.close();
		outStream.close();
		clientSocket.close();
		ObjInputStream.close();
		running = false;
	}

	private void updateHistory(String from, String mess) {
		String newLine = "\n" + from + ": \n" + mess;
		messageHistory += newLine;

	}

}
