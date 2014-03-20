package client;

import javax.swing.JFrame;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class ChatClientGui extends JFrame implements Runnable{
	
	private ClientModel clientModel;
	
	public ChatClientGui(ClientModel clientModel){
		this.clientModel = clientModel;
		
	}

	@Override
	public void run() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setPreferredSize(new Dimension(400, 500));
		MainWindow mainGui = new MainWindow(clientModel);
		CardLayout layout = new CardLayout();
		LoginPanel loginPanel = new LoginPanel(clientModel);
		JPanel cardPanel = new JPanel(layout); //Might not need, depends on menus, => use the frame instead
		cardPanel.setLayout(layout);
		cardPanel.add(loginPanel, "1");
		cardPanel.add(mainGui, "2");
		getContentPane().add(cardPanel);
		cardPanel.setPreferredSize(new Dimension(400, 500));
		pack();
		setVisible(true);
		
		while(!loginPanel.isFinished()){
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		

		layout.next(cardPanel);
		cardPanel.setPreferredSize(new Dimension(555, 390));
		pack();

	
		
		
	}
}
