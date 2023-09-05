package com.ruri.dto;


/**
 * 图片移动基本信息DTO类
 *
 * @author gokoururi
 */
public class PictureMoveInfoDTO {

    private String targetPath;

    private String pictureName;


    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }
}
