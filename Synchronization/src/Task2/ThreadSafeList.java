package Task2;
import java.util.ArrayList;

public class ThreadSafeList {
    private final ArrayList<String> list = new ArrayList<>();

    public synchronized void add(String element) {
        list.add(element);
    }

    public synchronized boolean remove(String element) {
        return list.remove(element);
    }

    public synchronized int size() {
        return list.size();
    }
}
