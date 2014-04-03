package GUI;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import client.ClientModel;
import client.HistoryArea;
import client.SendButton;
import client.TabHandler;
import client.WriteArea;

import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;


@SuppressWarnings("serial")
public class MainWindow extends JPanel {

	public MainWindow(ClientModel clientModel) {		
		setBackground(new Color(204, 255, 255));
		setLayout(null);

		WriteArea writePane = new WriteArea(clientModel);
		writePane.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
//		writePane.setBounds(6, 275, 542, 108);

		JScrollPane writeScroll = new JScrollPane(writePane);
		writeScroll.setBounds(17, 250, 486, 56);
		
		ChatTabs chatTabs = new ChatTabs(clientModel);
		
		chatTabs.setBounds(17, 19, 490, 219);
		

//		JScrollPane scrollHistory = new JScrollPane(historyArea);
//		scrollHistory.setBounds(17, 19, 490, 219);
//		scrollHistory.setVerticalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

//		scrollHistory.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
//		add(scrollHistory);
		
		
		ArrayList<String> clientList = clientModel.getConnectedClients();
		String[] listEntries = new String[clientList.size()];
		listEntries = clientList.toArray(listEntries);

		JList list = new JList(listEntries);
		new TabHandler(clientModel, chatTabs, list);
		add(chatTabs);
//		writeScroll.setVerticalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		writeScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(writeScroll);
		list.setForeground(new Color(51, 51, 102));
		list.setBounds(519, 19, 154, 207);

		JScrollPane connectedScroll = new JScrollPane(list);
		connectedScroll.setLocation(515, 19);
		connectedScroll.setSize(161, 219);
		add(connectedScroll);

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListListener test = new ListListener(clientModel);
		list.addListSelectionListener(test);
		new BetterListModel(clientModel, list);
		
		SendButton btnSend = new SendButton(writePane, clientModel);
		btnSend.setBounds(519, 250, 154, 56);
		add(btnSend);
		
		JLabel lblClientsOnline = new JLabel("Clients online");
		lblClientsOnline.setBackground(new Color(51, 51, 102));
		lblClientsOnline.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblClientsOnline.setForeground(Color.WHITE);
		lblClientsOnline.setEnabled(false);
		lblClientsOnline.setBounds(535, 0, 126, 22);
		add(lblClientsOnline);
		
		JLabel lblSignedInAs = new JLabel("Signed in as: " + clientModel.getLoginName());
		lblSignedInAs.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblSignedInAs.setForeground(new Color(51, 51, 102));
		lblSignedInAs.setBounds(188, 4, 207, 16);
		add(lblSignedInAs);

	}
}