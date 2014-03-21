package client;
import java.net.*;
import java.io.*;

import commons.ClientListFromServer;

public class SimpleClient {
//	public SimpleClient() throws UnknownHostException, IOException {
//		Socket s = new Socket("localhost", 4446);
//		OutputStream os = s.getOutputStream();
//		ObjectOutputStream oos = new ObjectOutputStream(os);
////		ClientListFromServer to = new ClientListFromServer(null);
//		oos.writeObject(to);
////		oos.close();
////		os.close();
////		s.close();
//		InputStream is;
//		ObjectInputStream ois;
//		is = s.getInputStream();
//		ois = new ObjectInputStream(is);
//		try {
//			to = (ClientListFromServer) ois.readObject();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if (to != null) {
//			System.out.println(to.id +"yay");
//
//		}
//		while(true){
//			
//		}

//	}
	public static void main(String[] args) throws UnknownHostException, IOException {
		new SimpleClient();
	}
}