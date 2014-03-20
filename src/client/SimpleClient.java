package client;
import java.net.*;
import java.io.*;

import commons.TestObject;

public class SimpleClient {
	public SimpleClient() throws UnknownHostException, IOException {
		Socket s = new Socket("localhost", 4446);
		OutputStream os = s.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		TestObject to = new TestObject(2, "object from client");
		oos.writeObject(to);
		oos.close();
		os.close();
		s.close();
	}
	public static void main(String[] args) throws UnknownHostException, IOException {
		new SimpleClient();
	}
}