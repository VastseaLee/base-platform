package com.base.authserver.service.impl;

import com.base.api.auth.model.UserInfo;
import com.base.authserver.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserInfo queryUserInfo() {
        return new UserInfo("Young","禹贡科技");
    }
}
