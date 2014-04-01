package GUI;

import javax.swing.JList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import client.ClientModel;

public class ListListener implements ListSelectionListener {
//	private String[] listEntries;
//	private JList list = new JList(listEntries);
	private String selection = "troll";
	private ClientModel clientModel;

	public ListListener(ClientModel clientModel) {
		this.clientModel = clientModel;

	}

	public String getSelected() {
		return selection;
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		boolean adjust = arg0.getValueIsAdjusting();
		if (!adjust) {
			JList list = (JList) arg0.getSource();
			int selections[] = list.getSelectedIndices();
			Object selectionValues[] = list.getSelectedValues();
			for (int i = 0, n = selections.length; i < n; i++) {
				if (i == 0) {
				}
				System.out.println("Listener chatting to : " + selectionValues[i].toString());
				clientModel.setChatWith(selectionValues[i].toString());
			}
		}

	}
}