package com.coisini.mongodb.repository;

import com.coisini.mongodb.model.MongoFile;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Description MongoDB文件仓储
 * @author coisini
 * @date Apr 17, 2022
 * @version 1.0
 */
public interface MongoFileRepository extends MongoRepository<MongoFile, String> {

}
