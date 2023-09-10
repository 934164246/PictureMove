package com.ruri.picture.service.impl;

import com.ruri.picture.dto.PathDTO;
import com.ruri.picture.dto.PictureMoveDTO;
import com.ruri.picture.info.PathInfo;
import com.ruri.picture.service.PictureService;
import com.ruri.picture.utils.PictureUtils;
import com.ruri.picture.utils.SystemInfoUtils;
import com.ruri.picture.vo.PictureInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GokouRuri
 */
@Service("pictureService")
public class PictureServiceImpl implements PictureService {

    private final static Logger logger = LoggerFactory.getLogger(PictureServiceImpl.class);


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

        for (PathInfo value : SystemInfoUtils.getFilePath()) {
            PathDTO dto = new PathDTO();

            dto.setKey(value.getName());
            dto.setValue(String.valueOf(value.getPath()));

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
}