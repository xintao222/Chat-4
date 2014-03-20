import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionHandler implements Runnable {
	Socket s;
	ServerSocket ss;
	InputStream is;
	ObjectInputStream ois;
	boolean running = false;

	public ConnectionHandler(Socket clientSocket) throws IOException {

		is = s.getInputStream();
		ois = new ObjectInputStream(is);

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