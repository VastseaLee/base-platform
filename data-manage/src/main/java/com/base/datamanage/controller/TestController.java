package com.base.datamanage.controller;

import com.base.api.auth.facade.AuthServerService;
import com.base.api.auth.model.UserInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @DubboReference
    private AuthServerService authServerService;

    public UserInfo queryUserInfo() {
        return authServerService.queryUserInfo();
    }
}
