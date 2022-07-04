package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Class Wget.
 * 1. Threads. 4. Скачивание файла с ограничением. [#144271]
 *
 * @author Kirill Kavalerov (kavalerov24@gmail.com)
 * @version 2
 * @since 4.07.2022
 */
public class Wget implements Runnable {
    private static final int DELAY_MS = 1000;
    private static final int BUFFER_BYTE = 1024;
    private final String url;
    private final int speed;
    private final String file;

    public Wget(String url, int speed, String file) {
        this.url = url;
        this.speed = speed;
        this.file = file;
    }

    public static void main(String[] args) throws InterruptedException {
        validate(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String file = args[2];
        Thread wget = new Thread(new Wget(url, speed, file));
        wget.start();
        wget.join();
    }

    private static void validate(String[] params) {
        if (params.length != 3) {
            throw new IllegalArgumentException("You have to enter 3 params: url, speed, writing path");
        }
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            byte[] dataBuffer = new byte[BUFFER_BYTE];
            int bytesRead;
            long start = System.currentTimeMillis();
            long interval;
            int totalBytes = 0;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                totalBytes += bytesRead;
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                if (totalBytes >= speed) {
                    interval = System.currentTimeMillis() - start;
                    if (interval < DELAY_MS)  {
                        Thread.sleep(DELAY_MS - interval);
                    }
                    totalBytes = 0;
                    start = System.currentTimeMillis();
                }
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
