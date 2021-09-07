package com.coisini.file.service.impl;

import com.coisini.file.model.FileModel;
import com.coisini.file.core.Uploader;
import com.coisini.file.vo.FileVo;
import com.coisini.file.service.FileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 文件上传实现类
 * @author coisini
 * @date
 * @Version 1.0
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private Uploader uploader;

    @Value("${file.domain}")
    private String domain;

    @Value("${file.serve-path:assets/**}")
    private String servePath;

    @Override
    public List<FileVo> upload(MultiValueMap<String, MultipartFile> fileMap) {
        return uploader.upload(fileMap).stream().map(item ->{
            /**
             * 这里可以拿到文件具体信息
             * 在此做数据库保存记录操作等业务处理
             */
            return transform(item);
        }).collect(Collectors.toList());
    }

    /**
     * 出参序列化
     * @param fileModel
     * @return
     */
    private FileVo transform(FileModel fileModel) {
        FileVo model = new FileVo();
        BeanUtils.copyProperties(fileModel, model);
        String s = servePath.split("/")[0];
        model.setUrl(domain + s + "/" + fileModel.getPath());
        return model;
    }
}
