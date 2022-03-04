package com.base.authserver.facade;

import com.base.api.auth.facade.AuthServerService;
import com.base.api.auth.model.UserInfo;
import com.base.authserver.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class AuthServerServiceImpl implements AuthServerService {

    @Autowired
    private UserService userService;

    @Override
    public UserInfo queryUserInfo() {
        return userService.queryUserInfo();
    }
}
