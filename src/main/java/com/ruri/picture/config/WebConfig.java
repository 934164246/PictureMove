package com.ruri.picture.config;

import com.ruri.picture.utils.SystemInfoPersistenceUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

/**
 * web设置
 * @author gokoururi
 */
@Configuration
public class WebConfig implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        //系统启动的时候去读取SystemInfo.txt中的数据
        SystemInfoPersistenceUtils.read();
    }
}
