package com.coisini.springbootlearn.service.impl;

import com.coisini.springbootlearn.model.Category;
import com.coisini.springbootlearn.repository.CategoryRepository;
import com.coisini.springbootlearn.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description Category 实现类
 * @author coisini
 * @date Aug 17, 2021
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Map<String, List<Category>> getAll() {
        List<Category> roots = categoryRepository.findAllByIsRootOrderBySortAsc(true);
        List<Category> subs = categoryRepository.findAllByIsRootOrderBySortAsc(false);
        Map<String, List<Category>> categories = new HashMap<>();
        categories.put("roots", roots);
        categories.put("subs", subs);
        return categories;
    }

}
