package com.coisini.mongodb.vo;

import cn.hutool.core.bean.BeanUtil;
import com.coisini.mongodb.model.MongoFile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Objects;

/**
 * @Description 统一文件下载vo
 * @author lit@epsoft.com.cn
 * @date Apr 8, 2022
 * @version 1.0
 */
@Data
public class FileExportVo {

    private String fileId;

    private String fileName;

    private String contentType;

    private String suffix;

    private long fileSize;

    @JsonIgnore
    private byte[] data;

    public FileExportVo(MongoFile mongoFile) {
        BeanUtil.copyProperties(mongoFile, this);
        if (Objects.nonNull(mongoFile.getContent())) {
            this.data = mongoFile.getContent().getData();
        }
        this.fileId = mongoFile.getId();
    }

}
