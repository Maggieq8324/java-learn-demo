package com.coisini.file.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.coisini.file.core.file.File;
import com.coisini.file.core.file.FileConstant;
import com.coisini.file.core.file.Uploader;
import com.coisini.file.model.FileModel;
import com.coisini.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pedro@TaleLin
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private Uploader uploader;

    @Value("${file.domain}")
    private String domain;

    @Value("${file.serve-path:assets/**}")
    private String servePath;

    /**
     * 为什么不做批量插入
     * 1. 文件上传的数量一般不多，3个左右
     * 2. 批量插入不能得到数据的id字段，不利于直接返回数据
     * 3. 批量插入也仅仅只是一条sql语句的事情，如果真的需要，可以自行尝试一下
     */
    @Override
    public List<FileModel> upload(MultiValueMap<String, MultipartFile> fileMap) {
        List<FileModel> res = new ArrayList<>();
        uploader.upload(fileMap, file -> {

            /**
             * 在此做数据库保存记录操作
             * 返回true 保存图片 false 不保存
             */
            res.add(transform(file, file.getKey()));
            return true;
        });

        return res;
    }

    private FileModel transform(File file, String key) {
        FileModel model = new FileModel();
        BeanUtil.copyProperties(file, model);
        if (file.getType().equals(FileConstant.LOCAL)) {
            String s = servePath.split("/")[0];
            model.setUrl(domain + s + "/" + file.getPath());
        } else {
            model.setUrl(file.getPath());
        }
        model.setKey(key);
        return model;
    }
}
