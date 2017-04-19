package com.tianma.repository;

import com.tianma.model.UserDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by zuowenxia on 2017/3/31.
 */

@Mapper
public interface UserMapper {

    UserDo selectUserById(String userId);

    UserDo selectUserByUsername(String username);

    List<UserDo> selectAllUsers();

    Integer insertUser(UserDo user);

    Integer updateUserOnPasswordById(UserDo user);

    Integer deleteUserById(String userId);
}
