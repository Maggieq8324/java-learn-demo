package com.coisini.springbootlearn.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @Description Tag 模型
 * @author coisini
 * @date Aug 26, 2021
 * @Version 1.0
 */
@Entity
@Getter
@Setter
public class Tag extends BaseEntity{

    @Id
    private int id;
    private String title;
    private String description;
    private Boolean highlight;
    private Boolean type;

}
