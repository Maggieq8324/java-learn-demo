package com.coisini.mongodb.controller;

import com.coisini.mongodb.model.ResponseMessage;
import com.coisini.mongodb.service.FileUploadService;
import com.coisini.mongodb.vo.FileExportVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @Description 文件上传接口
 * @author coisini
 * @date Apr 17, 2022
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class FileUploadController {

    /**
     * 文件上传实现类
     */
    @Resource(name="${fileUploadService.impl}")
    private FileUploadService fileUploadService;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResponseMessage<?> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        try {
            return ResponseMessage.ok("上传成功", fileUploadService.uploadFile(file));
        } catch (Exception e) {
            log.error("文件上传失败：", e);
            return ResponseMessage.error(e.getMessage());
        }
    }

    /**
     * 多文件上传
     * @param files
     * @return
     */
    @PostMapping("/uploadFiles")
    public ResponseMessage<?> uploadFile(@RequestParam(value = "files") List<MultipartFile> files) {
        try {
            return ResponseMessage.ok("上传成功", fileUploadService.uploadFiles(files));
        } catch (Exception e) {
            return ResponseMessage.error(e.getMessage());
        }
    }

    /**
     * 文件下载
     * @param fileId
     * @return
     */
    @GetMapping("/download/{fileId}")
    public ResponseEntity<Object> fileDownload(@PathVariable(name = "fileId") String fileId) {
        FileExportVo fileExportVo = fileUploadService.downloadFile(fileId);

        if (Objects.nonNull(fileExportVo)) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "fileName=\"" + fileExportVo.getFileName() + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, fileExportVo.getContentType())
                    .header(HttpHeaders.CONTENT_LENGTH, fileExportVo.getFileSize() + "").header("Connection", "close")
                    .body(fileExportVo.getData());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("file does not exist");
        }
    }

    /**
     * 文件删除
     * @param fileId
     * @return
     */
    @DeleteMapping("/remove/{fileId}")
    public ResponseMessage<?> removeFile(@PathVariable(name = "fileId") String fileId) {
        fileUploadService.removeFile(fileId);
        return ResponseMessage.ok("删除成功");
    }

}
