package com.coisini.file.core;

import com.coisini.file.model.FileModel;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * @Description 文件上传服务接口
 * @author coisini
 * @date Sep 7, 2021
 * @Version 1.0
 */
public interface Uploader {

    /**
     * 上传文件
     * @param fileMap 文件map
     * @return 文件数据
     */
    List<FileModel> upload(MultiValueMap<String, MultipartFile> fileMap);

}
