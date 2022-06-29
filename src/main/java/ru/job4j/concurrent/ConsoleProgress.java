package ru.job4j.concurrent;

/**
 * Class ConsoleProgress - Прерывание потоков.
 * 1. Threads. 3. Прерывание нити[#283074].
 *
 * @author Kirill Kavalerov (kavalerov24@gmail.com)
 * @version 1
 * @since 29.06.2022
 */
public class ConsoleProgress implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(10000);
        progress.interrupt();
    }

    @Override
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted()) {
            if (count == 4) {
                count = 0;
            }
            char[] chars = new char[]{'-', '\\', '|', '/'};
            System.out.print("\r loading: " + chars[count]);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                break;
            }
            count++;
        }
    }
}
