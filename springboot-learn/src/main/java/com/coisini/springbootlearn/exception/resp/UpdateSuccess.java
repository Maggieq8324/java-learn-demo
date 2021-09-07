package com.coisini.springbootlearn.exception.resp;

import com.coisini.springbootlearn.exception.basic.HttpException;

/**
 * @Description 修改成功
 * @author coisini
 * @date Aug 21, 2021
 * @Version 1.0
 */
public class UpdateSuccess extends HttpException {

    public UpdateSuccess(int code){
        this.httpStatusCode = 200;
        this.code = code;
    }

}
