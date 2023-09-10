package com.ruri.picture.service.impl;

import com.ruri.picture.dto.PictureMoveInfoDTO;
import com.ruri.picture.info.PathInfo;
import com.ruri.picture.service.PictureMoveService;
import com.ruri.picture.utils.SystemInfoUtils;
import com.ruri.picture.utils.PictureUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * @author gokoururi
 */
@Service("pictureMoveService")
public class PictureMoveServiceImpl implements PictureMoveService {

    @Override
    public boolean move(PictureMoveInfoDTO info) {
        // 1. 检查参数是否正常
        if (info == null || !StringUtils.hasText(info.getPictureName()) || !StringUtils.hasText(info.getTargetPath())) {
            return false;
        }
        if (SystemInfoUtils.getFilePath(info.getPictureName()) == null) {
            return false;
        }


        // 2. 检查目标文件夹
        PathInfo filePath = SystemInfoUtils.getFilePath(info.getTargetPath());
        if (filePath == null) {
            return false;
        }

        // 3. 检查目标文件是否存在
        File newFile = new File(info.getTargetPath() + "/" + info.getPictureName());
        if (newFile.exists()) {
            return false;
        }

        // 4. 检测是否对应得上
        File nowFile = PictureUtils.getNow();
        if (!nowFile.getName().equals(info.getPictureName())) {
            return false;
        }

        // 5. 移动图片
        return nowFile.renameTo(newFile);
    }
}
