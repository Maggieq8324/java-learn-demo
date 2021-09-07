package com.coisini.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.coisini.mybatisplus.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author generator@xxx
 * @since 2021-09-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("test")
public class Test extends BaseEntity {


    private String title;


}
