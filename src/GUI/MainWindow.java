package GUI;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import client.ClientModel;
import client.HistoryArea;
import client.SendButton;
import client.WriteArea;

import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JButton;


public class MainWindow extends JPanel {

	public MainWindow(ClientModel clientModel) {		
		setBackground(SystemColor.scrollbar);
		setLayout(null);

		WriteArea writePane = new WriteArea(clientModel);
//		writePane.setBounds(6, 275, 542, 108);

		HistoryArea historyArea = new HistoryArea(clientModel);
		
		historyArea.setEditable(false);
		historyArea.setBounds(6, 6, 542, 257);

		JScrollPane writeScroll = new JScrollPane(writePane);
		writeScroll.setBounds(17, 250, 486, 56);
//		writeScroll.setVerticalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		writeScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(writeScroll);

		JScrollPane scrollHistory = new JScrollPane(historyArea);
		scrollHistory.setBounds(17, 19, 490, 207);
//		scrollHistory.setVerticalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		scrollHistory.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		add(scrollHistory);
		
		
		ArrayList<String> clientList = clientModel.getConnectedClients();
		String[] listEntries = new String[clientList.size()];
		listEntries = clientList.toArray(listEntries);

		JList list = new JList(listEntries);
		list.setBounds(519, 19, 154, 207);

		JScrollPane connectedScroll = new JScrollPane(list);
		connectedScroll.setLocation(515, 19);
		connectedScroll.setSize(161, 207);
		add(connectedScroll);

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListListener test = new ListListener(clientModel);
		list.addListSelectionListener(test);
		new BetterListModel(clientModel, list);
		
		SendButton btnSend = new SendButton(writePane, clientModel);
		btnSend.setBounds(519, 250, 154, 56);
		add(btnSend);

	}
}