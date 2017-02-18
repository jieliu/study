package com.tianma.spring.mvc.service.currentuser;


import com.tianma.spring.mvc.domain.security.CurrentUser;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Long userId);

}
