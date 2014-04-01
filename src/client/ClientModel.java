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
	private int serverPort;
	private Socket clientSocket;
	private InputStream inStream;
	private ObjectInputStream ObjInputStream;
	private OutputStream outStream;
	private ObjectOutputStream objectOutStream;
	private boolean running = false;
	private ArrayList<SharedClient> connectedClients;
	private ArrayList<String> allConnectedNames;
	private String chatWith = "troll";

	public ClientModel(String loginName, String ip, int port) {
		this.loginName = loginName;
		this.serverIp = ip;
		this.serverPort = port;
		running = true;
		connectedClients = new ArrayList<SharedClient>();
		allConnectedNames = new ArrayList<String>();

		try {

			clientSocket = new Socket(serverIp, serverPort);

			inStream = clientSocket.getInputStream();

			outStream = clientSocket.getOutputStream();

			objectOutStream = new ObjectOutputStream(outStream);
			connectToServer();

			ObjInputStream = new ObjectInputStream(inStream);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Override
	public void run() {
		while (running) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				if (inStream.available() > 0) {
					Object input = null;
					try {
						input = (Object) ObjInputStream.readObject();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					if (input != null && input instanceof Message) {
						Message mess = (Message) input;
						System.out.println("ClientModel received: " + mess.getMessage());
						updateHistory(mess.getFrom(), mess.getMessage());
					} else if (input != null && input instanceof ClientListFromServer) {
						ClientListFromServer clientListFromServer = (ClientListFromServer) input;
						System.out.println("Received clientList from the server");
						ArrayList<SharedClient> tempList = clientListFromServer.getClients();
						System.out.println("Before: " + allConnectedNames);
					
						connectedClients.clear();
						connectedClients.addAll(tempList);
						allConnectedNames.clear();
						for (SharedClient sh : tempList) {
							allConnectedNames.add(sh.getName());
						}
						System.out.println("After: " + allConnectedNames);
						updateHistory("Server", "You are now connected to the server.");
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ArrayList<String> getConnectedClients() {
		return allConnectedNames;
	}

	/*
	 * Request connection to server by sending /C/
	 */
	private void connectToServer() {
		try {
			objectOutStream.writeObject(new String(loginName + "/C/"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void sendMessage(String message) {

		try {
			objectOutStream.writeObject(new Message(loginName, chatWith, message));
		} catch (IOException e) {
			e.printStackTrace();
		}

		updateHistory(loginName, message);

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
		setChanged();
		notifyObservers();
	}

	public void setChatWith(String string) {
		chatWith = string;

	}

}
