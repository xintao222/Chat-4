package client;
import java.awt.event.WindowEvent;

import sun.awt.WindowClosingListener;


public class WindowCloseListener implements WindowClosingListener{

	@Override
	public RuntimeException windowClosingDelivered(WindowEvent arg0) {
		System.out.println("Exit");
		return null;
	}

	@Override
	public RuntimeException windowClosingNotify(WindowEvent arg0) {
		System.out.println("other exit");
		return null;
	}

}
