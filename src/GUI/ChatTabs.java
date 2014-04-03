package GUI;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import client.ClientModel;
import client.HistoryArea;

@SuppressWarnings("serial")
public class ChatTabs extends JTabbedPane {
	ClientModel clientModel;

	public ChatTabs(ClientModel clientModel) {
		this.clientModel = clientModel;
		
	}
	
	@Override
	public void addTab(String title, Component component) {
		JScrollPane scroll = new JScrollPane(component);
		scroll.setBounds(17, 19, 490, 219);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		super.addTab(title, scroll);
	}
}
