package client;
import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Color;

import javax.swing.JScrollBar;

public class MainGui extends JPanel {
		private ClientModel clientModel;

	public MainGui(ClientModel clientModel) {
		this.clientModel = clientModel;

		setBackground(new Color(51, 0, 102));
		setLayout(null);

		WriteArea writePane = new WriteArea(clientModel);
//		writePane.setBounds(6, 275, 542, 108);

		JTextArea historyArea = new JTextArea();
		historyArea.setEditable(false);
		historyArea.setBounds(6, 6, 542, 257);

		JScrollPane writeScroll = new JScrollPane(writePane);
		writeScroll.setBounds(6, 275, 542, 108);
		writeScroll.setVerticalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		writeScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(writeScroll);

		JScrollPane scrollHistory = new JScrollPane(historyArea);
		scrollHistory.setBounds(6, 6, 542, 257);
		scrollHistory.setVerticalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		scrollHistory.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		add(scrollHistory);

	}
}