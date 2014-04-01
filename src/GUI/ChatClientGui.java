package GUI;

import javax.swing.JFrame;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import client.ClientModel;

@SuppressWarnings("serial")
public class ChatClientGui extends JFrame{
	
	
	public ChatClientGui(){
		
		
	}

	public ClientModel init() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CardLayout layout = new CardLayout();
		LoginPanel loginPanel = new LoginPanel();
		JPanel cardPanel = new JPanel(layout); //Might not need, depends on menus, => use the frame instead
		cardPanel.setLayout(layout);
		cardPanel.add(loginPanel, "1");
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
		int port = loginPanel.getPort();
		String ip = loginPanel.getIp();
		String loginName = loginPanel.getLoginName();
		System.out.println("truefd");

		ClientModel model = new ClientModel(loginName, ip, port);
		MainWindow mainGui = new MainWindow(model);
		cardPanel.add(mainGui, "2");

		System.out.println("next card");
		layout.next(cardPanel);
		cardPanel.setPreferredSize(new Dimension(555, 390));
		pack();
		return model;

		
	}
}
