package com.tianma.spring.mvc.service.currentuser;


import com.tianma.spring.mvc.domain.security.CurrentUser;
import com.tianma.spring.mvc.support.utils.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserDetailsService.class);

    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
        LOGGER.debug("Checking if login={} has access to login={}", currentUser, userId);
        return currentUser != null
                && (currentUser.getRole() == Role.ADMIN || currentUser.getId().equals(userId));
    }

}
