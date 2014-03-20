package client;

import javax.swing.JFrame;

import java.awt.CardLayout;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class ChatClientGui {

	public ChatClientGui(){
		JFrame frame = new JFrame();
		
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		new ChatClientGui();
	}
}
