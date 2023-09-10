package com.ruri.picture.service;

import com.ruri.picture.dto.PictureMoveInfoDTO;

/**
 * 图片移动服务类
 *
 * @author gokoururi
 */
public interface PictureMoveService {

    /**
     *  移动图片
     * @param info 信息
     * @return 是否移动成功
     */
    boolean move(PictureMoveInfoDTO info);
}
