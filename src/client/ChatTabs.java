package client;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import client.ClientModel;
import com.jidesoft.swing.JideTabbedPane;
//import client.CloseTabListener;

@SuppressWarnings("serial")
public class ChatTabs extends JideTabbedPane {
	ClientModel clientModel;

	public ChatTabs(ClientModel clientModel) {
		this.clientModel = clientModel;
        setShowCloseButton(true);
        setShowCloseButtonOnTab(true);

	}

	@Override
	public void addTab(String title, Component component) {
		JScrollPane scroll = new JScrollPane(component);
		scroll.setBounds(17, 19, 490, 219);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		super.addTab(title, scroll);

	}

    @Override
    public void removeTabAt(int index){
        super.removeTabAt(index);

    }
}
