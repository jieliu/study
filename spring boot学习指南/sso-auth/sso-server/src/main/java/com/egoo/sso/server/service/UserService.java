package com.egoo.sso.server.service;

import com.egoo.sso.server.model.security.UserInfoVo;

/**
 * Created by fiboliu on 16-11-1.
 */
public interface UserService {
    UserInfoVo getUserById(String userId);
}
