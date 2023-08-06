package com.ruri.service;

import com.ruri.dto.PathDTO;
import com.ruri.dto.PictureMoveDTO;
import com.ruri.vo.PictureInfoVO;

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

    boolean movePicture(Integer type);
}
