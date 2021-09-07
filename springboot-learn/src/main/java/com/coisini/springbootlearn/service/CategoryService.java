package com.coisini.springbootlearn.service;

import com.coisini.springbootlearn.model.Category;
import java.util.List;
import java.util.Map;

/**
 * @Description Category 接口
 * @author coisini
 * @date Aug 17, 2021
 * @Version 1.0
 */
public interface CategoryService {

    /**
     * 获取全部分类
     * @return
     */
    Map<String, List<Category>> getAll();

}
