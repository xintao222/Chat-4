package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class SendButton extends JButton implements ActionListener {

	private WriteArea writeArea;
	private ClientModel clientModel;

	public SendButton(WriteArea writeArea, ClientModel clientModel) {
		super("Send");
		this.writeArea = writeArea;
		this.clientModel = clientModel;
		addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		clientModel.sendMessage(writeArea.getText());
		writeArea.setText("");
        writeArea.requestFocus();

	}
}
