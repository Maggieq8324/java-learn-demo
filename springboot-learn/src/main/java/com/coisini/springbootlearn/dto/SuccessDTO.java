package com.coisini.springbootlearn.dto;

import com.coisini.springbootlearn.util.HttpRequestProxy;
import lombok.*;

/**
 * @Description
 * @author coisini
 * @date
 * @Version 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuccessDTO {

    private Integer code = 0;
    private String message = "ok";
    private String request = HttpRequestProxy.getRequestUrl();

}
