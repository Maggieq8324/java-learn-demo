package com.coisini.aop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 统一消息返回
 * @author coisini
 * @date Oct 14, 2021
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnifyMessage {

    private int code;
    private String message;

}
