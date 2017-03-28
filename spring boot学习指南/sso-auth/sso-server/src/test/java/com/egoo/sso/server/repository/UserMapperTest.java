package com.egoo.sso.server.repository;

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
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectUserById() throws Exception {

    }

    @Test
    public void selectUserByUsername() throws Exception {

    }

    @Test
    public void selectAllUsers() throws Exception {
        System.out.println(userMapper.selectAllUsers().toString());
    }

    @Test
    public void insertUser() throws Exception {

    }

    @Test
    public void updateUserOnPasswordById() throws Exception {

    }

    @Test
    public void deleteUserById() throws Exception {

    }

}