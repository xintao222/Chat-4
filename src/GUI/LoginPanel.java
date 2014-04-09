package GUI;

import client.EnterHitLogin;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.SystemColor;

public class LoginPanel extends JPanel {
	private JTextField loginField;
	private JTextField ipField;
	private JTextField portField;
	private boolean done = false;
	private String loginName;
	private String ip;
	private int port;

	public LoginPanel() {
		setBackground(UIManager.getColor("MenuBar.shadow"));
		setLayout(null);

		loginField = new JTextField();
		loginField.setHorizontalAlignment(SwingConstants.CENTER);
		loginField.setText("Krantz");
		loginField.setBounds(134, 69, 134, 28);
		add(loginField);
		loginField.setColumns(10);

		ipField = new JTextField();
		ipField.setHorizontalAlignment(SwingConstants.CENTER);
		ipField.setText("localhost");
		ipField.setBounds(134, 155, 134, 28);
		add(ipField);
		ipField.setColumns(10);

		portField = new JTextField();
		portField.setHorizontalAlignment(SwingConstants.CENTER);
		portField.setText("4446");
		portField.setBounds(134, 242, 134, 28);
		add(portField);
		portField.setColumns(10);

		JLabel lblLoginName = new JLabel("Login name");
		lblLoginName.setForeground(SystemColor.textHighlight);
		lblLoginName.setBackground(Color.WHITE);
		lblLoginName.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginName.setBounds(159, 41, 81, 16);
		add(lblLoginName);

		JLabel lblServerIp = new JLabel("Server ip");
		lblServerIp.setForeground(SystemColor.textHighlight);
		lblServerIp.setHorizontalAlignment(SwingConstants.CENTER);
		lblServerIp.setBounds(159, 127, 81, 16);
		add(lblServerIp);

		JLabel lblPort = new JLabel("Port");
		lblPort.setForeground(SystemColor.textHighlight);
		lblPort.setHorizontalAlignment(SwingConstants.CENTER);
		lblPort.setBounds(159, 214, 81, 16);
		add(lblPort);

		ConnectButton btnConnect = new ConnectButton(loginField, ipField, portField, this, "Connect");
		btnConnect.setBackground(SystemColor.menu);
		btnConnect.setForeground(SystemColor.textText);
		btnConnect.setBounds(144, 309, 117, 29);
		add(btnConnect);

        loginField.addKeyListener(new EnterHitLogin(btnConnect));
        portField.addKeyListener(new EnterHitLogin(btnConnect));
        ipField.addKeyListener(new EnterHitLogin(btnConnect));

	}

	public boolean isFinished() {
		return done;
	}

	public void setDone() {
		done = true;

	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getIp() {
		return ip;
	}

	public void setLoginName(String text) {
		this.loginName = text;
	}

	public void setIp(String text) {
		this.ip = text;
	}

}
