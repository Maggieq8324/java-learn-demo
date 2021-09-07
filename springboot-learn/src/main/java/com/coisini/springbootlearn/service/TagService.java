package com.coisini.springbootlearn.service;

import com.coisini.springbootlearn.model.Tag;
import java.util.List;

/**
 * @Description Tag 接口
 * @author coisini
 * @date Aug 26, 2021
 * @Version 1.0
 */
public interface TagService {

    /**
     * 根据Type查找
     * @return
     */
    List<Tag> getTagsByType();

}
