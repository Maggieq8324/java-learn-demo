package com.coisini.springbootlearn.service.impl;

import com.coisini.springbootlearn.model.GridCategory;
import com.coisini.springbootlearn.repository.GridCategoryRepository;
import com.coisini.springbootlearn.service.GridCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description GridCategory 实现类
 * @author coisini
 * @date Aug 17, 2021
 * @Version 1.0
 */
@Service
public class GridCategoryServiceImpl implements GridCategoryService {

    @Autowired
    private GridCategoryRepository gridCategoryRepository;

    @Override
    public List<GridCategory> getGridCategoryList() {
        return gridCategoryRepository.findAll();
    }
}
