package com.coisini.springbootlearn.service;

import com.coisini.springbootlearn.model.SaleExplain;
import java.util.List;

/**
 * @Description Sale Explain 接口
 * @author coisini
 * @date Aug 26, 2021
 * @Version 1.0
 */
public interface SaleExplainService {

    /**
     * 根据Fixed查找
     * @return
     */
    List<SaleExplain> getByFixed();

}
