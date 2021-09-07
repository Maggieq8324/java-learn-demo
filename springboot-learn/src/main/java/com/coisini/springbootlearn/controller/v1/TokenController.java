package com.coisini.springbootlearn.controller.v1;

import com.coisini.springbootlearn.dto.TokenDTO;
import com.coisini.springbootlearn.dto.TokenGetDTO;
import com.coisini.springbootlearn.exception.http.NotFoundException;
import com.coisini.springbootlearn.service.WxAuthenticationService;
import com.coisini.springbootlearn.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description Token 控制器
 * @author coisini
 * @date Aug 18, 2021
 * @Version 1.0
 */
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private WxAuthenticationService wxAuthenticationService;

    /**
     * 获取Token
     * @param userData
     * @return
     */
    @PostMapping("")
    public Map<String, String> getToken(@RequestBody @Validated TokenGetDTO userData) {
        Map<String, String> map = new HashMap<>();
        String token = null;

        switch (userData.getType()) {
            case USER_WX:
                token = wxAuthenticationService.code2session(userData.getAccount());
                break;
            case USER_EMAIL:
                break;
            default:
                throw new NotFoundException(10003);
        }

        map.put("token", token);
        return map;
    }

    /**
     * 验证Token
     * @param token
     * @return
     */
    @PostMapping("/verify")
    public Map<String, Boolean> verify(@RequestBody TokenDTO token) {
        Map<String, Boolean> map = new HashMap<>();
        Boolean valid = JwtToken.verifyToken(token.getToken());
        map.put("is_valid", valid);
        return map;
    }

}
