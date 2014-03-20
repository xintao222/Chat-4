package client;

import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class ChatClientGui extends JFrame{
	ClientModel clientModel;
	
	public ChatClientGui(ClientModel clientModel){
		this.clientModel = clientModel;
		LoginPanel loginPanel = new LoginPanel(clientModel);
		while(loginPanel.isFinished())
		MainGui mainGui = new MainGui(clientModel);
		CardLayout layout = new CardLayout();
		
		JPanel cardPanel = new JPanel(layout); //Might not need, depends on menus, => use the frame instead
		cardPanel.setLayout(layout);
		cardPanel.add(loginPanel);
		cardPanel.add(mainGui);
		getContentPane().add(cardPanel);
		
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		
	}
}
