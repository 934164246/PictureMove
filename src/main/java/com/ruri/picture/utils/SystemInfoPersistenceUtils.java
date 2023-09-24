package com.ruri.picture.utils;

import com.alibaba.fastjson2.JSONObject;
import com.ruri.picture.info.SystemInfoModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * 系统信息变量持久化工具
 * @author gokoururi
 */
public class SystemInfoPersistenceUtils {

    private static final Logger logger = LoggerFactory.getLogger(SystemInfoPersistenceUtils.class);

    /**
     * 系统变量存放文件名称
     */
    private final static String FILE_NAME = "SystemInfo.txt";

    public static void read() {
        // 读取资源文件
        Resource resource = new ClassPathResource(FILE_NAME);
        byte[] fileData = new byte[0];

        try {
            fileData = FileCopyUtils.copyToByteArray(resource.getInputStream());
        } catch (Exception e) {
            logger.error("发生错误：", e);
        }

        String fileContent = new String(fileData, StandardCharsets.UTF_8);

        if (!StringUtils.hasLength(fileContent)) {
            return;
        }

        SystemInfoModel model = JSONObject.parseObject(fileContent, SystemInfoModel.class);

        SystemInfoUtils.setFileFolder(model.getFileFolder());
        SystemInfoUtils.setFilePath(model.getFilePath());
        SystemInfoUtils.setFileFormat(model.getFileFormat());

        logger.info("File Content:{}", model);
    }

    public static void write(SystemInfoModel systemInfoModel) {
        logger.info("将要写入的内容：{}", systemInfoModel);

        String contentToWrite = JSONObject.toJSONString(systemInfoModel);

        try {
            Resource resource = new ClassPathResource(FILE_NAME);
            OutputStream outputStream = Files.newOutputStream(resource.getFile().toPath());

            // 将内容写入文件
            FileCopyUtils.copy(contentToWrite.getBytes(StandardCharsets.UTF_8), outputStream);

            // 关闭输出流
            outputStream.close();
        } catch (Exception e) {
            logger.error("发生错误，错误内容：", e);
        }
    }
}
