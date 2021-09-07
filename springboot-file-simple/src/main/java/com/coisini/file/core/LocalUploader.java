package com.coisini.file.core;

import com.coisini.file.config.FilePropertiesConfiguration;
import com.coisini.file.model.FileModel;
import com.coisini.file.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.PostConstruct;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description 本地上传
 * @author coisini
 * @date Sep 7, 2021
 * @Version 1.0
 */
@Slf4j
public class LocalUploader implements Uploader {

    @Autowired
    private FilePropertiesConfiguration filePropertiesConfiguration;

    /**
     * 初始化本地存储
     * 依赖注入完成后初始化
     */
    @PostConstruct
    public void initStoreDir() {
        System.out.println("initStoreDir start：" + this.filePropertiesConfiguration.getStoreDir());
        FileUtil.initStoreDir(this.filePropertiesConfiguration.getStoreDir());
        System.out.println("initStoreDir end");
    }

    /**
     * 文件上传
     * @param fileMap 文件map
     * @return
     */
    @Override
    public List<FileModel> upload(MultiValueMap<String, MultipartFile> fileMap) {
        // 检查文件
        checkFileMap(fileMap);
        return handleMultipartFiles(fileMap);
    }

    /**
     * 文件配置
     * @return
     */
    protected FilePropertiesConfiguration getFilePropertiesConfiguration() {
        return filePropertiesConfiguration;
    }

    /**
     * 单个文件体积限制
     * @return
     */
    private long getSingleFileLimit() {
        String singleLimit = getFilePropertiesConfiguration().getSingleLimit();
        return FileUtil.parseSize(singleLimit);
    }

    /**
     * 检查文件
     * @param fileMap
     */
    protected void checkFileMap(MultiValueMap<String, MultipartFile> fileMap){
        if (fileMap.isEmpty()) {
            throw new RuntimeException("file not found");
        }

        // 上传文件数量限制
        int nums = getFilePropertiesConfiguration().getNums();
        if (fileMap.size() > nums) {
            throw new RuntimeException("too many files, amount of files must less than" + nums);
        }
    }

    /**
     * 文件处理
     * @param fileMap
     * @return
     */
    protected List<FileModel> handleMultipartFiles(MultiValueMap<String, MultipartFile> fileMap) {
        long singleFileLimit = getSingleFileLimit();
        List<FileModel> res = new ArrayList<>();
        fileMap.keySet().forEach(key -> fileMap.get(key).forEach(file -> {
            if (!file.isEmpty()) {
                handleOneFile(res, singleFileLimit, file);
            }
        }));
        return res;
    }

    /**
     * 单文件处理
     * @param res
     * @param singleFileLimit
     * @param file
     */
    private void handleOneFile(List<FileModel> res, long singleFileLimit, MultipartFile file) {
        byte[] bytes = FileUtil.getFileBytes(file);
        String[] include = getFilePropertiesConfiguration().getInclude();
        String[] exclude = getFilePropertiesConfiguration().getExclude();
        String ext = UploadHelper.checkOneFile(include, exclude, singleFileLimit, file.getOriginalFilename(), bytes.length);
        String newFilename = UploadHelper.getNewFilename(ext);
        String storePath = getStorePath(newFilename);
        // 生成文件的md5值
        String md5 = FileUtil.getFileMD5(bytes);
        FileModel fileModelData = FileModel.builder().
                name(newFilename).
                md5(md5).
                key(file.getName()).
                path(storePath).
                size(bytes.length).
                extension(ext).
                build();

        boolean ok = writeFile(bytes, newFilename);
        if (ok) {
            res.add(fileModelData);
        }
    }

    /**
     * 写入存储
     * @param bytes
     * @param newFilename
     * @return
     */
    protected boolean writeFile(byte[] bytes, String newFilename) {
        // 获取绝对路径
        String absolutePath =
                FileUtil.getFileAbsolutePath(filePropertiesConfiguration.getStoreDir(), getStorePath(newFilename));
        System.out.println("absolutePath:" + absolutePath);
        try {
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(absolutePath)));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            System.out.println("write file error:" + e);
            return false;
        }
        return true;
    }

    /**
     * 获取缓存地址
     * @param newFilename
     * @return
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    protected String getStorePath(String newFilename) {
        Date now = new Date();
        String format = new SimpleDateFormat("yyyy/MM/dd").format(now);
        Path path = Paths.get(filePropertiesConfiguration.getStoreDir(), format).toAbsolutePath();
        File file = new File(path.toString());
        if (!file.exists()) {
            file.mkdirs();
        }

        return Paths.get(format, newFilename).toString();
    }
}
