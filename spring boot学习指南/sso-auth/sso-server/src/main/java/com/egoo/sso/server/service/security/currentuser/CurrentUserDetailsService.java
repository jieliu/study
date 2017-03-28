package com.egoo.sso.server.service.security.currentuser;


import com.egoo.sso.server.model.security.CurrentUser;
import com.egoo.sso.server.model.security.UserInfoVo;
import com.egoo.sso.server.service.UserService;
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

    public CurrentUser loadUserByUsername(String userId) throws UsernameNotFoundException {
        logger.debug("Authenticating login with userId={}", userId.replaceFirst("@.*", "@***"));
        UserInfoVo user = userService.getUserById(userId);
        if(user == null) {
            return null;
        }
        System.out.println(user.toString());
        return new CurrentUser(user);
    }

}
