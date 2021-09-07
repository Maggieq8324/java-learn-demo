package com.coisini.file.config;

import com.coisini.file.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * @Description Spring MVC 配置
 * @author coisini
 * @date Sep 7, 2021
 * @Version 1.0
 */
@Configuration(proxyBeanMethods = false)
@Slf4j
public class WebConfiguration implements WebMvcConfigurer {

    @Value("${file.store-dir:assets/}")
    private String dir;

    @Value("${file.serve-path:assets/**}")
    private String servePath;

    /**
     * 跨域设置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

    /**
     * 拦截处理请求信息
     * 添加文件真实地址
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(getDirServePath())
                .addResourceLocations("file:" + getAbsDir() + "/");
    }

    /**
     * 获取服务器url
     * @return
     */
    private String getDirServePath() {
        return servePath;
    }

    /**
     * 获得文件夹的绝对路径
     */
    private String getAbsDir() {
        if (FileUtil.isAbsolutePath(dir)) {
            return dir;
        }
        String cmd = System.getProperty("user.dir");
        Path path = FileSystems.getDefault().getPath(cmd, dir);
        return path.toAbsolutePath().toString();
    }


}
