package com.coisini.springbootlearn.exception.http;

import com.coisini.springbootlearn.exception.basic.HttpException;

/**
 * @Description http 404 异常
 * @author coisini
 * @date Aug 9, 2021
 * @Version 1.0
 */
public class NotFoundException extends HttpException {

    public NotFoundException(int code) {
        this.httpStatusCode = 404;
        this.code = code;
    }
}
