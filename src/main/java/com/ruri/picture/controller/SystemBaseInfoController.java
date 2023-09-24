package com.ruri.picture.controller;

import com.ruri.picture.dto.PathDTO;
import com.ruri.picture.info.PathInfo;
import com.ruri.picture.service.PictureService;
import com.ruri.picture.utils.SystemInfoUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * @author gokoururi
 */
@RestController
@RequestMapping("system")
public class SystemBaseInfoController {

    @Resource(name = "pictureService")
    private PictureService pictureService;

    @PostMapping("setFolder")
    public boolean setFolder(@RequestBody List<String> folderList) {
        // 1. 判断目标文件是否为空
        if (CollectionUtils.isEmpty(folderList)) {
            return false;
        }

        // 2. 判断路径是否为文件夹
        for (String folderPath : folderList) {
            File file = new File(folderPath);

            if (!file.isDirectory()) {
                return false;
            }
        }

        // 3. 设置图片文件夹
        SystemInfoUtils.setFileFolder(folderList);

        return true;
    }

    @GetMapping("getFolder")
    public List<String> getFolder() {
        return SystemInfoUtils.getFileFolder();
    }

    @PostMapping("setPath")
    public boolean setPictureTargetPath(@RequestBody List<PathInfo> pathInfoList) {
        // 1. 判断目标路径是否为空
        if (CollectionUtils.isEmpty(pathInfoList)) {
            return false;
        }

        // 2. 判断路径是否为文件夹
        for (PathInfo pathInfo : pathInfoList) {
            File file = new File(pathInfo.getPath());

            if (!file.isDirectory()) {
                return false;
            }
        }

        // 3. 设置图片移动文件夹
        SystemInfoUtils.setFilePath(pathInfoList);

        return true;
    }


    @GetMapping("getPath")
    public List<PathDTO> getPictureTargetPath() {
        return pictureService.getPictureTargetPath();
    }


    @PostMapping("setFormat")
    public boolean setPictureFormat(@RequestBody List<String> pictureFormatList) {
        // 1. 判断图片格式列表是否为空
        if (CollectionUtils.isEmpty(pictureFormatList)) {
            return false;
        }

        // 2. 设置图片格式
        SystemInfoUtils.setFileFormat(pictureFormatList);

        return true;
    }

    @GetMapping("getFormat")
    public List<String> getPictureFormat() {
        return SystemInfoUtils.getFileFormat();
    }

    @GetMapping("getTotalFormat")
    public List<String> getTotalPictureFormat() {
        return SystemInfoUtils.getTotalPictureFormat();
    }
}
