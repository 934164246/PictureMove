package com.ruri.utils;


import com.ruri.info.PathInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 系统基本信息
 *
 * @author gokoururi
 */
public final class SystemBaseInfo {

    /**
     * 文件夹
     */
    private static List<String> fileFolder = new ArrayList<>();

    /**
     * 所有路径
     */
    private static List<PathInfo> filePath = new ArrayList<>();

    /**
     * 文件格式
     */
    private static List<String> fileFormat = new ArrayList<>(Arrays.asList("jpg", "png", "gif", "jpeg"));


    public static List<String> getFileFolder() {
        return fileFolder;
    }

    public static void setFileFolder(List<String> fileFolder) {
        SystemBaseInfo.fileFolder = fileFolder;
    }

    public static List<PathInfo> getFilePath() {
        return filePath;
    }

    public static void setFilePath(List<PathInfo> filePath) {
        SystemBaseInfo.filePath = filePath;
    }

    public static List<String> getFileFormat() {
        return fileFormat;
    }

    public static void setFileFormat(List<String> fileFormat) {
        SystemBaseInfo.fileFormat = fileFormat;
    }
}