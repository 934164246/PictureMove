package com.ruri.controller;

import com.ruri.dto.PathDTO;
import com.ruri.dto.PictureMoveDTO;
import com.ruri.service.PictureService;
import com.ruri.utils.PathEnum;
import com.ruri.vo.PictureInfoVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author GokouRuri
 */
@RestController
@RequestMapping("picture")
public class PictureController {

    @Resource(name = "pictureService")
    private PictureService pictureService;

    @GetMapping("getNextPicture")
    public ResponseEntity<byte[]> getNextPicture() {
        PictureMoveDTO dto = pictureService.getNextIndex();

        HttpHeaders headers=new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+dto.getFileName());


        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(dto.getBytes());
    }

    @GetMapping("getLocalPicture")
    public ResponseEntity<byte[]> getLocalPicture() {
        PictureMoveDTO dto = pictureService.getLocal();

        HttpHeaders headers=new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+dto.getFileName());


        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(dto.getBytes());
    }

    @GetMapping("getPrevPicture")
    public ResponseEntity<byte[]> getPrevPicture() {
        PictureMoveDTO dto = pictureService.getPrevIndex();

        HttpHeaders headers=new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+dto.getFileName());


        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(dto.getBytes());
    }

    @GetMapping("getPictureInfo")
    public PictureInfoVO info() {
        return pictureService.getPictureInfo();
    }

    @PostMapping("setPictureLocation/{type}")
    public boolean setLocation(@PathVariable("type") Integer type) {
        return pictureService.movePicture(type);
    }

    @GetMapping("location")
    public List<PathDTO> getPictureTargetPath() {
        return pictureService.getPictureTargetPath();
    }
}
