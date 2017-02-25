package com.tianma.api.service.impl.user;

import com.tianma.api.domain.user.User;
import com.tianma.api.service.user.UserService;
import com.tianma.api.domain.user.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String name)
			throws UsernameNotFoundException {

		User requestedUser = new User();
		requestedUser.setName(name);

		User savedUser = userService.findByName(name);

		return new UserDetailsImpl(savedUser);
	}

}
