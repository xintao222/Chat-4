import java.net.*;
import java.io.*;



public class SimpleServer {
	public static void main(String args[]) {
		int port = 4446;
		try {
			ServerSocket ss = new ServerSocket(port);
			Socket s = ss.accept();
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			TestObject to = (TestObject) ois.readObject();
			if (to != null) {
				System.out.println(to.id);
			}
			System.out.println((String) ois.readObject());
			is.close();
			s.close();
			ss.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}