package com.ruri.service.impl;

import com.ruri.dto.PathDTO;
import com.ruri.dto.PictureMoveDTO;
import com.ruri.service.PictureService;
import com.ruri.utils.PathEnum;
import com.ruri.utils.PictureUtils;
import com.ruri.vo.PictureInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GokouRuri
 */
@Slf4j
@Service("pictureService")
public class PictureServiceImpl implements PictureService {


    @Override
    public PictureMoveDTO getLocal() {
        File file = PictureUtils.getNow();

        byte[] bytes = PictureUtils.toByte(file);

        PictureMoveDTO dto = new PictureMoveDTO();
        dto.setBytes(bytes);
        dto.setFileName(file.getName());

        return dto;
    }

    @Override
    public PictureMoveDTO getNextIndex() {
        File file = PictureUtils.getNext();

        byte[] bytes = PictureUtils.toByte(file);

        PictureMoveDTO dto = new PictureMoveDTO();
        dto.setBytes(bytes);
        dto.setFileName(file.getName());

        return dto;
    }

    @Override
    public PictureMoveDTO getPrevIndex() {
        File file = PictureUtils.getPrev();

        byte[] bytes = PictureUtils.toByte(file);

        PictureMoveDTO dto = new PictureMoveDTO();
        dto.setBytes(bytes);
        dto.setFileName(file.getName());

        return dto;
    }

    @Override
    public List<PathDTO> getPictureTargetPath() {
        List<PathDTO> list = new ArrayList<>();

        for (PathEnum value : PathEnum.values()) {
            PathDTO dto = new PathDTO();

            dto.setKey(value.getName());
            dto.setValue(String.valueOf(value.getIndex()));

            list.add(dto);
        }

        return list;
    }

    @Override
    public PictureInfoVO getPictureInfo() {
        File file = PictureUtils.getNow();

        PictureInfoVO vo = new PictureInfoVO();
        vo.setName(file.getName());
        vo.setPath(file.getPath());
        vo.setSize(PictureUtils.convertBytesToMB(file.length()));

        return vo;
    }

    @Override
    public boolean movePicture(Integer type) {
        PathEnum path = PathEnum.getPath(type);

        File nowFile = PictureUtils.getNow();
        File targetFile = new File(path.getPath() + nowFile.getName());

        // 目标文件是否存在
        if(targetFile.exists()) {
            return false;
        }

        if (nowFile.renameTo(targetFile)) {
            PictureUtils.changeCurrentPicture(targetFile);

            log.info("【{}】文件移动到【{}】", nowFile.getName(), targetFile.getPath());
            return true;
        } else {
            log.error("【{}】文件移动失败", nowFile.getName());
            return false;
        }
    }
}