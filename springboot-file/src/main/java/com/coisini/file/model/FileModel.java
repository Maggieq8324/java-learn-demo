package com.coisini.file.model;

import lombok.Data;

/**
 * @author pedro@TaleLin
 */
@Data
public class FileModel {

    /**
     * 文件 key，上传时指定的
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
