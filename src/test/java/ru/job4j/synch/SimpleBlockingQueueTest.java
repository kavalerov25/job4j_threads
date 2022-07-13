package ru.job4j.synch;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void whenProducerAndConsumerInteract() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        int[] expect = {1, 2, 3, 4, 5};
        int[] actual = new int[5];
        Thread producer = new Thread(() -> {
            for (int i : expect) {
                queue.offer(i);
            }
        });
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < actual.length; i++) {
                actual[i] = queue.poll();
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertArrayEquals(expect, actual);
    }
}