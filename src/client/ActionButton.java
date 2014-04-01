package client;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

public class ActionButton extends AbstractAction {
	private WriteArea t;
	private ClientModel clientModel;

	public ActionButton(WriteArea t, ClientModel clientModel) {
		this.t = t;
		this.clientModel = clientModel;
		t.getActionMap().put(t.getInputMap().get(KeyStroke.getKeyStroke("ENTER")), this);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		clientModel.sendMessage(t.getText());
		t.setText("");

	}

}
