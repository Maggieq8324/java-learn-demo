package com.coisini.springbootlearn.exception.http;

import com.coisini.springbootlearn.exception.basic.HttpException;

/**
 * @Description http无权限异常
 * @author coisini
 * @date Aug 9, 2021
 * @Version 1.0
 */
public class ForbiddenException extends HttpException {

    public ForbiddenException (int code) {
        this.code = code;
        this.httpStatusCode = 403;
    }

}
