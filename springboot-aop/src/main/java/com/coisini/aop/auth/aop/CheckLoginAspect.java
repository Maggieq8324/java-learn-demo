package com.coisini.aop.auth.aop;

import com.coisini.aop.exception.ServerErrorException;
import com.coisini.aop.util.JwtUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description 登录校验切面
 *      @Aspect 切面注解
 * @author coisini
 * @date Oct 14, 2021
 * @Version 1.0
 */
@Aspect
@Component
public class CheckLoginAspect {

    /**
     * 只要加了@CheckLogin的方法都会走到这里
     * @param point
     * @return
     */
    @Around("@annotation(com.coisini.aop.auth.annotation.CheckLogin)")
    public Object checkLogin(ProceedingJoinPoint point) {
        try {
            // 从header中获取token
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest request = attributes.getRequest();

            String token = request.getHeader("token");

            // 校验token是否合法
            Boolean valid = JwtUtil.verifyToken(token);
            if (!valid) {
                throw new ServerErrorException(HttpStatus.UNAUTHORIZED.value(), "Token 不合法");
            }

            // 执行后续的方法
            return point.proceed();
        } catch (Throwable throwable) {
            throw new ServerErrorException(HttpStatus.UNAUTHORIZED.value(), "Token 不合法");
        }
    }

}
