package server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable {

	ArrayList<Client> clients;
	ServerSocket serverSocket;

	public Server() {
		clients = new ArrayList<Client>();

		int port = 4446;
		try {
			ServerSocket serverSocket = new ServerSocket(port);
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
				Client cl = new Client(socketCon);
				clients.add(cl);
				new Thread(cl).start();
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
		}

	}

	public static void main(String[] args) {
		Server server = new Server();
		new Thread(server).start();
	}
}
