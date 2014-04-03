package client;

import java.awt.Font;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import GUI.ChatTabs;

public class TabHandler implements Observer, ChangeListener{
	private ClientModel clientModel;
	private ChatTabs chatTab;
	HashMap<String, HistoryArea> tabs;

	public TabHandler(ClientModel clientModel, ChatTabs chatTab) {
		this.clientModel = clientModel;
		this.chatTab = chatTab;
		clientModel.addObserver(this);
		tabs = new HashMap<String, HistoryArea>();
		createHistoryArea("Forever alone");
		chatTab.addChangeListener(this);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		String chatWith = clientModel.chatsWith();
		
		if (tabs.containsKey(chatWith)) {
			String history = clientModel.getHistory();
			tabs.get(chatWith).setText(history);
		} else {
			createHistoryArea(chatWith);
		}
	}

	private void createHistoryArea(String chatWith) {
		HistoryArea ha = new HistoryArea(chatWith);
		ha.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		ha.setEditable(false);
		ha.setBounds(6, 6, 542, 257);
		tabs.put(chatWith, ha);
		chatTab.addTab(chatWith, ha);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JScrollPane p = (JScrollPane) chatTab.getSelectedComponent();

		JViewport viewport = p.getViewport(); 
		HistoryArea current = (HistoryArea) viewport.getView();
		
		System.out.println("Changes tab to: " + current.getName());

		clientModel.setChatWith(current.getName());
		
	}
}
