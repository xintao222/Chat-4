package server;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import commons.TestObject;

public class ServerSend {
	
	Socket s;
	OutputStream outStream;
	ObjectOutputStream objectOutStream;

	public ServerSend(Socket s) throws IOException {
		this.s = s;
		outStream = s.getOutputStream();
		objectOutStream = new ObjectOutputStream(outStream);
		
	}

	public void sendToClient(TestObject to) throws IOException {
		objectOutStream.writeObject(to);
		objectOutStream.writeObject(new String("another object from the client"));
//		objectOutStream.close();
//		outStream.close();
//		s.close();

		
	}

}
