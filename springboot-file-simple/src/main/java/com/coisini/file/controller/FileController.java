package com.coisini.file.controller;

import com.coisini.file.vo.FileVo;
import com.coisini.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description 文件上传控制器
 * @author coisini
 * @date Sep 7, 2021
 * @Version 1.0
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     * @param request
     * @return
     */
    @PostMapping("/upload")
    public List<FileVo> upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartHttpServletRequest = ((MultipartHttpServletRequest) request);
        MultiValueMap<String, MultipartFile> fileMap = multipartHttpServletRequest.getMultiFileMap();
        List<FileVo> files = fileService.upload(fileMap);
        return files;
    }

}
