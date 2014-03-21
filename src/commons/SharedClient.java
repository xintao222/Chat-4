package commons;

import java.io.Serializable;

public class SharedClient implements Serializable{

	private int id;
	private String name;

	public SharedClient(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}
}
