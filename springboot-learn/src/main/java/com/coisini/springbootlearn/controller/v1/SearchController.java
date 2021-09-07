package com.coisini.springbootlearn.controller.v1;

import com.coisini.springbootlearn.core.common.PageCounter;
import com.coisini.springbootlearn.core.common.PagingDozer;
import com.coisini.springbootlearn.model.Spu;
import com.coisini.springbootlearn.service.SpuService;
import com.coisini.springbootlearn.util.CommonUtil;
import com.coisini.springbootlearn.vo.SpuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description Search 控制器
 * @author coisini
 * @date Aug 26, 2021
 * @Version 1.0
 */
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SpuService spuService;

    /**
     * 根据关键字搜索Spu
     * @param q
     * @param start
     * @param count
     * @return
     */
    @GetMapping()
    public PagingDozer<Spu, SpuVo> search(@RequestParam String q,
                                          @RequestParam(defaultValue = "0") Integer start,
                                          @RequestParam(defaultValue = "10") Integer count){
        // 分页请求对象
        PageCounter pageCounter = CommonUtil.convertToPageParameter(start, count);

        // 分页返回数据
        Page<Spu> page = spuService.getLatestPagingSpuByTitle(q, pageCounter.getPage(), pageCounter.getCount());

        // 分页拷贝对象返回
        return new PagingDozer<>(page, SpuVo.class);
    }



}
