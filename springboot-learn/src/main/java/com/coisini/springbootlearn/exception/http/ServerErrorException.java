package com.coisini.springbootlearn.exception.http;

import com.coisini.springbootlearn.exception.basic.HttpException;

/**
 * @Description 业务异常
 * @author coisini
 * @date Aug 16, 2021
 * @Version 1.0
 */
public class ServerErrorException extends HttpException {
    public ServerErrorException(int code){
        this.code = code;
        this.httpStatusCode = 500;
    }
}

