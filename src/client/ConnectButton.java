package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class ConnectButton extends JButton implements ActionListener {
	
	private JTextField loginName;
	private JTextField ip;
	private JTextField port;
	private ClientModel clientModel;

	public ConnectButton(JTextField loginName, JTextField ip, JTextField port, ClientModel clientModel){
		this.loginName = loginName;
		this.ip = ip;
		this.port = port;
		this.clientModel = clientModel;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		clientModel.setLoginName(loginName.getText());
		clientModel.setIp(ip.getText());
		clientModel.setPort(port.getText());

	}

}
