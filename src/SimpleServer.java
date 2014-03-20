import java.net.*;
import java.io.*;

class testobject implements Serializable {
	int value;
	String id;

	public testobject(int v, String s) {
		this.value = v;
		this.id = s;
	}
}

public class SimpleServer {
	public static void main(String args[]) {
		int port = 4446;
		try {
			ServerSocket ss = new ServerSocket(port);
			Socket s = ss.accept();
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			testobject to = (testobject) ois.readObject();
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