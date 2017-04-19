package com.tianma.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by zuowenxia on 2017/4/18.
 */
@RunWith(SpringRunner.class)
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
        System.out.println(userMapper.selectAllUsers());
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