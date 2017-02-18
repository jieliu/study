package com.tianma.api.service.user;

import com.tianma.api.domain.user.User;


public interface UserService {

    User findByName(String name);

    User createUser(User toCreate);

    public User createDefaultUser();

    void delectByName(String name);

}
