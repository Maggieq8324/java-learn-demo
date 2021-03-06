package com.coisini.springbootlearn.exception.http;

import com.coisini.springbootlearn.exception.basic.HttpException;

/**
 * @Description 未登陆权限异常
 * @author coisini
 * @date Aug 19, 2021
 * @Version 1.0
 */
public class UnAuthenticatedException extends HttpException {
    public UnAuthenticatedException(int code){
        this.code = code;
        this.httpStatusCode = 401;
    }
}
