package com.ruri.picture.dto;

/**
 * @author GokouRuri
 */
public class PictureMoveDTO {

    private byte[] bytes;

    private String fileName;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
