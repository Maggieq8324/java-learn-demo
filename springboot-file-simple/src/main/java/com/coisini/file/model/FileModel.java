package com.coisini.file.model;

import lombok.*;

/**
 * @Description 文件具体信息，可存储数据库
 * @author coisini
 * @date Sep 7, 2021
 * @Version 1.0
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileModel {

    /**
     * url
     */
    private String url;

    /**
     * key
     */
    private String key;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 扩展名，例：.jpg
     */
    private String extension;

    /**
     * 文件大小
     */
    private Integer size;

    /**
     * md5值，防止上传重复文件
     */
    private String md5;
}
