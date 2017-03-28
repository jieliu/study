package com.egoo.sso.server.service.impl;

import com.egoo.sso.server.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zuowenxia on 2017/3/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUserById() throws Exception {
        System.out.println(userService.getUserById("17729293270"));
    }

}