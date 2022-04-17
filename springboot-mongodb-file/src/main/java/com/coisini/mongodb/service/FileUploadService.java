package com.coisini.mongodb.service;

import com.coisini.mongodb.vo.FileExportVo;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * @Description 文件上传接口
 * @author coisini
 * @date Apr 17, 2022
 * @version 1.0
 */
public interface FileUploadService {

    /**
     * 文件上传
     * @param file
     * @return
     */
    FileExportVo uploadFile(MultipartFile file) throws Exception;

    /**
     * 多文件上传
     * @param files
     * @return
     */
    List<FileExportVo> uploadFiles(List<MultipartFile> files);

    /**
     * 文件下载
     * @param fileId
     * @return
     */
    FileExportVo downloadFile(String fileId);

    /**
     * 文件删除
     * @param fileId
     */
    void removeFile(String fileId);

}
