package client;

import java.awt.Font;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import GUI.ChatTabs;

public class TabHandler implements Observer {
	private ClientModel clientModel;
	private ChatTabs chatTab;
	HashMap<String, HistoryArea> tabs;

	public TabHandler(ClientModel clientModel, ChatTabs chatTab) {
		this.clientModel = clientModel;
		this.chatTab = chatTab;
		clientModel.addObserver(this);
		tabs = new HashMap<String, HistoryArea>();

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		String chatWith = clientModel.chatsWith();
		
		if (tabs.containsKey(chatWith)) {
			tabs.get(chatWith).setText("Something");
		} else {
			HistoryArea ha = new HistoryArea(chatWith);
			ha.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			ha.setEditable(false);
			ha.setBounds(6, 6, 542, 257);
			tabs.put(chatWith, ha);
			chatTab.addTab(chatWith, ha);
		}
	}
}
