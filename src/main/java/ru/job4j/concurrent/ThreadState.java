package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {

        Thread first = new Thread(
                () -> { }
        );

        Thread second = new Thread(
                () -> { }
        );
        System.out.println(first.getState() + " " + "New first thread");
        System.out.println(second.getState() + " " + "New second thread");
        first.start();
        second.start();

        while (first.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getState() + " " + "first");
        }

        while (second.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getState() + " " + "second");
        }
        System.out.println("The work is done");
    }
}