package com.coisini.file.service;

import com.coisini.file.vo.FileVo;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * @Description 文件上传接口
 * @author coisini
 * @date Sep 7, 2021
 * @Version 1.0
 */
public interface FileService {

    /**
     * 上传文件
     * @param fileMap 文件map
     * @return 文件数据
     */
    List<FileVo> upload(MultiValueMap<String, MultipartFile> fileMap);

}
