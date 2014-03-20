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
				new Thread(new ConnectionHandler(socketCon)).start();
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
		}

	}

	public static void main(String[] args) {
		new Server();
	}
}
