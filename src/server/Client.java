package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import commons.TestObject;

public class Client implements Runnable {
	Socket clientSocket;
	ServerSocket ss;
	InputStream is;
	ObjectInputStream ois;
	OutputStream outStream;
	ObjectOutputStream objectOutStream;
	boolean running = false;
	

	public Client(Socket clientSocket) throws IOException {
		this.clientSocket = clientSocket;
		is = clientSocket.getInputStream();
		ois = new ObjectInputStream(is);
		outStream = clientSocket.getOutputStream();
		objectOutStream = new ObjectOutputStream(outStream);

		running = true;
		
	}

	@Override
	public void run() {

		while (running) {
			TestObject to = null;
			try {
				to = (TestObject) ois.readObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (to != null) {
				System.out.println(to.id +"Yey");
				try {
					objectOutStream.writeObject(new TestObject(1, "works"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		try {
			is.close();
			s.close();
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}