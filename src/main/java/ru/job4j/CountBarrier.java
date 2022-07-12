package ru.job4j;

public class CountBarrier {

    private final Object monitor = this;
    private final int total;
    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public synchronized void count() {
        synchronized (monitor) {
            count++;
            monitor.notifyAll();
        }
    }

    public synchronized void await() {
        while (count < total) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


