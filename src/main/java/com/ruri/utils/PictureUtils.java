package com.ruri.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class PictureUtils {

    public static final ArrayList<File> LIST=new ArrayList<>(1600);

    public static AtomicInteger index =new AtomicInteger(0);

    static {
        File file=new File("D:\\Saved Pictures");

        File[] files = file.listFiles();

        for (File temp : files) {
            String[] split = temp.getName().split("\\.");

            String type = split[split.length - 1].toLowerCase();
            if (!temp.isDirectory() && ("jpg".equals(type) || "png".equals(type) || "jpeg".equals(type))) {
                LIST.add(temp);
            }
        }
    }

    public static File getNow() {
        return LIST.get(index.get());
    }

    public static File getPrev() {
        int i = index.get();

        if( i != 0) {
            i--;
            index.set(i);
        }

        return LIST.get(i);
    }

    public static File getNext() {
        return LIST.get(index.incrementAndGet());
    }

    public static byte[] toByte(File file) {
        try {
            InputStream inputStream = Files.newInputStream(file.toPath());


            byte[] data=new byte[(int)file.length()];
            inputStream.read(data);

            return data;
        } catch (IOException e) {
            return null;
        }
    }

    public static double convertBytesToMB(long bytes) {
        return (double) bytes / (1024 * 1024);
    }

    public static void changeCurrentPicture(File file) {
        LIST.set(index.get(), file);
    }
}
