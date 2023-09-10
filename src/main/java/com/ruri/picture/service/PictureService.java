package com.ruri.picture.service;

import com.ruri.picture.dto.PathDTO;
import com.ruri.picture.dto.PictureMoveDTO;
import com.ruri.picture.vo.PictureInfoVO;

import java.util.List;

/**
 * @author GokouRuri
 */
public interface PictureService {

    PictureMoveDTO getLocal();

    /**
     * 获取坐标
     * @return int
     */
    PictureMoveDTO getNextIndex();

    PictureMoveDTO getPrevIndex();

    List<PathDTO> getPictureTargetPath();

    PictureInfoVO getPictureInfo();
}
