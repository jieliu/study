package com.tianma.api.domain.user;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@SuppressWarnings("serial")
public class UserDetailsImpl implements UserDetails {
//实现了security里给出的类
	private User user;
	private Collection<? extends GrantedAuthority> authorities;//自定义的
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialNonExpired;

	public UserDetailsImpl(User savedUser) {
		this.user = savedUser;
		this.authorities = AuthorityUtils.createAuthorityList(user.getStringRole());//我们得来从数据库里读取授权
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialNonExpired = true;
	}
		
	public User getUser() {
		return user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}//这里是USerDetail类定义的验证的时候获取密码的方法

	@Override
	public String getUsername() {
		return user.getName();
	}//验证的时候获取用户名

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return (User.Status.ACTIVATED == user.getStatus()) ? true : false;

    }

}
