package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import commons.Message;
import commons.RequestMessage;

public class Server implements Runnable {

	private ArrayList<ServerClient> clients;
	private ServerSocket serverSocket;

	public Server() {
		clients = new ArrayList<ServerClient>();

		int port = 4446;
		try {
			serverSocket = new ServerSocket(port);
			// serverSocket.bind(new InetSocketAddress("0.0.0.0", port));
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
		
		for (ServerClient sc : clients) {
			if (sc.getName().equals(to)) {
				sc.sendObject(mess);
			}
		}

	}

	public void exterminateMe(ServerClient serverClient) {
		System.out.println(clients);
		clients.remove(serverClient);
		System.out.println(clients);

		for (ServerClient s : clients) {
			System.out.println(s);
			s.updateOnlineClients();

		}

	}

    public void passOnRequest(RequestMessage requestMessage) {
        String to = requestMessage.getTo();

        for (ServerClient sc : clients) {
            if (sc.getName().equals(to)) {
                sc.sendObject(requestMessage);
            }
        }
    }
}
