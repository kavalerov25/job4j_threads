package ru.job4j.io;

import java.io.*;

public class SaveContent {
    private final File file;

    public SaveContent(File file) {
        this.file = file;
    }

    public synchronized  void saveContent(String content) {
        try (OutputStream o = new FileOutputStream(file)) {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
