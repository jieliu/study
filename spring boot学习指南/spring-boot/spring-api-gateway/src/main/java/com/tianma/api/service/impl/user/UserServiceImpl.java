package com.tianma.api.service.impl.user;


import com.tianma.api.domain.user.User;
import com.tianma.api.support.Dao;
import com.tianma.api.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private Dao dao;


    public User findByName(String name) {

        return dao.get("egoo.domain.user",".selectUserByName",name);
    }


    public void delectByName(String name){

        dao.delete("egoo.domain.user",".delectUserByName",name);
    }

    public void updateUser(User user){//这个就是更新了个密码
        dao.update("egoo.domain.user",".updateUser",user);

    }

    public User createDefaultUser() {
        User user = new User();
        user.setEmail("a@1.com");
        user.setName("zpw123");
        user.setPassword("123");
        user.setRole(User.Role.ROLE_USER);
        user.setStatus(User.Status.ACTIVATED);
        dao.insert("egoo.domain.user", ".insertUser", user);
        return user;
    }

    public User createUser(User toCreate) {
        dao.insert("egoo.domain.user", ".insertUser", toCreate);
        return toCreate;
    }

}
