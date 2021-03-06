package ru.job4j;

public final class Cache {
    private static Cache cache;

    /**
     * Атомарность говорит о том, что некоторое действие (или их последовательность) должно происходить "все и сразу".
     * операция не атомарна
     * Определить не атомарные операции просто.
     * Все операции, где данные зависят от начального состояния не атомарны. Эти операции можно описать через процесс "проверить и выполнить".
     *
     * В этой операции происходит проверка на null, потом присвоение и возврат значения. Операция
     * происходит поэтапно. То есть одна нить может вызвать этот метод и проверить, что объект
     * равен null и затем перейти к присвоению, а в этот момент другая нить может вызвать этот же метод и тоже проверка вернет
     * null, так как первая нить еще не успела присвоить значение.
     * @return cache
     * Поэтому, если вам просто нужно изменять одну переменную с помощью нескольких потоков, лучше выбирать атомарные классы.
     */
    public synchronized static Cache instOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }
}

