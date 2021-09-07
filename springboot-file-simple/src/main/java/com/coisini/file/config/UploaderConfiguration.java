package com.coisini.file.config;

import com.coisini.file.core.LocalUploader;
import com.coisini.file.core.Uploader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @Description 文件上传配置类
 * @author coisini
 * @date Sep 7, 2021
 * @Version 1.0
 */
@Configuration
public class UploaderConfiguration {
    /**
     * @return 本地文件上传实现类
     */
    @Bean
    @Order
    @ConditionalOnMissingBean
    public Uploader uploader(){
        return new LocalUploader();
    }
}
