package com.coisini.springbootlearn.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description OrderAddress DTO
 * @author coisini
 * @date Aug 21, 2021
 * @Version 1.0
 */
@Getter
@Setter
public class OrderAddressDTO {

    private String userName;
    private String province;
    private String city;
    private String county;
    private String mobile;
    /**
     * 国家码
     */
    private String nationalCode;
    /**
     * 邮政编码
     */
    private String postalCode;
    private String detail;

}

