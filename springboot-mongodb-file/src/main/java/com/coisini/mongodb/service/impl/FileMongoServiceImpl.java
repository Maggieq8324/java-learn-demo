package com.coisini.mongodb.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import com.coisini.mongodb.model.MongoFile;
import com.coisini.mongodb.repository.MongoFileRepository;
import com.coisini.mongodb.service.FileUploadService;
import com.coisini.mongodb.util.MD5Util;
import com.coisini.mongodb.vo.FileExportVo;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Description MongoDB文件上传实现类
 * @author coisini
 * @date Apr 17, 2022
 * @version 1.0
 */
@Slf4j
@Service("fileMongoServiceImpl")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileMongoServiceImpl implements FileUploadService {

    private final MongoFileRepository mongoFileRepository;
    private final MongoTemplate mongoTemplate;
    private final GridFsTemplate gridFsTemplate;
    private final GridFSBucket gridFSBucket;

    /**
     * 多文件上传
     * @param files
     * @return
     */
    @Override
    public List<FileExportVo> uploadFiles(List<MultipartFile> files) {

        return files.stream().map(file -> {
            try {
                return this.uploadFile(file);
            } catch (Exception e) {
                log.error("文件上传失败", e);
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * 文件上传
     * @param file
     * @return
     * @throws Exception
     */
    @Override
    public FileExportVo uploadFile(MultipartFile file) throws Exception {
        if (file.getSize() > 16777216) {
            return this.saveGridFsFile(file);
        } else {
            return this.saveBinaryFile(file);
        }
    }

    /**
     * 文件下载
     * @param fileId
     * @return
     */
    @Override
    public FileExportVo downloadFile(String fileId) {
        Optional<MongoFile> option = this.getBinaryFileById(fileId);

        if (option.isPresent()) {
            MongoFile mongoFile = option.get();
            if(Objects.isNull(mongoFile.getContent())){
                option = this.getGridFsFileById(fileId);
            }
        }

        return option.map(FileExportVo::new).orElse(null);
    }

    /**
     * 文件删除
     * @param fileId
     */
    @Override
    public void removeFile(String fileId) {
        Optional<MongoFile> option = this.getBinaryFileById(fileId);

        if (option.isPresent()) {
            if (Objects.nonNull(option.get().getGridFsId())) {
                this.removeGridFsFile(fileId);
            } else {
                this.removeBinaryFile(fileId);
            }
        }
    }

    /**
     * 删除Binary文件
     * @param fileId
     */
    public void removeBinaryFile(String fileId) {
        mongoFileRepository.deleteById(fileId);
    }

    /**
     * 删除GridFs文件
     * @param fileId
     */
    public void removeGridFsFile(String fileId) {
        // TODO 根据id查询文件
        MongoFile mongoFile = mongoTemplate.findById(fileId, MongoFile.class );
        if(Objects.nonNull(mongoFile)){
            // TODO 根据文件ID删除fs.files和fs.chunks中的记录
            Query deleteFileQuery = new Query().addCriteria(Criteria.where("filename").is(mongoFile.getGridFsId()));
            gridFsTemplate.delete(deleteFileQuery);
            // TODO 删除集合mongoFile中的数据
            Query deleteQuery = new Query(Criteria.where("id").is(fileId));
            mongoTemplate.remove(deleteQuery, MongoFile.class);
        }
    }

    /**
     * 保存Binary文件（小文件）
     * @param file
     * @return
     * @throws Exception
     */
    public FileExportVo saveBinaryFile(MultipartFile file) throws Exception {

        String suffix = getFileSuffix(file);

        MongoFile mongoFile = mongoFileRepository.save(
                MongoFile.builder()
                        .fileName(file.getOriginalFilename())
                        .fileSize(file.getSize())
                        .content(new Binary(file.getBytes()))
                        .contentType(file.getContentType())
                        .uploadDate(new Date())
                        .suffix(suffix)
                        .md5(MD5Util.getMD5(file.getInputStream()))
                        .build()
        );

        return new FileExportVo(mongoFile);
    }

    /**
     * 保存GridFs文件（大文件）
     * @param file
     * @return
     * @throws Exception
     */
    public FileExportVo saveGridFsFile(MultipartFile file) throws Exception {
        String suffix = getFileSuffix(file);

        String gridFsId = this.storeFileToGridFS(file.getInputStream(), file.getContentType());

        MongoFile mongoFile = mongoTemplate.save(
                MongoFile.builder()
                        .fileName(file.getOriginalFilename())
                        .fileSize(file.getSize())
                        .contentType(file.getContentType())
                        .uploadDate(new Date())
                        .suffix(suffix)
                        .md5(MD5Util.getMD5(file.getInputStream()))
                        .gridFsId(gridFsId)
                        .build()
        );

        return new FileExportVo(mongoFile);
    }

    /**
     * 上传文件到Mongodb的GridFs中
     * @param in
     * @param contentType
     * @return
     */
    public String storeFileToGridFS(InputStream in, String contentType){
        String gridFsId = IdUtil.simpleUUID();
        // TODO 将文件存储进GridFS中
        gridFsTemplate.store(in, gridFsId , contentType);
        return gridFsId;
    }

    /**
     * 获取Binary文件
     * @param id
     * @return
     */
    public Optional<MongoFile> getBinaryFileById(String id) {
        return mongoFileRepository.findById(id);
    }

    /**
     * 获取Grid文件
     * @param id
     * @return
     */
    public Optional<MongoFile> getGridFsFileById(String id){
        MongoFile mongoFile = mongoTemplate.findById(id , MongoFile.class );
        if(Objects.nonNull(mongoFile)){
            Query gridQuery = new Query().addCriteria(Criteria.where("filename").is(mongoFile.getGridFsId()));
            try {
                // TODO 根据id查询文件
                GridFSFile fsFile = gridFsTemplate.findOne(gridQuery);
                // TODO 打开流下载对象
                GridFSDownloadStream in = gridFSBucket.openDownloadStream(fsFile.getObjectId());
                if(in.getGridFSFile().getLength() > 0){
                    // TODO 获取流对象
                    GridFsResource resource = new GridFsResource(fsFile, in);
                    // TODO 获取数据
                    mongoFile.setContent(new Binary(IoUtil.readBytes(resource.getInputStream())));
                    return Optional.of(mongoFile);
                }else {
                    return Optional.empty();
                }
            }catch (IOException e){
                log.error("获取MongoDB大文件失败", e);
            }
        }

        return Optional.empty();
    }

    /**
     * 获取文件后缀
     * @param file
     * @return
     */
    private String getFileSuffix(MultipartFile file) {
        String suffix = "";
        if (Objects.requireNonNull(file.getOriginalFilename()).contains(".")) {
            suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        }
        return suffix;
    }

}
