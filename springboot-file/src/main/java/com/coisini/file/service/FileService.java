package com.coisini.file.service;

import com.coisini.file.model.FileModel;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * @author pedro@TaleLin
 */
public interface FileService {

    /**
     * 上传文件
     * @param fileMap 文件map
     * @return 文件数据
     */
    List<FileModel> upload(MultiValueMap<String, MultipartFile> fileMap);

}
