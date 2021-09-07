package com.coisini.mybatisplus.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * @Description 分页数据统一返回
 * @author coisini
 * @date Sep 7, 2021
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponseVo<T> {

    private long total;

    private List<T> items;

    private long page;

    private long count;
}

