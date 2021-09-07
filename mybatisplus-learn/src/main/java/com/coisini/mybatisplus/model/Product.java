package com.coisini.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@TableName("product")
public class Product {

    private Integer id;

    private String title;

    @JsonIgnore
    private Date createTime;

}
