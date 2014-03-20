package client;
import java.net.*;
import java.io.*;

import commons.TestObject;

public class SimpleClient {
	public SimpleClient() throws UnknownHostException, IOException {
		Socket s = new Socket("localhost", 4446);
		OutputStream os = s.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		TestObject to = new TestObject(1, "object from client");
		oos.writeObject(to);
		oos.writeObject(new String("another object from the client"));
		oos.close();
		os.close();
		s.close();

	}
}