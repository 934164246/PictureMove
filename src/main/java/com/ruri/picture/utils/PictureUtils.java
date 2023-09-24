package com.ruri.picture.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

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
    public static final ArrayList<File> LIST = new ArrayList<>(1600);

    public static AtomicInteger index = new AtomicInteger(0);

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
        List<String> fileFolder = SystemInfoUtils.getFileFolder();
        Set<String> fileFormat = new HashSet<>(SystemInfoUtils.getFileFormat());

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
        if (CollectionUtils.isEmpty(LIST)) {
            return new File("");
        } else {
            return LIST.get(index.get());
        }
    }

    public static File getPrev() {
        int i = index.get();

        if (i != 0) {
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


            byte[] data = new byte[(int) file.length()];
            inputStream.read(data);

            return data;
        } catch (IOException e) {
            return new byte[0];
        }
    }

    public static double convertBytesToMB(long bytes) {
        return (double) bytes / (1024 * 1024);
    }

    public static void changeCurrentPicture(File file) {
        LIST.set(index.get(), file);
    }

    /**
     * 把文件信息打包
     * @param fileName    文件名称
     * @param pictureByte 图片字节
     * @return control返回体
     */
    public static ResponseEntity<byte[]> wrapPictureByte(String fileName, byte[] pictureByte) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);


        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(pictureByte);
    }
}
