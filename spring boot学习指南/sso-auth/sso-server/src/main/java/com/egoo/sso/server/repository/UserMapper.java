package com.egoo.sso.server.repository;

import com.egoo.sso.server.model.security.UserDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    UserDo selectUserById(String userId);

    UserDo selectUserByUsername(String username);

    List<UserDo> selectAllUsers();

    Integer insertUser(UserDo user);

    Integer updateUserOnPasswordById(UserDo user);

    Integer deleteUserById(String userId);
}
