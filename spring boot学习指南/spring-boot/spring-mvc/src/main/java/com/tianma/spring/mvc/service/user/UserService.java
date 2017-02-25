package com.tianma.spring.mvc.service.user;


import com.tianma.spring.mvc.domain.security.User;
import com.tianma.spring.mvc.domain.security.UserCreateForm;

import java.util.Collection;

public interface UserService {

    User getUserById(long id);

    User getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);

}
