package commons;

import java.io.Serializable;

public class SharedClient implements Serializable, Comparable<SharedClient>{

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
	
	public String toString(){
		return name;
	}

	@Override
	public int compareTo(SharedClient o) {
		return name.compareTo(o.getName());
	}
	
}
