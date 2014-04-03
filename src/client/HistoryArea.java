package client;

import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class HistoryArea extends JTextArea implements Comparable<HistoryArea> {
	private String name;

	public HistoryArea(String name) {
		this.name = name;

	}

	public String getName() {
		return name;
	}

	@Override
	public int compareTo(HistoryArea o) {
		return name.compareTo(o.getName());
	}

	public int hashCode() {
		return name.hashCode();
	}
}
