package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import client.ClientModel;

public class ConnectButton extends JButton implements ActionListener {
	
	private JTextField loginName;
	private JTextField ip;
	private JTextField port;
	private LoginPanel loginPanel;
	
	public ConnectButton(JTextField loginName, JTextField ip, JTextField port, LoginPanel loginPanel, String name){
		super(name);
		this.loginName = loginName;
		this.ip = ip;
		this.port = port;
		this.loginPanel = loginPanel;
		addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		loginPanel.setLoginName(loginName.getText());
		loginPanel.setIp(ip.getText());
		loginPanel.setPort(Integer.parseInt(port.getText()));
		loginPanel.setDone();
	}

}
