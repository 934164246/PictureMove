package com.ruri.picture.controller;

import com.ruri.picture.dto.PathDTO;
import com.ruri.picture.dto.PictureMoveDTO;
import com.ruri.picture.service.PictureService;
import com.ruri.picture.utils.PictureUtils;
import com.ruri.picture.vo.PictureInfoVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author GokouRuri
 */
@RestController
@RequestMapping("info")
public class PictureInfoController {

    @Resource(name = "pictureService")
    private PictureService pictureService;

    @GetMapping("getNextPicture")
    public ResponseEntity<byte[]> getNextPicture() {
        PictureMoveDTO dto = pictureService.getNextIndex();

        return PictureUtils.wrapPictureByte(dto.getFileName(), dto.getBytes());
    }

    @GetMapping("getLocalPicture")
    public ResponseEntity<byte[]> getLocalPicture() {
        PictureMoveDTO dto = pictureService.getLocal();

        return PictureUtils.wrapPictureByte(dto.getFileName(), dto.getBytes());
    }

    @GetMapping("getPrevPicture")
    public ResponseEntity<byte[]> getPrevPicture() {
        PictureMoveDTO dto = pictureService.getPrevIndex();

        return PictureUtils.wrapPictureByte(dto.getFileName(), dto.getBytes());
    }

    @GetMapping("getPictureInfo")
    public PictureInfoVO info() {
        return pictureService.getPictureInfo();
    }
}
