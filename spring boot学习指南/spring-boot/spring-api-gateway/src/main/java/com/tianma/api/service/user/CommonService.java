package com.tianma.api.service.user;

import com.tianma.api.domain.user.User;


public interface CommonService<T> {
	Long count();

    //检查用户对资源的权限
	boolean checkOwnership(User userFromToken, Long resourceId);
}
