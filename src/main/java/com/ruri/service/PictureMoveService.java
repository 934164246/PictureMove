package com.ruri.service;

import com.ruri.dto.PictureMoveInfoDTO;

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
