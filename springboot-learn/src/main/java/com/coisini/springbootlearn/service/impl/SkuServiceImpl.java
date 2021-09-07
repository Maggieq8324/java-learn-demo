package com.coisini.springbootlearn.service.impl;

import com.coisini.springbootlearn.model.Sku;
import com.coisini.springbootlearn.repository.SkuRepository;
import com.coisini.springbootlearn.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Description Sku 实现类
 * @author coisini
 * @date Aug 21, 2021
 * @Version 1.0
 */
@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuRepository skuRepository;

    @Override
    public List<Sku> getSkuListByIds(List<Long> ids) {
        return skuRepository.findAllByIdIn(ids);
    }
}
