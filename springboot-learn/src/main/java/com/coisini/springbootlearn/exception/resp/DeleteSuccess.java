package com.coisini.springbootlearn.exception.resp;

import com.coisini.springbootlearn.exception.basic.HttpException;

/**
 * @Description 删除成功
 * @author coisini
 * @date Aug 21, 2021
 * @Version 1.0
 */
public class DeleteSuccess extends HttpException {

    public DeleteSuccess(int code){
        this.httpStatusCode = 200;
        this.code = code;
    }

}
