import java.io.Serializable;

public class testObject implements Serializable {
	int value;
	String id;

	public testObject(int v, String s) {
		this.value = v;
		this.id = s;
	}
}