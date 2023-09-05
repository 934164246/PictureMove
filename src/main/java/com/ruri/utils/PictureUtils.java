package com.ruri.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 图片工具类
 *
 * @author gokoururi
 */
public class PictureUtils {

    /**
     * 所有文件路径都临时存在于此
     */
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

    /**
     * 重制文件list
     */
    public static void resetFileList() {
        LIST.clear();

        addFile();
    }

    /**
     * 读取文件夹中所有的图片
     */
    public static void addFile() {
        List<String> fileFolder = SystemBaseInfo.getFileFolder();
        Set<String> fileFormat = new HashSet<>(SystemBaseInfo.getFileFormat());

        for (String folderStr : fileFolder) {
            File folder = new File(folderStr);

            File[] files = folder.listFiles();

            for (File file : files) {
                String[] split = file.getName().split("\\.");

                String fileType = split[split.length - 1].toLowerCase();
                if (!file.isDirectory() && fileFormat.contains(fileType)) {
                    LIST.add(file);
                }
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
