package commons;
import java.io.Serializable;

public class TestObject implements Serializable {
	int value;
	public String id;

	public TestObject(int v, String s) {
		this.value = v;
		this.id = s;
	}
}