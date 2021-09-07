package com.coisini.springbootlearn.exception.resp;

import com.coisini.springbootlearn.exception.basic.HttpException;

/**
 * @Description 创建成功
 * @author coisini
 * @date Aug 21, 2021
 * @Version 1.0
 */
public class CreateSuccess extends HttpException {

    public CreateSuccess(int code) {
        this.httpStatusCode = 201;
        this.code = code;
    }

}
