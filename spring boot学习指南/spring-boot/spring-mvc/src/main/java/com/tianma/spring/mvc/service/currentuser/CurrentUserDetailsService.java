package com.tianma.spring.mvc.service.currentuser;


import com.tianma.spring.mvc.domain.security.CurrentUser;
import com.tianma.spring.mvc.domain.security.User;
import com.tianma.spring.mvc.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CurrentUserDetailsService.class);

    private final UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {

        this.userService = userService;
    }

    public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.debug("Authenticating login with email={}", email.replaceFirst("@.*", "@***"));
        User user = userService.getUserByEmail(email);
        return new CurrentUser(user);
    }

}
