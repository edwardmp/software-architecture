package LogFilter;

interface Pipe {
    boolean put(Object obj);
    Object get() throws InterruptedException;
}