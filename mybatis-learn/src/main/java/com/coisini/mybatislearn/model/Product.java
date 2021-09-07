package com.coisini.mybatislearn.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class Product {

    private Integer id;

    private String title;

    private Date createTime;

}
