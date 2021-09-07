package com.coisini.file.vo;

import lombok.Data;

/**
 * @Description 文件出参
 * @author coisini
 * @date Sep 7, 2021
 * @Version 1.0
 */
@Data
public class FileVo {

    /**
     * 文件 key
     */
    private String key;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件 URL
     */
    private String url;
}
