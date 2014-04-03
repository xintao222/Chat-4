package client;

public class SavedChatHistory implements Comparable<SavedChatHistory> {
	private String history = "Welcome!";
	private String chatWith;

	public SavedChatHistory(String chatWith) {
		this.chatWith = chatWith;
	}

	public void append(String from, String message) {
		String newLine = "\n" + from + ": " + message;
		history += newLine;
	}

	public String getHistory() {
		return history;
	}

	@Override
	public int compareTo(SavedChatHistory o) {

		return chatWith.compareTo(o.getchatWith());
	}

	public String getchatWith() {
		return chatWith;
	}
	
	public int hashCode(){
		return chatWith.hashCode();
	}

}
