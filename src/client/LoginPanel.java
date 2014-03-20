package client;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.SystemColor;


public class LoginPanel extends JPanel {
	private JTextField txtGh;
	private JTextField txtLocalhost;
	private JTextField textField_1;

	
	public LoginPanel(){
		setBackground(UIManager.getColor("MenuBar.shadow"));
		setLayout(null);
		
		txtGh = new JTextField();
		txtGh.setHorizontalAlignment(SwingConstants.CENTER);
		txtGh.setText("Krantz");
		txtGh.setBounds(134, 69, 134, 28);
		add(txtGh);
		txtGh.setColumns(10);
		
		txtLocalhost = new JTextField();
		txtLocalhost.setHorizontalAlignment(SwingConstants.CENTER);
		txtLocalhost.setText("localhost");
		txtLocalhost.setBounds(134, 155, 134, 28);
		add(txtLocalhost);
		txtLocalhost.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setText("4446");
		textField_1.setBounds(134, 242, 134, 28);
		add(textField_1);
		textField_1.setColumns(10);
		
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
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.setBackground(SystemColor.menu);
		btnConnect.setForeground(SystemColor.textText);
		btnConnect.setBounds(144, 309, 117, 29);
		add(btnConnect);
		
	}
}
