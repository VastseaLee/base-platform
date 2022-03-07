package com.base.api.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserInfo implements Serializable {

    private String realName;

    private String address;
}
