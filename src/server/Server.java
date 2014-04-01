package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import commons.Message;

public class Server implements Runnable {

	private ArrayList<ServerClient> clients;
	private ServerSocket serverSocket;

	public Server() {
		clients = new ArrayList<ServerClient>();

		int port = 4446;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		while (true) {
			try {
				Socket socketCon = serverSocket.accept();
				System.out.println("Connected clients: " + clients);
				ServerClient cl = new ServerClient(this, socketCon);
				clients.add(cl);
				new Thread(cl).start();
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}

		}

	}

	public ArrayList<ServerClient> getServerClients() {
		return clients;
	}

	public static void main(String[] args) {
		Server server = new Server();
		new Thread(server).start();
	}

	public void passOnMessage(Message mess) {
		String to = mess.getTo();
		String from = mess.getFrom();

		for (ServerClient sc : clients) {
			if (sc.getName().equals(to)) {
				sc.sendMessage(mess);
			}
		}

	}
}
