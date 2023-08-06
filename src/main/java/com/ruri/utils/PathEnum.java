package com.ruri.utils;

import java.util.*;

/**
 * @author GokouRuri
 */

public enum PathEnum {

    /**
     *
     */
    GENSHIN(1,"Genshin Impact", "D:\\Saved Pictures\\Genshin Impact"),
    GOKOU(2, "Gokou Ruri","D:\\Saved Pictures\\GokouRuri"),
    HONKAI(3, "Honkai Impact","D:\\Saved Pictures\\Honkai Impact 3"),
    SENSUAL(4, "Sensual","D:\\Saved Pictures\\Sensual"),
    STAR(5, "Star Rail","D:\\Saved Pictures\\Star Rail"),
    VUP(6, "Vup","D:\\Saved Pictures\\Vup"),
    HEAD(7, "Head","D:\\Saved Pictures\\头像"),
    GAME(8, "Game","D:\\Saved Pictures\\Game");

    private final int index;
    private final String name;
    private final String path;

    PathEnum(int index, String name, String path) {
        this.index=index;
        this.name=name;
        this.path=path+"\\";
    }

    public String getPath() {
        return path;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public static PathEnum getPath(int index) {
        for (PathEnum value : PathEnum.values()) {
            if (value.getIndex() == index) {
                return value;
            }
        }

        return null;
    }

    public static List<Map<String, Object>> getDropDown() {
        List<Map<String, Object>> result=new ArrayList<>();

        for (PathEnum value : PathEnum.values()) {
            Map<String, Object> map=new HashMap<>(16);
            map.put("name", value.getName());
            map.put("value", value.getIndex());

            result.add(map);
        }

        result.sort(Comparator.comparingInt(item -> (int)item.get("value")));

        return result;
    }
}
