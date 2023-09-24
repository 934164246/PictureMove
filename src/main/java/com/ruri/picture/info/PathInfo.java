package com.ruri.picture.info;


/**
 * @author gokoururi
 */
public class PathInfo {

    private String name;

    private String path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "PathInfo{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
