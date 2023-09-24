package com.ruri.picture.info;

import java.io.Serializable;
import java.util.List;

/**
 * 系统变量模型，用于把系统变量存入txt文件中
 * @author gokoururi
 */
public class SystemInfoModel implements Serializable {

    /**
     * 文件夹
     */
    private List<String> fileFolder;

    /**
     * 所有路径
     */
    private List<PathInfo> filePath;

    /**
     * 文件格式
     */
    private List<String> fileFormat;

    public List<String> getFileFolder() {
        return fileFolder;
    }

    public void setFileFolder(List<String> fileFolder) {
        this.fileFolder = fileFolder;
    }

    public List<PathInfo> getFilePath() {
        return filePath;
    }

    public void setFilePath(List<PathInfo> filePath) {
        this.filePath = filePath;
    }

    public List<String> getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(List<String> fileFormat) {
        this.fileFormat = fileFormat;
    }

    @Override
    public String toString() {
        return "SystemInfoModel{" +
                "fileFolder=" + fileFolder +
                ", filePath=" + filePath +
                ", fileFormat=" + fileFormat +
                '}';
    }
}
