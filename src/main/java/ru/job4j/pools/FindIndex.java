package ru.job4j.pools;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FindIndex<T> extends RecursiveTask<Integer> {
    private final List<T> array;
    private final int from;
    private final int to;
    private final T element;

    public FindIndex(List<T> array, int from, int to, T element) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.element = element;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            for (int i = from; i < to; i++) {
                if (array.get(i).equals(element)) {
                    return i;
                }
            }
            return -1;
        }
        int mid = (to + from) / 2;
        var left = new FindIndex<>(array, from, mid, element);
        var right = new FindIndex<>(array, mid + 1, to, element);
        left.fork();
        right.fork();
        Integer leftIndex = left.join();
        Integer rightIndex = right.join();
        return leftIndex == -1 ? rightIndex : leftIndex;
    }

    public static <T> Integer find(List<T> array, int from, int to, T element) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new FindIndex<>(array, from, to, element));
    }

}