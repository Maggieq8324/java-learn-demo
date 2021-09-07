package com.coisini.springbootlearn.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Description Product 模型
 * @author coisini
 * @date Aug 15, 2021
 * @Version 1.0
 */
@Entity
@Getter
@Setter
@Where(clause = "delete_time is null")
public class Product {

    @Id
    private int id;
    private String title;
    private Date createTime;
    private Date deleteTime;

}
