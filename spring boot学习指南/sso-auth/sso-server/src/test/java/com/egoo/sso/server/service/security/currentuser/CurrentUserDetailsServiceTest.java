package com.egoo.sso.server.service.security.currentuser;

import com.egoo.sso.server.model.security.CurrentUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zuowenxia on 2017/3/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CurrentUserDetailsServiceTest {

    @Autowired
    private CurrentUserDetailsService userDetailsService;

    @Test
    public void loadUserByUsername() throws Exception {

        CurrentUser currentUser = userDetailsService.loadUserByUsername("17729293270");
        if(currentUser == null) {
            System.out.println("null");
        } else {
            System.out.println(currentUser.toString());
        }
    }

}