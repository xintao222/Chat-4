package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import commons.ClientListFromServer;
import commons.Message;
import commons.SharedClient;

public class ServerClient implements Runnable, Comparable<ServerClient> {
	private Socket clientSocket;
	private ServerSocket ss;
	private InputStream is;
	private ObjectInputStream ois;
	private OutputStream outStream;
	public ObjectOutputStream objectOutStream;
	boolean running = false;
	private Server server;
	private int id;
	private String name;

	public ServerClient(Server server, Socket clientSocket) throws IOException {
		this.clientSocket = clientSocket;
		this.server = server;
		is = clientSocket.getInputStream();
		ois = new ObjectInputStream(is);
		outStream = clientSocket.getOutputStream();
		objectOutStream = new ObjectOutputStream(outStream);
		running = true;

	}

	@Override
	public void run() {
		while (running) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				while (is.available() > 0) {
					Object input = null;
					try {
						input = (Object) ois.readObject();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						System.out.println("unknonwn class");
						e.printStackTrace();
					}

					if (input != null && input instanceof String) {

						String message = (String) input;
						if (message.contains("/C/")) {
							System.out.println(name + " receivedString: " + message);
							String[] split = message.split("/");
							name = split[0];
							updateOnlineClients();
							objectOutStream.writeObject(new Message("Server", name, "You are now connected to the server."));
						}
					} else if (input != null && input instanceof Message) {
						Message mess = (Message) input;
						System.out.println(name + "received (Message) :" + mess.getMessage());
						server.passOnMessage(mess);
					}

				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public void updateOnlineClients() {
		ArrayList<ServerClient> connectedClients = server.getServerClients();
		ArrayList<SharedClient> shareClientList = new ArrayList<SharedClient>();

		for (ServerClient s : connectedClients) {
			SharedClient share = new SharedClient(s.getID(), s.getName());
			shareClientList.add(share);
		}
		for (ServerClient s : connectedClients) {
			try {
				s.objectOutStream.writeObject(new ClientListFromServer(shareClientList));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getName() {
		return name;
	}

	public void Exterminate() {
		running = false;
		try {
			is.close();
			clientSocket.close();
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getID() {
		return id;
	}

	@Override
	public int compareTo(ServerClient o) {
		return name.compareTo(o.getName());
	}

	public void sendMessage(Message mess) {
		try {
			objectOutStream.writeObject(mess);
		} catch (IOException e) {
			System.out.println("Couldn't sendMessage()");
			e.printStackTrace();
		}

	}

	public String toString() {
		return name;
	}
}