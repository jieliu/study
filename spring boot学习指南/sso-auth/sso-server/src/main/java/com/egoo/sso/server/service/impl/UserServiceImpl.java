package com.egoo.sso.server.service.impl;

import com.egoo.sso.server.model.security.UserInfoVo;

import com.egoo.sso.server.service.UserService;
import com.egoo.sso.server.util.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by fiboliu on 16-11-1.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserInfoVo getUserById(String userId) {
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setUserId("17729293270");
        userInfoVo.setPassword("123456");
        userInfoVo.setRole(Role.USER);
        return userInfoVo;
    }
}
