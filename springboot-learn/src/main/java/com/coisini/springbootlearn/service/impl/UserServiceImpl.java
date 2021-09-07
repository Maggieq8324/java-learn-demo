package com.coisini.springbootlearn.service.impl;

import com.coisini.springbootlearn.core.common.LocalUser;
import com.coisini.springbootlearn.model.User;
import com.coisini.springbootlearn.repository.UserRepository;
import com.coisini.springbootlearn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description User 实现类
 * @author coisini
 * @date Aug 20, 2021
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findFirstById(id);
    }

    @Override
    public void updateUserWxInfo(Map<String, Object> wxUser) {
        User user =this.getUserById(LocalUser.getUser().getId());
        user.setNickname(wxUser.get("nickName").toString());
        user.setWxProfile(wxUser);
        userRepository.save(user);
    }
}
