package com.coisini.aop.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description 自定义异常
 * @author coisini
 * @date Oct 14, 2021
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class ServerErrorException extends RuntimeException{

    public Integer code;
    public String message;

}
