package com.coisini.springbootlearn.service;

import com.coisini.springbootlearn.dto.TokenGetDTO;

public interface AuthenticationService {

    void getTokenByEmail(TokenGetDTO userData);

    void validateByWx(TokenGetDTO userData);

    void register();

}
