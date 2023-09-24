package com.ruri.picture.utils;


import com.ruri.picture.info.PathInfo;
import com.ruri.picture.info.SystemInfoModel;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 系统基本信息
 *
 * @author gokoururi
 */
public final class SystemInfoUtils {

    /**
     * 文件夹
     */
    private static List<String> fileFolder = new ArrayList<>();

    /**
     * 所有路径
     */
    private static List<PathInfo> filePath = new ArrayList<>();

    /**
     * 文件路径缓存
     */
    private final static Map<String, PathInfo> FILE_PATH_MAP = new HashMap<>();

    /**
     * 文件格式
     */
    private static List<String> fileFormat = new ArrayList<>();

    private final static Set<String> FILE_FORMAT_SET = new HashSet<>(fileFormat);

    private final static Set<String> FILE_FORMAT_TOTAL = new HashSet<>(Arrays.asList("jpg", "png", "gif", "jpeg", "tif", "bmp", "svg"));


    public static List<String> getFileFolder() {
        return fileFolder;
    }

    public static void setFileFolder(List<String> fileFolder) {
        SystemInfoUtils.fileFolder = fileFolder;

        // 保存数据
        SystemInfoPersistenceUtils.write(SystemInfoUtils.getModelInfo());
    }

    public static List<PathInfo> getFilePath() {
        return filePath;
    }

    public static PathInfo getFilePath(String name) {
        return FILE_PATH_MAP.getOrDefault(name, null);
    }

    public static void setFilePath(List<PathInfo> filePath) {
        SystemInfoUtils.filePath = filePath;

        // 更新文件路径缓存
        FILE_PATH_MAP.clear();
        for (PathInfo pathInfo : filePath) {
            FILE_PATH_MAP.put(pathInfo.getPath(), pathInfo);
        }

        // 保存数据
        SystemInfoPersistenceUtils.write(SystemInfoUtils.getModelInfo());
    }

    public static boolean hasFileFormat(String name) {
        return FILE_FORMAT_SET.contains(name.substring(name.lastIndexOf(".") + 1));
    }

    public static List<String> getFileFormat() {
        return fileFormat;
    }

    public static void setFileFormat(List<String> fileFormat) {
        //过滤出合适的文件格式
        fileFormat = fileFormat.stream().peek(FILE_FORMAT_TOTAL::contains).collect(Collectors.toList());

        SystemInfoUtils.fileFormat = fileFormat;

        FILE_FORMAT_SET.clear();
        FILE_FORMAT_SET.addAll(fileFormat);

        // 保存数据
        SystemInfoPersistenceUtils.write(SystemInfoUtils.getModelInfo());
    }

    public static List<String> getTotalPictureFormat() {
        return new ArrayList<>(FILE_FORMAT_TOTAL);
    }

    /**
     * 获得model info，用于写入文件封装用
     *
     * @return systemInfoModel
     */
    public static SystemInfoModel getModelInfo() {
        SystemInfoModel model = new SystemInfoModel();

        model.setFileFolder(fileFolder);
        model.setFilePath(filePath);
        model.setFileFormat(fileFormat);

        return model;
    }
}