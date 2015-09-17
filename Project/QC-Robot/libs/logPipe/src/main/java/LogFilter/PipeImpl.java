package LogFilter;

import java.util.ArrayList;
import java.util.List;

public class PipeImpl implements Pipe {
    private List buffer = new ArrayList();

    public synchronized boolean put(Object obj) {
        boolean bAdded = buffer.add(obj);
        notify();
        return bAdded;
    }

    public synchronized Object get() throws InterruptedException {
        while(buffer.isEmpty()) wait(); // wait if pipe empty
        Object obj = buffer.remove(0);
        return obj;
    }
}