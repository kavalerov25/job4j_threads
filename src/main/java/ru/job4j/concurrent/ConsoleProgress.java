package ru.job4j.concurrent;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

/**
 * Class ConsoleProgress - Прерывание потоков.
 * 1. Threads. 3. Прерывание нити[#283074].
 *
 * @author Kirill Kavalerov (kavalerov24@gmail.com)
 * @version 1.2
 * @since 1.07.2022
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
                /** NB:
                 * В блоке catch нужно дополнительно вызывать прерывание нити для того, чтобы прерывания действительно произошло.
                 * схема является шаблоном. Запомните, если используются методы sleep(), join() или wait(), то нужно в блоке catch вызвать прерывание.
                 */
                Thread.currentThread().interrupt();
            }
        }
    }
}

