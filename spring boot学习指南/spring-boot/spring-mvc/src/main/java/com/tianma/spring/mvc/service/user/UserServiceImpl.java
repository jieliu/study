package com.tianma.spring.mvc.service.user;


import com.tianma.spring.mvc.domain.security.User;
import com.tianma.spring.mvc.domain.security.UserCreateForm;
import com.tianma.spring.mvc.support.utils.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

//    @Autowired
//    private UserRepository userRepository;

    public User getUserById(long id) {
        LOGGER.debug("Getting login={}", id);
        //return userRepository.findOne(id);
        User user = new User();
        user.setId(1L);
        user.setEmail("egoo@egoo.net");
        user.setPasswordHash(new BCryptPasswordEncoder().encode("123456"));
        user.setRole(Role.ADMIN);
        return user;
    }


    public User getUserByEmail(String email) {
        LOGGER.debug("Getting login by email={}", email.replaceFirst("@.*", "@***"));
        //return userRepository.findOneByEmail(email);
        User user = new User();
        user.setId(1L);
        user.setEmail("fiboliu@163.com");
        user.setPasswordHash(new BCryptPasswordEncoder().encode("123456"));
        user.setRole(Role.ADMIN);
        return user;
    }

    public Collection<User> getAllUsers() {
        LOGGER.debug("Getting all users");
        Collection<User> userList = new ArrayList<User>();
        User user = new User();
        user.setId(1L);
        user.setPasswordHash(new BCryptPasswordEncoder().encode("123456"));
        user.setRole(Role.USER);
        user.setEmail("fiboliu@163.com");
        userList.add(user);
        return userList;
    }

    public User create(UserCreateForm form) {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setId(1L);
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRole(form.getRole());
        return user;
    }

}
