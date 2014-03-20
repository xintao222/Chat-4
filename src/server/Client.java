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
	private Socket clientSocket;
	private ServerSocket ss;
	private InputStream is;
	private ObjectInputStream ois;
	private OutputStream outStream;
	private ObjectOutputStream objectOutStream;
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
			Object input = null;
			try {
				input = (Object) ois.readObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (input != null && input instanceof TestObject) {
				TestObject to = (TestObject) input;
				System.out.println("From client: " + to.id);
				try {
					objectOutStream.writeObject(new TestObject(1, "Hello mr, the server send this "));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
		}
		try {
			is.close();
			clientSocket.close();
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}