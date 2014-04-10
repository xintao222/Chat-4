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
//import client.CloseTabListener;

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
//		int index = indexOfTab(title);
//		JPanel pnlTab = new JPanel(new GridBagLayout());
//		pnlTab.setOpaque(false);
//		JLabel lblTitle = new JLabel(title);
//		JButton btnClose = new JButton("x");
//
//		GridBagConstraints gbc = new GridBagConstraints();
//		gbc.gridx = 0;
//		gbc.gridy = 0;
//		gbc.weightx = 1;
//
//		pnlTab.add(lblTitle, gbc);
//
//		gbc.gridx++;
//		gbc.weightx = 0;
//		pnlTab.add(btnClose, gbc);
//		setTabComponentAt(index, pnlTab);
//
//		btnClose.addActionListener(new CloseTabListener(this, title));
	}
}
