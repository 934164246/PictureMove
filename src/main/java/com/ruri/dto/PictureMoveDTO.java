package com.ruri.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author GokouRuri
 */
@Data
@EqualsAndHashCode
public class PictureMoveDTO {

    private byte[] bytes;

    private String fileName;
}
