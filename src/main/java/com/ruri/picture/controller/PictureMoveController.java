package com.ruri.picture.controller;

import com.alibaba.fastjson2.JSONObject;
import com.ruri.picture.dto.PictureMoveInfoDTO;
import com.ruri.picture.service.PictureMoveService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author gokoururi
 */
@RestController
@RequestMapping("operate")
public class PictureMoveController {

    @Resource(name = "pictureMoveService")
    private PictureMoveService pictureMoveService;

    @PostMapping("move")
    public boolean movePicture(@RequestBody String requestBody) {
        return pictureMoveService.move(JSONObject.parseObject(requestBody, PictureMoveInfoDTO.class));
    }
}
