package com.coisini.file.config;

import cn.hutool.core.util.StrUtil;
import com.coisini.file.core.file.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Spring MVC 配置
 *
 * @author pedro@TaleLin
 */
@Configuration(proxyBeanMethods = false)
@Slf4j
public class WebConfiguration implements WebMvcConfigurer {

    @Value("${file.store-dir:assets/}")
    private String dir;

    @Value("${file.serve-path:assets/**}")
    private String servePath;

    /**
     * 跨域
     * 注意： 跨域问题涉及安全性问题，这里提供的是最方便简单的配置，任何host和任何方法都可跨域
     * 但在实际场景中，这样做，无疑很危险，所以谨慎选择开启或者关闭
     * 如果切实需要，请咨询相关安全人员或者专家进行配置
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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // classpath: or file:
        registry.addResourceHandler(getDirServePath())
                .addResourceLocations("file:" + getAbsDir() + "/");
    }

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
