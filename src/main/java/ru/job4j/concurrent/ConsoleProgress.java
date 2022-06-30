package ru.job4j.concurrent;

import static java.lang.Thread.*;

/**
 * Class ConsoleProgress - Прерывание потоков.
 * 1. Threads. 3. Прерывание нити[#283074].
 *
 * @author Kirill Kavalerov (kavalerov24@gmail.com)
 * @version 1.1
 * @since 30.06.2022
 */
public class ConsoleProgress implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        sleep(10000);
        progress.interrupt();
    }

    @Override
    public void run() {
        int count = 0;

        while (!currentThread().isInterrupted()) {
            char[] chars = new char[]{'-', '\\', '|', '/'};
            System.out.print("\r load: " + chars[count]);
            count++;

            if (count == chars.length) {
                count = 0;
            }
            try {
                sleep(500);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
