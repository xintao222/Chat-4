//package client;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import GUI.ChatTabs;
//
//public class CloseTabListener implements ActionListener {
//
//	private String tabName;
//	private ChatTabs chatTabs;
//
//	public CloseTabListener(ChatTabs chatTabs, String tabName) {
//		this.tabName = tabName;
//		this.chatTabs = chatTabs;
//	}
//
//	public String getTabName() {
//		return tabName;
//	}
//
//	public void actionPerformed(ActionEvent evt) {
//
//		int index = chatTabs.indexOfTab(getTabName());
//		if (index >= 0) {
//
//			chatTabs.removeTabAt(index);
//			// It would probably be worthwhile getting the source
//			// casting it back to a JButton and removing
//			// the action handler reference ;)
//
//		}
//
//	}
//
//}