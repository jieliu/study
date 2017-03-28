package com.egoo.sso.server.service.security.currentuser;

import com.egoo.sso.server.model.security.CurrentUser;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Long userId);

}
