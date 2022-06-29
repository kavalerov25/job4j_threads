package ru.job4j.concurrent;

/**
 * Class ThreadState - Состояние потоков.
 * 1. Threads. 1.1. Состояние нити.[#283070].
 *
 * @author Kirill Kavalerov (kavalerov24@gmail.com)
 * @version 2
 * @since 29.06.2022
 */

public class ThreadState {
    /**
     * Нить main должна дождаться завершения двух созданных нитей и вывести на консоль сообщение, что работа завершена.
     */
    public static void main(String[] args) {

        var first = new Thread(
                () -> {
                    System.out.println("Creating first thread: " + Thread.currentThread().getName());
                }
        );

        var second = new Thread(
                () -> {
                    System.out.println("Creating second thread: " + Thread.currentThread().getName());
                }
        );

        first.start();
        second.start();
        System.out.println(first.getState() + " " + " first thread");
        System.out.println(second.getState() + " " + " second thread");

        while (first.getState() != Thread.State.TERMINATED && second.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getState() + " " + "first");
            System.out.println(first.getState() + " " + "second");
        }

        System.out.println("The work is done");
    }
}