package com.coisini.file.config;

import com.coisini.file.factory.YmlPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Description 文件上传属性配置类
 * @author coisini
 * @date Sep 7, 2021
 * @Version 1.0
 */
@Component
@ConfigurationProperties(prefix = "file")
@PropertySource(value = "classpath:application.yml",
        encoding = "UTF-8",factory = YmlPropertySourceFactory.class)
public class FilePropertiesConfiguration {

    private static final String[] DEFAULT_EMPTY_ARRAY = new String[0];

    private String storeDir = "/assets";

    private String singleLimit = "2MB";

    private Integer nums = 10;

    private String domain;

    private String[] exclude = DEFAULT_EMPTY_ARRAY;

    private String[] include = DEFAULT_EMPTY_ARRAY;

    public String getStoreDir() {
        return storeDir;
    }

    public void setStoreDir(String storeDir) {
        this.storeDir = storeDir;
    }

    public String getSingleLimit() {
        return singleLimit;
    }

    public void setSingleLimit(String singleLimit) {
        this.singleLimit = singleLimit;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public String[] getExclude() {
        return exclude;
    }

    public void setExclude(String[] exclude) {
        this.exclude = exclude;
    }

    public String[] getInclude() {
        return include;
    }

    public void setInclude(String[] include) {
        this.include = include;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
