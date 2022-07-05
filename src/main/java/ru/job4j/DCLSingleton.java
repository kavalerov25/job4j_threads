package ru.job4j;

public final class DCLSingleton {

    /**
     * Cитуация называется проблемой видимости (share visibility problem).
     * Переменная inst должна быть volatile, так как возможно ситуация, когда один
     * из потоков прочитает не полностью сконструированный объект
     */

    private static volatile DCLSingleton inst;

    private DCLSingleton() {
    }

    public static DCLSingleton instOf() {
        if (inst == null) {
            synchronized (DCLSingleton.class) {
                if (inst == null) {
                    inst = new DCLSingleton();
                }
            }
        }
        return inst;
    }

}