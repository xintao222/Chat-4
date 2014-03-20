package client;

import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class ChatClientGui {

	public ChatClientGui(){
		JFrame frame = new JFrame();
		ClientModel clientModel = new ClientModel();
		LoginPanel loginPanel = new LoginPanel(clientModel);
		MainGui mainGui = new MainGui(clientModel);
		CardLayout layout = new CardLayout();
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		new ChatClientGui();
	}
}
