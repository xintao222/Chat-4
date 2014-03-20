package client;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Color;


public class MainWindow extends JPanel {
		private ClientModel clientModel;

	public MainWindow(ClientModel clientModel) {
		this.clientModel = clientModel;
		
		setBackground(new Color(51, 0, 102));
		setLayout(null);

		WriteArea writePane = new WriteArea(clientModel);
//		writePane.setBounds(6, 275, 542, 108);

		HistoryArea historyArea = new HistoryArea(clientModel);
		
		historyArea.setEditable(false);
		historyArea.setBounds(6, 6, 542, 257);

		JScrollPane writeScroll = new JScrollPane(writePane);
		writeScroll.setBounds(6, 275, 542, 108);
//		writeScroll.setVerticalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		writeScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(writeScroll);

		JScrollPane scrollHistory = new JScrollPane(historyArea);
		scrollHistory.setBounds(6, 6, 542, 257);
//		scrollHistory.setVerticalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		scrollHistory.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		add(scrollHistory);

	}
}