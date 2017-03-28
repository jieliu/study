package com.egoo.sso.server.service.security.currentuser;

import com.egoo.sso.server.model.security.CurrentUser;
import com.egoo.sso.server.util.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    private static final Logger logger = LoggerFactory.getLogger(CurrentUserDetailsService.class);

    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
        logger.debug("Checking if login={} has access to login={}", currentUser, userId);
        return currentUser != null
                && (currentUser.getRole() == Role.ADMIN || currentUser.getUserId().equals(userId));
    }

}
