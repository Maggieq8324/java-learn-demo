package com.coisini.springbootlearn.dto;

import com.coisini.springbootlearn.core.annotation.TokenPassword;
import com.coisini.springbootlearn.core.enumeration.LoginType;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

/**
 * @Description Token DTO
 * @author coisini
 * @date Aug 18, 2021
 * @Version 1.0
 */
@Getter
@Setter
public class TokenGetDTO {

    @NotBlank(message = "account不允许为空")
    private String account;

    @TokenPassword(max=30, message = "{token.password}")
    private String password;

    private LoginType type;

}
