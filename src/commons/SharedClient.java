package commons;

import java.io.Serializable;

public class SharedClient implements Serializable, Comparable<SharedClient> {

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

    public String toString() {
        return name;
    }

    @Override
    public int compareTo(SharedClient o) {
        return name.compareTo(o.getName());
    }

    public boolean equals(Object o) {
        if (o instanceof SharedClient) {
            if (((SharedClient) o).getName().equals(name)){
                return true;
            }
        }
        return false;
    }
}
