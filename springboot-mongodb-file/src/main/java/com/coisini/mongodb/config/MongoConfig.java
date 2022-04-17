package com.coisini.mongodb.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description MongoDB配置类
 * @author coisini
 * @date Apr 17, 2022
 * @version 1.0
 */
@Configuration
public class MongoConfig {
    /**
     * 数据库配置信息
     */
    @Value("${spring.data.mongodb.database}")
    private String db;

    /**
     * GridFSBucket用于打开下载流
     * @param mongoClient
     * @return
     */
    @Bean
    public GridFSBucket getGridFSBucket(MongoClient mongoClient){
        MongoDatabase mongoDatabase = mongoClient.getDatabase(db);
        return GridFSBuckets.create(mongoDatabase);
    }
}
