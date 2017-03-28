package com.egoo.sso.server.model.security;

import com.egoo.sso.server.util.Role;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * 当前登录用户信息
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private UserInfoVo userInfoVo;

    public CurrentUser(UserInfoVo userInfoVo) {
        super(userInfoVo.getUserId(), userInfoVo.getPassword(), AuthorityUtils.createAuthorityList(userInfoVo.getRole().toString()));
        this.userInfoVo = userInfoVo;
    }

    public UserInfoVo getUser() {
        return userInfoVo;
    }

    public String getUserId() {
        return userInfoVo.getUserId();
    }

    public Role getRole() {
        return userInfoVo.getRole();
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "user=" + userInfoVo +
                '}';
    }
}
