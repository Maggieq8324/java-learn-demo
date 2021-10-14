package com.coisini.aop.exception;

import com.coisini.aop.model.UnifyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description 统一异常处理
 * @author coisini
 * @date Oct 14, 2021
 * @Version 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity<UnifyMessage> handleServerErrorException(ServerErrorException e) {

        log.warn("ServerErrorException 异常", e);

        return new ResponseEntity<>(
                UnifyMessage.builder()
                        .code(e.getCode())
                        .message(e.getMessage())
                        .build(),
                HttpStatus.UNAUTHORIZED
        );
    }

}
